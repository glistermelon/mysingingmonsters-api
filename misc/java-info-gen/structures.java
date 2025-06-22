package com.glisterbyte;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.glisterbyte.Localization.Language;
import com.glisterbyte.Localization.LocalizedResources;
import com.glisterbyte.SingingMonsters.Cache;
import com.glisterbyte.SingingMonsters.StructureType;

import java.io.File;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        Cache.preload();
        LocalizedResources.loadAllLanguages();
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode root = mapper.createArrayNode();
        for (StructureType type : Cache.getAllStructureTypes()) {
            ObjectNode node = mapper.createObjectNode();
            node.put("name", type.getName(Language.ENGLISH));
            node.put("id", type.getTypeId());
            root.add(node);
        }
        mapper.writer().writeValue(new File("structures.json"), root);
    }
}