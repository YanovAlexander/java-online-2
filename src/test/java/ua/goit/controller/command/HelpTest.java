package ua.goit.controller.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.goit.view.View;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class HelpTest {

    @Mock
    private View view;

    private Help command;

    @BeforeEach
    void init() {
        command = new Help(view);
    }

    @Test
    void testHelpCanProcessShouldReturnTrue() {
        //when
        boolean result = command.canProcess("help");

        //then
        assertTrue(result);
    }

    @Test
    void testHelpCanProcessShouldReturnFalse() {
        //when
        boolean result = command.canProcess("help121212");

        //then
        assertFalse(result);
    }

    @Test
    void testProcessShouldCompleteWithoutError() {
        //when
        command.process();

        //then
        verify(view, times(6)).write(any());
        verify(view).write("Enter help to see available commands.");
        verify(view).write("Enter exit to exit.");
        verify(view).write("Enter addBook to add book to library.");
        verify(view).write("Enter addJournal to add journal to library.");
        verify(view).write("Enter addAuthor to add author to library.");
        verify(view).write("Enter findAll to view all publications.");
    }
}