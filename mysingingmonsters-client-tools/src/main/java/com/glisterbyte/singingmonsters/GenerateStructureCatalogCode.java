package com.glisterbyte.singingmonsters;

import com.glisterbyte.singingmonsters.exceptions.ClientException;
import com.glisterbyte.singingmonsters.main.catalog.Catalog;
import com.glisterbyte.singingmonsters.main.catalog.StructureType;
import com.glisterbyte.singingmonsters.main.client.Client;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.time.Duration;
import java.util.*;
import java.util.List;

public class GenerateStructureCatalogCode {

    private static final String TAB = "    ";

    public static void main(String[] args) throws ClientException, InterruptedException {

        Client client = new Client();
        client.connectWithEmail(Configuration.email, Configuration.password);
        client.disconnect();

        Catalog catalog = client.getCatalog();

        List<String> lines = new ArrayList<>();

        lines.add("// The following is auto-generated");

        List<String> previousNames = new ArrayList<>();
        for (
                StructureType structureType : catalog.getAllStructureTypes().stream()
                .sorted(Comparator.comparing(StructureType::getTypeId)).toList()
        ) {

            String name = structureType.getName();
            if (name == null || name.isEmpty()) continue;

            name = Character.toLowerCase(name.charAt(0)) + name
                    .substring(1).replace(" ", "");
            name = name.replace("'", "");
            name = name.replace("-", "");
            name = name.replace("(", "");
            name = name.replace(")", "");
            name = name.replace(",", "");
            name = name.replace(".", "");
            name = name.replace("/", "by");
            name = name.replace("${CAMPAIGN}", "campaign");

            if (name.endsWith("AnniversaryMonument")) {
                name = "anniversaryMonument" + name.substring(0, name.indexOf('A'));
            }

            if (previousNames.contains(name)) {
                int n = 2;
                while (previousNames.contains(name + n)) n++;
                name += n;
            }
            previousNames.add(name);

            lines.add("");
            lines.add("public StructureType " + name + "() {");
            lines.add(TAB + "return catalog.getStructureType(" + structureType.getTypeId() + ");");
            lines.add("}");

        }

        String java = String.join("\n", lines);

        System.out.println(java);

        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(java), null);
        Thread.sleep(Duration.ofSeconds(1));

    }

}