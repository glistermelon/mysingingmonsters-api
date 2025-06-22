package com.glisterbyte;

import com.glisterbyte.Localization.Language;
import com.glisterbyte.Localization.LocalizedResources;
import com.glisterbyte.SingingMonsters.Cache;
import com.glisterbyte.SingingMonsters.Element;
import com.glisterbyte.SingingMonsters.MonsterType;
import com.glisterbyte.SingingMonsters.StructureType;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {

        Cache.preload();
        LocalizedResources.loadAllLanguages();

        MonsterType monsterType = MonsterType.rareShrubb();
        System.out.println(monsterType.getName());
        System.out.println(monsterType.getName(Language.JAPANESE));
        for (Element element : monsterType.getElements()) {
            System.out.println(element.getName());
        }
        System.out.println(monsterType.getDescription());
        System.out.println(monsterType.getDescription(Language.SPANISH));

        StructureType structureType = StructureType.amberedThing();
        System.out.println(structureType.getStructureCategory());
        System.out.println(structureType.getName());
        System.out.println(structureType.getDescription());

        structureType = StructureType.nexusNucleus();
        System.out.println(structureType.getStructureCategory());
        System.out.println(structureType.getName());
        System.out.println(structureType.getDescription(Language.FRENCH));

        structureType = StructureType.reflectingPool();
        System.out.println(structureType.getStructureCategory());
        System.out.println(structureType.getName());
        System.out.println(structureType.getDescription(Language.PORTUGUESE));

    }
}