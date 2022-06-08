package ua.goit.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ua.goit.Books;
import ua.goit.model.dao.BookDao;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource({"classpath:application-test.properties"})
@ActiveProfiles("test")
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void findByNameShouldReturnBook() {
        //given
        prepareBook();
        //when
        List<BookDao> books = bookRepository.findByName("book1");
        //then
        assertThat(books.size(), equalTo(1));
        BookDao book = books.stream().filter(b -> b.getId().equals(1)).findFirst().get();
        assertThat(book.getName(), is("book1"));
        assertThat(book.getId(), is(1));
        assertThat(book.getCountPages(), is(100));
        assertThat(book.getAuthors().size(), is(2));
    }

    private void prepareBook() {
        List<BookDao> books = Books.prepareBooksForDb();
        books.forEach(book -> {
            entityManager.persist(book);
            book.getAuthors()
                    .forEach(entityManager::persist);
        });
        entityManager.flush();
    }
}
