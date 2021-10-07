package ua.goit;

public class Library {
    public void printPublications(Publication[] publications) {
        if (publications == null || publications.length == 0) {
            System.out.println("No publications");
            return;
        }

        for (Publication publication : publications) {
            System.out.println(publication.print());
        }
    }
}
