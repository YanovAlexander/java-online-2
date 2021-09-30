package ua.goit;

public class Publication {
    private String name;
    private int countPages;

    public Publication(String name, int countPages) {
        this.name = name;
        this.countPages = countPages;
    }

    public String print() {
        return "name = " + this.name + ", countPages = " + this.countPages;
    }
}