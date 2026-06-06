package com.glisterbyte.singingmonsters.main.catalog;

import com.glisterbyte.singingmonsters.localization.Language;
import com.glisterbyte.singingmonsters.localization.LocalizedTextManager;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsGene;

public class Element {

    private final Catalog catalog;

    private final int elementId;
    private final char codeLetter;
    private final String nameCode;

    public Element(Catalog catalog, SfsGene sfsGene) {
        this.catalog = catalog;
        elementId = sfsGene.gene_id;
        codeLetter = sfsGene.gene_letter.charAt(0);
        nameCode = sfsGene.gene_string;
    }

    public int getElementId() {
        return elementId;
    }

    public char getCodeLetter() {
        return codeLetter;
    }

    public String getName(Language language) {
        return catalog.getLocalizedTextManager().getText(nameCode, language);
    }

    public String getName() {
        return catalog.getLocalizedTextManager().getText(nameCode);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Element otherElement)) return false;
        return elementId == otherElement.elementId;
    }

    @Override
    public String toString() {
        return "Element(" + getName() + ")";
    }

}