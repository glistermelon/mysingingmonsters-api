package com.glisterbyte.singingmonsters;

import com.glisterbyte.singingmonsters.exceptions.ClientException;
import com.glisterbyte.singingmonsters.main.catalog.Catalog;
import com.glisterbyte.singingmonsters.main.catalog.MonsterSpecies;
import com.glisterbyte.singingmonsters.main.client.Client;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.time.Duration;
import java.util.*;

public class GenerateMonsterCatalogCode {

    private static final String ISLAND_DEFAULT = "DEFAULT";
    private static final String ISLAND_GOLD = "GOLD";
    private static final String ISLAND_SHUGABUSH = "SHUGABUSH";
    private static final String ISLAND_FIRE_HAVEN = "FIRE_HAVEN";
    private static final String ISLAND_FIRE_OASIS = "FIRE_OASIS";
    private static final String ISLAND_SEASONAL_SHANTY = "SEASONAL_SHANTY";
    private static final String ISLAND_COMPOSER = "COMPOSER";

    private static final String TAB = "    ";

    public static void main(String[] args) throws ClientException, InterruptedException {

        Client client = new Client();
        client.connectWithEmail(Configuration.email, Configuration.password);
        client.disconnect();

        Catalog catalog = client.getCatalog();

        Map<String, Map<String, Integer>> map = new LinkedHashMap<>();

        for (
                MonsterSpecies species : catalog.getAllMonsterSpecies().stream()
                    .sorted(Comparator.comparing(MonsterSpecies::getSpeciesId)).toList()
        ) {

            String name = species.getCommonName();
            final int id = species.getSpeciesId();

            name = name.replace("'", "");
            name = name.replace("-", "");
            name = name.replace("(", "");
            name = name.replace(")", "");
            name = name.toLowerCase();

            List<String> nameParts = new ArrayList<>(Arrays.stream(name.split("\\s")).toList());

            if (List.of("do", "re", "mi", "fa", "sol", "la", "ti").contains(nameParts.get(0))) {
                nameParts.addFirst("dipster");
            }

            String island = ISLAND_DEFAULT;

            if (!(nameParts.contains("epic") && nameParts.contains("wubbox"))) {

                if (nameParts.getLast().equals("island")) {
                    island = nameParts.reversed().get(1);
                    nameParts = new ArrayList<>(nameParts.subList(0, nameParts.size() - 2));
                }
                else if (nameParts.size() > 2 && nameParts.get(1).equals("island")) {
                    island = nameParts.getFirst();
                    nameParts = new ArrayList<>(nameParts.subList(2, nameParts.size()));
                }
                else if (nameParts.getFirst().equals("legendary")) {
                    island = ISLAND_SHUGABUSH;
                    nameParts.removeFirst();
                }

                if (nameParts.contains("wubbox") && nameParts.size() > 1) {
                    if (nameParts.reversed().get(1).equals("fire") && nameParts.getLast().equals("haven")) {
                        island = ISLAND_FIRE_HAVEN;
                        nameParts = new ArrayList<>(nameParts.subList(0, nameParts.size() - 2));
                    }
                    else if (nameParts.reversed().get(1).equals("fire") && nameParts.getLast().equals("oasis")) {
                        island = ISLAND_FIRE_OASIS;
                        nameParts = new ArrayList<>(nameParts.subList(0, nameParts.size() - 2));
                    }
                }

            }
            else if (nameParts.getFirst().equals("gold")) {

                // Gold Island Epic Wubbox 01/02/03/04/05

                System.out.println(name);

                island = ISLAND_GOLD;
                nameParts = new ArrayList<>(nameParts.subList(2, nameParts.size()));
                String[] naturalIslands = { "plant", "cold", "air", "water", "earth" };
                nameParts.set(nameParts.size() - 1, naturalIslands[Integer.parseInt(nameParts.getLast()) - 1]);
                nameParts.add("island");

            }

            island = switch (island) {
                case "fire" -> ISLAND_FIRE_HAVEN;
                case "seasonal" -> ISLAND_SEASONAL_SHANTY;
                case "composer" -> ISLAND_COMPOSER;
                default -> island.toUpperCase();
            };

            for (int i = 1; i < nameParts.size(); i++) {
                String part = nameParts.get(i);
                nameParts.set(i, Character.toUpperCase(part.charAt(0)) + part.substring(1));
            }
            name = String.join("", nameParts);

            map.computeIfAbsent(name, x -> new HashMap<>()).put(island, id);

        }

        List<String> lines = new ArrayList<>();

        lines.add("// The following is auto-generated");
        lines.add("");

        for (String name : map.keySet()) {
            lines.add("private MultiMonsterSpecies " + name + "Instance;");
        }

        lines.add("public void init() {");
        lines.add("");
        lines.add(TAB + "MonsterSpecies defaultSpecies;");
        lines.add(TAB + "Map<IslandType, MonsterSpecies> speciesMap;");

        for (var entry : map.entrySet()) {

            String name = entry.getKey();
            Map<String, Integer> islandToSpeciesIdMap = entry.getValue();

            lines.add("");
            if (islandToSpeciesIdMap.containsKey(ISLAND_DEFAULT)) {
                lines.add(
                        TAB + "defaultSpecies = catalog.getMonsterSpecies("
                                + islandToSpeciesIdMap.get(ISLAND_DEFAULT) + ");"
                );
            }
            else {
                lines.add(TAB + "defaultSpecies = null;");
            }

            lines.add(TAB + "speciesMap = new HashMap<>();");

            if (islandToSpeciesIdMap.containsKey(ISLAND_COMPOSER)) {
                lines.add(TAB + "for (IslandType composerType : IslandType.allComposerTypes()) {");
                lines.add(
                        TAB + TAB + "speciesMap.put(composerType, catalog.getMonsterSpecies("
                                + islandToSpeciesIdMap.get(ISLAND_COMPOSER) + "));"
                );
                lines.add(TAB + "}");
            }

            for (String island : islandToSpeciesIdMap.keySet()) {

                if (island.equals(ISLAND_DEFAULT) || island.equals(ISLAND_COMPOSER)) continue;

                String islandType = island;
                if (!islandType.contains("_")) islandType += "_ISLAND";

                lines.add(
                        TAB + "speciesMap.put(IslandType." + islandType
                                + ", catalog.getMonsterSpecies(" + islandToSpeciesIdMap.get(island) + "));"
                );

            }

            lines.add(TAB + name + "Instance = new MultiMonsterSpecies(defaultSpecies, speciesMap);");

        }

        lines.add("");
        lines.add("}");

        for (String name : map.keySet()) {
            lines.add("");
            lines.add("public MultiMonsterSpecies " + name + "() {");
            lines.add(TAB + "return " + name + "Instance;");
            lines.add("}");
        }

        String java = String.join("\n", lines);

        System.out.println(java);

        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(java), null);
        Thread.sleep(Duration.ofSeconds(1));

    }

}