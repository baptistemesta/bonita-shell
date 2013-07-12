/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This library is free software; you can redistribute it and/or modify it under the terms
 * of the GNU Lesser General Public License as published by the Free Software Foundation
 * version 2.1 of the License.
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License along with this
 * program; if not, write to the Free Software Foundation, Inc., 51 Franklin Street, Fifth
 * Floor, Boston, MA 02110-1301, USA.
 **/
package org.bonitasoft.engine.command;

import java.util.Arrays;
import java.util.List;

import jline.console.completer.Completer;
import jline.console.completer.StringsCompleter;

import org.bonitasoft.engine.BonitaShellContext;

/**
 * @author Baptiste Mesta
 */
public class LoginCommand extends ShellCommand<BonitaShellContext> {

    @Override
    public boolean execute(final List<String> args, final BonitaShellContext context) throws Exception {
        if (!context.isLogged()) {
            context.login(args.get(0), args.get(1));
            return true;
        } else {
            System.out.println("Already logged to the tenant!");
        }
        return false;
    }

    @Override
    public void printHelp() {
        System.out.println("Usage: login <username> <password>");
    }

    @Override
    public boolean validate(final List<String> args) {
        return args.size() == 2;
    }

    @Override
    public List<Completer> getCompleters() {
        return Arrays.asList((Completer) new StringsCompleter("platform", "tenant"));
    }

    @Override
    public String getName() {
        return "login";
    }

}
