package ua.goit;

public class Main {
    public static void main(String[] args) {
        Storage dataStorage = new Storage(1);
        dataStorage.add(new Journal("PC", 100, 1, 2021));
        dataStorage.add(new Journal("PC", 100, 1, 2021));
        dataStorage.add(new Journal("PC", 100, 1, 2021));
    }


}
