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

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.bonitasoft.engine.BonitaShellContext;
import org.bonitasoft.engine.exception.BonitaException;

/**
 * @author Baptiste Mesta
 */
public class DeployOrganisationCommand extends IdentityCommand {

    @Override
    protected void executeTenantBusiness(final List<String> args, final BonitaShellContext context) throws BonitaException, IOException {
        String orga = FileUtils.readFileToString(new File(args.get(0)));
        getIdentityApi().importOrganization(orga);
        System.out.println("organization imported");
    }

    @Override
    public String getName() {
        return "deploy-orga";
    }

    @Override
    public void printHelp() {
        System.out.println("Usage: deploy-orga <path to orga xml file>");
    }

    @Override
    public boolean validate(final List<String> args) {
        return args.size() == 1;
    }

}
