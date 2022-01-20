package ua.goit.it;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.goit.Application;
import ua.goit.config.ConfigurableInputStream;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AddAuthorTest {

    private ConfigurableInputStream is;
    private ByteArrayOutputStream os;

    @BeforeEach
    void init() {
        is = new ConfigurableInputStream();
        os = new ByteArrayOutputStream();
        System.setIn(is);
        System.setOut(new PrintStream(os));
    }

    @Test
    void testAddAuthorWithCorrectParameters() {
        //given
        String name = "Alex";
        String lastName = "Yanov";
        String email = "alex.yanov@mail.com";

        is.add("addAuthor");
        is.add(name);
        is.add(lastName);
        is.add(email);
        is.add("exit");

        //when
        Application.main(new String[0]);

        //then
        assertEquals("Welcome to Library\n" +
                "Please, enter help to see available commands:\n" +
                "Enter author first name:\n" +
                "Enter author last name:\n" +
                "Enter author email:\n" +
                "Author added to library\n" +
                "Please, enter help to see available commands:\n" +
                "Good Bye!\n", getResult());
    }

    public String getResult() {
        try {
            String result = new String(os.toByteArray(), "UTF-8");
            os.reset();
            return result;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
