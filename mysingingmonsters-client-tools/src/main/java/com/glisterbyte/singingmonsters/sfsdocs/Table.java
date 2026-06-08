package com.glisterbyte.singingmonsters.sfsdocs;

import java.util.ArrayList;
import java.util.List;

import static com.glisterbyte.singingmonsters.common.StringUtil.format;

public class Table {

    private int width;
    private int height = 0;

    private List<Integer> columnWidths;

    private final List<String> columnNames;
    private final List<List<String>> columns = new ArrayList<>();

    public Table(String... columns) {
        width = columns.length;
        columnNames = new ArrayList<>(List.of(columns));
        for (int i = 0; i < width; i++) this.columns.add(new ArrayList<>());
    }

    public void setColumnWidths(Integer... widths) {
        columnWidths = new ArrayList<>(List.of(widths));
    }

    public void addRow(String... cells) {
        if (cells.length != width) throw new RuntimeException(
                format("cells.length ({}) must equal table width ({})", cells.length, width)
        );
        for (int i = 0; i < cells.length; i++) {
            String cell = cells[i];
            if (cell == null) cell = "";
            else cell = cell.strip();
            columns.get(i).add(cell);
        }
        height++;
    }

    private static void addListTableRow(List<String> output, List<String> cells) {
        for (int i = 0; i < cells.size(); i++) {
            String line = "  - " + cells.get(i);
            if (i == 0) line = "*" + line.substring(1);
            output.add(line);
        }
    }

    @Override
    public String toString() {

        for (int col = 0; col < width; col++) {
            if (columns.get(col).stream().allMatch(String::isEmpty)) {
                columnNames.remove(col);
                columns.remove(col);
                if (columnWidths != null) columnWidths.remove(col);
                col--;
                width--;
            }
        }

        List<String> lines = new ArrayList<>();

        lines.add("```{list-table}");
        if (columnWidths != null) {
            lines.add(":widths: " + String.join(" ", columnWidths.stream().map(Object::toString).toList()));
        }
        lines.add(":header-rows: 1");
        addListTableRow(lines, columnNames);
        for (int i = 0; i < height; i++) {
            int finalI = i;
            addListTableRow(lines, columns.stream().map(c -> c.get(finalI)).toList());
        }
        lines.add("```");

        return String.join("\n", lines);
    }

    public String toBasicMdString() {

        for (int col = 0; col < width; col++) {
            if (columns.get(col).stream().allMatch(String::isEmpty)) {
                columnNames.remove(col);
                columns.remove(col);
                col--;
                width--;
            }
        }

        List<String> lines = new ArrayList<>();

        lines.add("| " + String.join(" | ", columnNames) + " |");

        List<String> dashes = new ArrayList<>();
        for (int i = 0; i < width; i++) dashes.add("-");
        lines.add("|" + String.join("|", dashes) + "|");

        for (int i = 0; i < height; i++) {
            int finalI = i;
            String joinedRow = String.join(
                    " | ", columns.stream().map(c -> c.get(finalI)).toList()
            );
            lines.add("| " + joinedRow + " |");
        }
        return String.join("\n", lines);

    }

    public boolean isEmpty() {
        return height == 0;
    }

}