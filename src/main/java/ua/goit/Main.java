package ua.goit;

public class Main {
    public static void main(String[] args) {
        Storage dataStorage = new Storage(1);
        dataStorage.add(new Journal("PCgaming", 100, 1, 2021));
        Journal pc2 = new Journal("PC2", 100, 1, 2021);
        dataStorage.add(pc2);
        dataStorage.add(new Journal("PC3", 100, 1, 2021));

        dataStorage.delete(pc2);
        Publication[] publications = dataStorage.findAll();

        for (Publication publication :
                publications) {
            if (publication != null) {
                System.out.println(publication.print());
            }
        }
    }


}
