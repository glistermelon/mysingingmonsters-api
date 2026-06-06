package com.glisterbyte.singingmonsters.main.catalog;

public class ElementCatalog {

    private final Catalog catalog;

    public ElementCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    // These functions are manually compiled based on the results of the db_gene API command.

    public Element air() {
        return catalog.getElement('A');
    }

    public Element plant() {
        return catalog.getElement('B');
    }

    public Element earth() {
        return catalog.getElement('C');
    }

    public Element water() {
        return catalog.getElement('D');
    }

    public Element cold() {
        return catalog.getElement('E');
    }

    // I don't know the difference between these two.

    public Element electric1() {
        return catalog.getElement('F');
    }

    public Element electric2() {
        return catalog.getElement('U');
    }

    public Element plasma() {
        return catalog.getElement('G');
    }

    public Element mech() {
        return catalog.getElement('K');
    }

    public Element shadow() {
        return catalog.getElement('J');
    }

    public Element crystal() {
        return catalog.getElement('L');
    }

    public Element poison() {
        return catalog.getElement('M');
    }

    // I don't know the difference between these two either.

    public Element legendary1() {
        return catalog.getElement('Z');
    }

    public Element legendary2() {
        return catalog.getElement('O');
    }

    public Element fire() {
        return catalog.getElement('N');
    }

    public Element dipster() {
        return catalog.getElement('Q');
    }

    public Element celestial() {
        return catalog.getElement('T');
    }

    public Element mythical() {
        return catalog.getElement('P');
    }

    public Element psychic() {
        return catalog.getElement('R');
    }

    public Element fairy() {
        return catalog.getElement('Y');
    }

    public Element bone() {
        return catalog.getElement('V');
    }

    public Element light() {
        return catalog.getElement('W');
    }

    public Element dream() {
        return catalog.getElement('H');
    }

    public Element paironormal() {
        return catalog.getElement('I');
    }

}