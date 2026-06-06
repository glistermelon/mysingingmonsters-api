package com.smartfoxserver.v2.protocol.serialization;

import com.smartfoxserver.v2.entities.data.ISFSArray;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSArray;
import com.smartfoxserver.v2.entities.data.SFSObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ISFSDataSerializer {
   byte[] object2binary(ISFSObject var1);

   byte[] array2binary(ISFSArray var1);

   ISFSObject binary2object(byte[] var1);

   ISFSArray binary2array(byte[] var1);

   ISFSObject pojo2sfs(Object var1);

   Object sfs2pojo(ISFSObject var1);

   SFSObject resultSet2object(ResultSet var1) throws SQLException;

   SFSArray resultSet2array(ResultSet var1) throws SQLException;
}
