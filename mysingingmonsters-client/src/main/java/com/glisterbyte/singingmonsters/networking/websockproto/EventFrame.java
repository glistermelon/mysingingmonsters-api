package com.glisterbyte.singingmonsters.networking.websockproto;

import com.glisterbyte.singingmonsters.networking.exceptions.ClientDeserializeException;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.protocol.serialization.DefaultSFSDataSerializer;
import okio.ByteString;

import java.io.*;

public class EventFrame {

    public String command;
    public SFSObject data;

    public static EventFrame deserialize(ByteString byteString) throws ClientDeserializeException {

        DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(byteString.toByteArray()));

        EventFrame frame = new EventFrame();

        try {

            int commandLen = inputStream.readUnsignedShort();
            byte[] commandBytes = new byte[commandLen];
            inputStream.readFully(commandBytes);
            frame.command = new String(commandBytes);

            byte[] dataBytes = inputStream.readAllBytes();
            DefaultSFSDataSerializer serializer = DefaultSFSDataSerializer.getInstance();
            try {
                frame.data = (SFSObject)serializer.binary2object(dataBytes);
            }
            catch (IllegalStateException ex) {
                throw new ClientDeserializeException(ex);
            }

        }
        catch (IOException ex) {
            throw new ClientDeserializeException(ex);
        }

        return frame;

    }

}