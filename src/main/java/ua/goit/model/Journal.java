package ua.goit.model;

public class Journal extends Publication {
    private int number;
    private int publicationYear;

    public Journal(String name, int countPages, int number, int publicationYear) {
        super(name, countPages);
        this.number = number;
        this.publicationYear = publicationYear;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    @Override
    public String toString() {
        return "Journal{" + super.toString() + ", number = " + this.number + ", year = " + this.publicationYear + "}";
    }
}
