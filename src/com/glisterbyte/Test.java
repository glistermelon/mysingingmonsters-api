package com.glisterbyte;

import com.glisterbyte.Localization.Language;
import com.glisterbyte.Network.Client;
import com.glisterbyte.Network.Credentials;
import com.glisterbyte.SingingMonsters.*;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {

        Client client = Client.newClient();
        client.connect(new Credentials("", ""));
        Monster monster = client.fetchPlayer().getIsland(IslandType.PLANT_ISLAND).getMonsters().getFirst();
        MonsterSpecies genericSpecies = monster.getGenericSpecies();
        System.out.println(genericSpecies.getName());
        IslandSpecificMonsterSpecies species = monster.getSpecificSpecies();
        System.out.println(species.getName());
        System.out.println(monster.getName());
        System.out.println(species.getDescription());
        System.out.println(species.getDescription(Language.SPANISH));
        System.out.println(species.getDescription(Language.FRENCH));

    }
}