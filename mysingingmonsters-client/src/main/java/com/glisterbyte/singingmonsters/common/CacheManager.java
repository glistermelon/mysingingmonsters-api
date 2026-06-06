package com.glisterbyte.singingmonsters.common;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class CacheManager {

    private final static Logger logger = LoggerFactory.getLogger(CacheManager.class);

    private CacheManager() { }

    public synchronized static void clear() {
        try {
            FileUtils.deleteDirectory(GlobalConfig.cacheDir.toFile());
        }
        catch (IOException ex) {
            throw new RuntimeException("Failed to erase cache directory '" + GlobalConfig.cacheDir + "'", ex);
        }
    }

    public synchronized static void ensureCacheDirExists() {
        try {
            if (!Files.exists(GlobalConfig.cacheDir)) Files.createDirectories(GlobalConfig.cacheDir);
        }
        catch (IOException ex) {
            throw new RuntimeException("Failed to ensure cache directory '" + GlobalConfig.cacheDir + "' exists", ex);
        }
    }

    public synchronized static void writeFile(String name, byte[] data) {
        ensureCacheDirExists();
        Path path = GlobalConfig.cacheDir.resolve(name);
        try {
            Files.write(path, data);
        }
        catch (IOException ex) {
            throw new RuntimeException("Failed to write file '" + path + "'", ex);
        }
    }

    public synchronized static void writeFile(String name, String data) {
        writeFile(name, data.getBytes());
    }

    public synchronized static byte[] readFile(String name) {
        Path path = GlobalConfig.cacheDir.resolve(name);
        if (!Files.exists(path)) return null;
        try {
            return Files.readAllBytes(path);
        }
        catch (IOException ex) {
            logger.warn("Failed to read cache file '{}'", path);
            try {
                Files.deleteIfExists(path);
            }
            catch (IOException ex2) {
                throw new RuntimeException(
                        StringUtil.format("Erase file '{}' failed due to exception", path),
                        ex2
                );
            }
            return null;
        }
    }

    public synchronized static String readFileAsString(String name) {
        return Optional.ofNullable(readFile(name)).map(String::new).orElse(null);
    }

    public synchronized static void eraseFile(String name) {
        Path path = GlobalConfig.cacheDir.resolve(name);
        try {
            Files.deleteIfExists(path);
        }
        catch (IOException ex) {
            throw new RuntimeException(
                    StringUtil.format("Erase file '{}' failed due to exception", path),
                    ex
            );
        }
    }

}