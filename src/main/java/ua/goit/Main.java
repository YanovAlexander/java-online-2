package ua.goit;

public class Main {
    public static void main(String[] args) {
        Storage dataStorage = new Storage(1);
        dataStorage.add(new Journal("PCgaming", 100, 1, 2021));
        Journal pc2 = new Journal("PC2", 100, 1, 2021);
        dataStorage.add(pc2);
        dataStorage.add(new Journal("PC3", 100, 1, 2021));

        print(dataStorage.findAll());
        System.out.println();

        Publication publication2 = dataStorage.remove(pc2);
        Publication[] publications = dataStorage.findAll();
        print(publications);
        System.out.println("Publication removed by value:");
        System.out.println(publication2.print());
        System.out.println();

        Publication publication = dataStorage.remove(0);
        Publication[] publications1 = dataStorage.findAll();
        print(publications1);
        System.out.println("Publication removed by index:");
        System.out.println(publication.print());

    }

    private static void print(Publication[] publications) {
        for (Publication publication :
                publications) {
            if (publication != null) {
                System.out.println(publication.print());
            }
        }
    }


}
