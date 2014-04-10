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

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bonitasoft.engine.BonitaShellContext;

/**
 * @author Baptiste Mesta
 */
public abstract class BonitaCommand extends ShellCommand<BonitaShellContext> {

    public static int BATCH_SIZE = 20;

    private static Logger logger = Logger.getLogger(BonitaCommand.class.getName());

    protected abstract void executeBusiness(List<String> args, BonitaShellContext context) throws Exception;

    @Override
    public boolean execute(final List<String> args, final BonitaShellContext context) throws Exception {
        if (context.isLogged()) {
            try {
                executeBusiness(args, context);
            } catch (final Exception e) {
                logger.log(Level.SEVERE, "Error executing the command", e);
                return false;
            }
        } else {
            System.out.println("Must be logged to the tenant!");
            return false;
        }
        return true;
    }

}
