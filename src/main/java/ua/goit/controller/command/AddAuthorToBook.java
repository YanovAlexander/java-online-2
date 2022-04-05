package ua.goit.controller.command;

import ua.goit.model.dto.AuthorDto;
import ua.goit.model.dto.BookDto;
import ua.goit.service.AuthorService;
import ua.goit.service.BookService;
import ua.goit.view.View;

import java.util.Optional;

import static ua.goit.controller.command.Commands.ADD_AUTHOR_TO_BOOK;

public class AddAuthorToBook implements Command {

    private final View view;
    private final AuthorService authorService;
    private final BookService bookService;

    public AddAuthorToBook(View view, AuthorService authorService, BookService bookService) {
        this.view = view;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public boolean canProcess(String input) {
        return input.equals(ADD_AUTHOR_TO_BOOK.getName());
    }

    @Override
    public void process() {
      AuthorDto author = findAuthorByEmail();
      BookDto book = findBookByName();

      bookService.addAuthorToBook(book, author);
      view.write("Author added to book");
    }

    private BookDto findBookByName() {
        String name = null;
        boolean isBookExists = false;
        Optional<BookDto> book = Optional.empty();

        view.write("Enter book name:");

        while (!isBookExists) {
            String bookName = view.read();
//            book = bookService.findBookByName(bookName);
            if (book.isPresent()) {
                isBookExists = true;
            } else {
                view.write("Incorrect book name. Please enter book name again");
            }
        }

        return book.get();
    }

    private AuthorDto findAuthorByEmail() {
        String email = null;
        boolean isAuthorExists = false;

        view.write("Enter author email:");

        while (!isAuthorExists) {
            email = view.read();
            isAuthorExists = authorService.isAuthorExistByEmail(email);
            if (!isAuthorExists) {
                view.write("Incorrect author email. Please enter author email again:");
            }
        }

        return authorService.findByEmail(email);
    }
}
