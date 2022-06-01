package ua.goit.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ua.goit.model.dao.AuthorDao;
import ua.goit.model.dao.BookDao;
import ua.goit.repository.BookRepository;

import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@ActiveProfiles({"test"})
class BookControllerTest {

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private BookRepository bookRepository;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(roles = "VISITOR")
    void testGetBookFormShouldReturnBookForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books/form/find"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("findBookForm"));
    }

    @Test
    void testGetBookFormWithIncorrectUserShouldReturnHttpStatus302AndRedirectToLoginPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books/form/find"))
                .andExpect(MockMvcResultMatchers.status().is(302))
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/login"));
    }

    @Test
    @WithMockUser(roles = "VISITOR")
    void testFindBookByNameShouldReturnBook() throws Exception {
        BookDao book = prepareBook();
        when(bookRepository.findByName("book1")).thenReturn(List.of(book));

        mockMvc.perform(MockMvcRequestBuilders.get("/books/name/?name=book1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("findBook"))
                .andExpect(MockMvcResultMatchers.model().attribute("books", hasSize(1)))
                .andExpect(MockMvcResultMatchers.model().attribute("books", hasItem(allOf(
                        hasProperty("name", equalTo(book.getName())),
                        hasProperty("countPages", equalTo(book.getCountPages())),
                        hasProperty("id", equalTo(book.getId()))
                ))));
    }

    private BookDao prepareBook() {
        BookDao book = new BookDao();
        book.setId(1);
        book.setName("Book1");
        book.setAuthors(Set.of(prepareAuthor()));
        book.setCountPages(100);
        return book;
    }

    private AuthorDao prepareAuthor() {
        AuthorDao author = new AuthorDao();
        author.setId(1);
        author.setFirstName("Oleksandr");
        author.setLastName("Yanov");
        author.setEmail("oleksandr1@gmail.com");
        return author;
    }
}