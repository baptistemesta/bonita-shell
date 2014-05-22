package org.bonitasoft.engine.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.bonitasoft.engine.BonitaShellContext;
import org.bonitasoft.shell.command.LoginCommand;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LoginCommandTest {

    @Mock
    private BonitaShellContext context;

    private LoginCommand loginCommand;

    @Before
    public void setup() {
        loginCommand = new LoginCommand();
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals("login", loginCommand.getName());
    }

    @Test
    public void testPrintHelp() throws Exception {
        loginCommand.printHelp();
    }

    @Test
    public void testValidate() throws Exception {
        assertFalse(loginCommand.validate(Arrays.asList("john")));
        assertFalse(loginCommand.validate(Arrays.asList("john", "jack", "james")));
        assertTrue(loginCommand.validate(Arrays.asList("john", "bpm")));
    }

    @Test
    public void testExecute() throws Exception {
        loginCommand.execute(Arrays.asList("john", "bpm"), context);
        verify(context).login("john", "bpm");
    }

    @Test
    public void testExecuteWhenAlreadyLogged() throws Exception {
        when(context.isLogged()).thenReturn(true);
        loginCommand.execute(Arrays.asList("john", "bpm"), context);
        verify(context, times(1)).isLogged();
        verify(context, times(0)).login("john", "bpm");
    }

}
