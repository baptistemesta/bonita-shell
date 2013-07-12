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
package org.bonitasoft.engine.command.identity;

import java.util.List;

import org.bonitasoft.engine.BonitaShellContext;
import org.bonitasoft.engine.exception.BonitaException;
import org.bonitasoft.engine.identity.Group;
import org.bonitasoft.engine.identity.GroupCreator;

/**
 * @author Baptiste Mesta
 */
public class CreateGroupCommand extends IdentityCommand {

    @Override
    protected void executeTenantBusiness(final List<String> args, final BonitaShellContext context) throws BonitaException {
        final GroupCreator createNewInstance = new GroupCreator(args.get(0));
        if (args.size() == 2) {
            createNewInstance.setParentPath(args.get(1));
        }
        final Group group = getIdentityApi().createGroup(createNewInstance);
        System.out.println("created group with id=" + group.getId());
    }

    @Override
    public String getName() {
        return "create-group";
    }

    @Override
    public void printHelp() {
        System.out.println("Usage: create-group group_name [group_path]");
    }

    @Override
    public boolean validate(final List<String> args) {
        return args.size() == 1 || args.size() == 2;
    }

}
