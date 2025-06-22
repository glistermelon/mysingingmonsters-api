package com.glisterbyte.SingingMonsters;

import com.glisterbyte.Localization.Language;
import com.glisterbyte.Localization.LocalizedResources;
import com.glisterbyte.SingingMonsters.SfsModels.Server.SfsGene;

public class Element {

    private int elementId;
    private char codeLetter;
    private String nameCode;

    public Element(SfsGene sfsGene) {
        elementId = sfsGene.geneId;
        codeLetter = sfsGene.geneLetter.charAt(0);
        nameCode = sfsGene.geneString;
    }

    public int getElementId() {
        return elementId;
    }

    public char getCodeLetter() {
        return codeLetter;
    }

    public String getName(Language language) {
        return LocalizedResources.getText(nameCode, language);
    }

    public String getName() {
        return LocalizedResources.getText(nameCode);
    }

    // These functions are manually compiled
    // based on the results of the db_gene
    // API command.

    public static Element air() {
        return Cache.getElementByLetter('A');
    }

    public static Element plant() {
        return Cache.getElementByLetter('B');
    }

    public static Element earth() {
        return Cache.getElementByLetter('C');
    }

    public static Element water() {
        return Cache.getElementByLetter('D');
    }

    public static Element cold() {
        return Cache.getElementByLetter('E');
    }

    // I don't know the difference between these two.

    public static Element electric1() {
        return Cache.getElementByLetter('F');
    }

    public static Element electric2() {
        return Cache.getElementByLetter('U');
    }

    public static Element plasma() {
        return Cache.getElementByLetter('G');
    }

    public static Element mech() {
        return Cache.getElementByLetter('K');
    }

    public static Element shadow() {
        return Cache.getElementByLetter('J');
    }

    public static Element crystal() {
        return Cache.getElementByLetter('L');
    }

    public static Element poison() {
        return Cache.getElementByLetter('M');
    }

    // I don't know the difference between these two either.

    public static Element legendary1() {
        return Cache.getElementByLetter('Z');
    }

    public static Element legendary2() {
        return Cache.getElementByLetter('O');
    }

    public static Element fire() {
        return Cache.getElementByLetter('N');
    }

    public static Element dipster() {
        return Cache.getElementByLetter('Q');
    }

    public static Element celestial() {
        return Cache.getElementByLetter('T');
    }

    public static Element mythical() {
        return Cache.getElementByLetter('P');
    }

    public static Element psychic() {
        return Cache.getElementByLetter('R');
    }

    public static Element fairy() {
        return Cache.getElementByLetter('Y');
    }

    public static Element bone() {
        return Cache.getElementByLetter('V');
    }

    public static Element light() {
        return Cache.getElementByLetter('W');
    }

    public static Element dream() {
        return Cache.getElementByLetter('H');
    }

    public static Element paironormal() {
        return Cache.getElementByLetter('I');
    }

}