package ua.goit;

public class Main {
  public static void main(String[] args) {
    Library library = new Library();
    library.printPublication(null);
    library.printPublication(new Publication[0]);
    library.printPublication(new Publication[]{new Journal("PC", 100, 1, 2021)});
    library.printPublication(new Publication[]{new Book("Witcher", 200, "Andrzej Sapkowski")});
    library.printPublication(new Publication[]{new Book("Master & Margarita", 300, "Mikhail Bulgakov"),
                                        new Journal("Gamer", 101, 10, 2018)});
  }
}
