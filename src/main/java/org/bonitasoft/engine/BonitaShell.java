package org.bonitasoft.engine;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.engine.api.IdentityAPI;
import org.bonitasoft.engine.api.ProcessAPI;
import org.bonitasoft.shell.BaseShell;
import org.bonitasoft.shell.command.LoginCommand;
import org.bonitasoft.shell.command.LogoutCommand;
import org.bonitasoft.shell.command.ReflectCommand;
import org.bonitasoft.shell.command.ShellCommand;

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

    @Override
    protected List<ShellCommand<BonitaShellContext>> initShellCommands() throws Exception {
        ArrayList<ShellCommand<BonitaShellContext>> commands = new ArrayList<ShellCommand<BonitaShellContext>>(4);
        commands.add(new LoginCommand<BonitaShellContext>());
        commands.add(new LogoutCommand<BonitaShellContext>());
        commands.add(new ReflectCommand<BonitaShellContext>("process", ProcessAPI.class));
        commands.add(new ReflectCommand<BonitaShellContext>("identity", IdentityAPI.class));
        return commands;
        // return Arrays.asList(createCommand(LoginCommand.class), createCommand(LogoutCommand.class), createCommand(DeployOrganisationCommand.class),
        // createCommand(CreateGroupCommand.class),
        // createCommand(CreateUserCommand.class), createCommand(CreateRoleCommand.class), createCommand(ListUserCommand.class),
        // createCommand(DeployBARCommand.class), createCommand(ListProcessDefinitions.class), createCommand(StartProcessCommand.class),
        // createCommand(EnableProcess.class), createCommand(AddUserToProcess.class));
    }

}
