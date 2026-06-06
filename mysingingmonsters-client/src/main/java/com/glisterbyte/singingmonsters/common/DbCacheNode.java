package com.glisterbyte.singingmonsters.common;

import com.glisterbyte.singingmonsters.networking.WebsocketClient;
import com.glisterbyte.singingmonsters.sfsmapping.SfsMapper;
import com.glisterbyte.singingmonsters.exceptions.ClientException;
import com.glisterbyte.singingmonsters.sfsmodels.DbRequestModel;
import com.glisterbyte.singingmonsters.sfsmodels.DbResponse;
import com.glisterbyte.singingmonsters.sfsmodels.SfsChunkedDbResponse;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

public class DbCacheNode {

    private static final Logger logger = LoggerFactory.getLogger(DbCacheNode.class);

    private final Class<? extends DbRequestModel> requestType;
    private final Class<? extends DbResponse> responseType;

    private DbResponse cachedResponse;
    private final String fileName;

    public DbCacheNode(
            String fileName,
            Class<? extends DbRequestModel> requestType,
            Class<? extends DbResponse> responseType
    ) {
        this.fileName = fileName;
        this.requestType = requestType;
        this.responseType = responseType;
    }

    public synchronized DbResponse get() {
        return cachedResponse;
    }

    public synchronized void loadFromCache() {
        byte[] data = CacheManager.readFile(fileName);
        if (data == null) {
            cachedResponse = null;
            return;
        }
        try {
            ISFSObject sfsObject = SFSObject.newFromBinaryData(data);
            cachedResponse = SfsMapper.mapSFSObjectToClass(responseType, sfsObject);
        }
        catch (Throwable ex) {
            logger.warn("Loading cache file '{}' failed due to exception", fileName, ex);
            CacheManager.eraseFile(fileName);
            cachedResponse = null;
        }
    }

    public synchronized void update(WebsocketClient client) throws InterruptedException, ClientException {
        cachedResponse = null;
        loadFromCache();
        long lastUpdated = cachedResponse == null ? 0 : cachedResponse.server_time;
        DbRequestModel request;
        try {
            request = requestType.getDeclaredConstructor().newInstance();
        }
        catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException ex) {
            throw new RuntimeException(ex);
        }
        request.last_updated = lastUpdated;
        DbResponse response = (DbResponse)client.request(request, responseType);
        if (!response.isEmpty()) {
            if (response instanceof SfsChunkedDbResponse chunkedDbResponse && cachedResponse != null) {
                SfsChunkedDbResponse chunkedCachedResponse = (SfsChunkedDbResponse)cachedResponse;
                chunkedCachedResponse.getChunkedList().addAll(chunkedDbResponse.getChunkedList());
            }
            else cachedResponse = response;
            CacheManager.writeFile(fileName, cachedResponse.sfsObject.toBinary());
        }
        else if (cachedResponse == null) {
            throw new RuntimeException(responseType.getName() + " cache unexpectedly empty after attempting load");
        }
        logger.info("{} cache loaded {} elements", responseType.getName(), cachedResponse.getElementCount());
    }

}