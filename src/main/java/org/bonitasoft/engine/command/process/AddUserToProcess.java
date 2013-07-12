/**
 * Copyright (C) 2013 BonitaSoft S.A.
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
package org.bonitasoft.engine.command.process;

import java.util.List;

import org.bonitasoft.engine.BonitaShellContext;
import org.bonitasoft.engine.bpm.process.ProcessDefinition;
import org.bonitasoft.engine.exception.BonitaException;
import org.bonitasoft.engine.identity.User;

/**
 * @author Baptiste Mesta
 */
public class AddUserToProcess extends ProcessCommand {

    @Override
    protected void executeTenantBusiness(final List<String> args, final BonitaShellContext context) throws BonitaException {
        ProcessDefinition processDefinition = getProcessAPI().getProcessDefinition(getProcessAPI().getProcessDefinitionId(args.get(0), args.get(1)));
        User user = context.getIdentityAPI().getUserByUserName(args.get(2));
        getProcessAPI().addUserToActor(args.get(3), processDefinition, user.getId());
    }

    @Override
    public String getName() {
        return "add-user-to-actor";
    }

    @Override
    public void printHelp() {
        System.out.println("Usage: enable <process name> <process version> <actor name> <user name>");
    }

    @Override
    public boolean validate(final List<String> args) {
        return args.size() == 4;
    }
}
