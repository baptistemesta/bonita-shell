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
import org.bonitasoft.engine.exception.BonitaException;

/**
 * @author Baptiste Mesta
 */
public class EnableProcess extends ProcessCommand {

    @Override
    protected void executeTenantBusiness(final List<String> args, final BonitaShellContext context) throws BonitaException {
        if (args.size() == 1) {
            getProcessAPI().enableProcess(Long.parseLong(args.get(0)));
        } else {
            getProcessAPI().enableProcess(getProcessAPI().getProcessDefinitionId(args.get(0), args.get(1)));
        }
    }

    @Override
    public String getName() {
        return "enable";
    }

    @Override
    public void printHelp() {
        System.out.println("Usage: enable <processID>|<process name> <process version>");
    }

    @Override
    public boolean validate(final List<String> args) {
        if (args.size() == 0) {
            return false;
        }
        boolean args0IsNumber = false;
        try {
            Long.parseLong(args.get(0));
            args0IsNumber = true;
        } catch (final NumberFormatException e) {
        }
        return args.size() == 1 && args0IsNumber || args.size() == 2;
    }
}
