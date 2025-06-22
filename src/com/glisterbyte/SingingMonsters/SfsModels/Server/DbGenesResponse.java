package com.glisterbyte.SingingMonsters.SfsModels.Server;

import com.glisterbyte.SfsMapping.SfsArrayElementType;
import com.glisterbyte.SingingMonsters.SfsModels.SfsChunked;
import com.glisterbyte.SingingMonsters.SfsModels.SfsCmd;

import java.util.List;

@SfsCmd("db_gene")
@SfsChunked("genes_data")
public class DbGenesResponse extends SfsResponseModel {
    @SfsArrayElementType(SfsGene.class)
    public List<SfsGene> genesData;
}
