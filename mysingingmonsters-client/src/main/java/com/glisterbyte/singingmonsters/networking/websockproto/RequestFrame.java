package com.glisterbyte.singingmonsters.networking.websockproto;

import com.glisterbyte.singingmonsters.networking.exceptions.ClientSerializeException;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.protocol.serialization.DefaultSFSDataSerializer;
import okio.ByteString;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class RequestFrame {

    public long seqNum;
    public String command;
    public SFSObject data;

    public ByteString serialize() throws ClientSerializeException {

        try {

            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            DataOutputStream dataStream = new DataOutputStream(byteStream);

            dataStream.writeLong(seqNum);

            dataStream.writeShort(command.length());
            dataStream.write(command.getBytes());

            DefaultSFSDataSerializer serializer = DefaultSFSDataSerializer.getInstance();
            dataStream.write(serializer.object2binary(data));

            dataStream.flush();
            return ByteString.of(byteStream.toByteArray());

        }
        catch (IOException ex) {
            throw new ClientSerializeException(ex);
        }

    }

}