package com.glisterbyte.singingmonsters.sfsmodels.events;

import com.glisterbyte.singingmonsters.sfsmapping.SfsArrayElementType;
import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmapping.SfsOptional;
import com.glisterbyte.singingmonsters.sfsmodels.DbResponse;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsGene;

import java.util.ArrayList;
import java.util.List;

@SfsCmd("db_gene")
public class DbGenesResponse extends DbResponse {

    @SfsOptional
    @SfsArrayElementType(SfsGene.class)
    public List<SfsGene> genes_data = new ArrayList<>();

    public int getElementCount() {
        return genes_data == null ? 0 : genes_data.size();
    }

}