package org.bonitasoft.engine;

import java.util.Arrays;
import java.util.List;

import org.bonitasoft.engine.command.LoginCommand;
import org.bonitasoft.engine.command.LogoutCommand;
import org.bonitasoft.engine.command.ShellCommand;
import org.bonitasoft.engine.command.identity.CreateGroupCommand;
import org.bonitasoft.engine.command.identity.CreateRoleCommand;
import org.bonitasoft.engine.command.identity.CreateUserCommand;
import org.bonitasoft.engine.command.identity.ListUserCommand;
import org.bonitasoft.engine.command.process.AddUserToProcess;
import org.bonitasoft.engine.command.process.DeployBARCommand;
import org.bonitasoft.engine.command.process.EnableProcess;
import org.bonitasoft.engine.command.process.ListProcessDefinitions;
import org.bonitasoft.engine.command.process.StartProcessCommand;

public class BonitaShell extends BaseShell<BonitaShellContext> {

    public BonitaShell() {
    }

    public static void main(final String[] args) throws Exception {
        final BaseShell<BonitaShellContext> shell = new BonitaShell();
        shell.init();
        shell.run(System.in, System.out);
        shell.destroy();
    }

    @Override
    protected BonitaShellContext getContext() {
        return BonitaShellContext.getInstance();
    }

    private ShellCommand<BonitaShellContext> createCommand(final Class<?> clazz) throws InstantiationException, IllegalAccessException {
        return (ShellCommand<BonitaShellContext>) clazz.newInstance();
    }

    @Override
    protected List<ShellCommand<BonitaShellContext>> initShellCommands() throws Exception {
        return Arrays.asList(createCommand(LoginCommand.class), createCommand(LogoutCommand.class), createCommand(CreateGroupCommand.class),
                createCommand(CreateUserCommand.class), createCommand(CreateRoleCommand.class), createCommand(ListUserCommand.class),
                createCommand(DeployBARCommand.class), createCommand(ListProcessDefinitions.class), createCommand(StartProcessCommand.class),
                createCommand(EnableProcess.class), createCommand(AddUserToProcess.class));
    }
}
