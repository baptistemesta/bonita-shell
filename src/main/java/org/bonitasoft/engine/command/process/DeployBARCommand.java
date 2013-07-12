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

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import jline.console.completer.Completer;
import jline.console.completer.FileNameCompleter;

import org.bonitasoft.engine.BonitaShellContext;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveFactory;
import org.bonitasoft.engine.bpm.bar.InvalidBusinessArchiveFormatException;
import org.bonitasoft.engine.bpm.process.ProcessDefinition;
import org.bonitasoft.engine.exception.BonitaException;

/**
 * @author Baptiste Mesta
 */
public class DeployBARCommand extends ProcessCommand {

    @Override
    protected void executeTenantBusiness(final List<String> args, final BonitaShellContext context) throws BonitaException {
        try {
            final ProcessDefinition deploy = getProcessAPI().deploy(BusinessArchiveFactory.readBusinessArchive(new File(args.get(0))));
            System.out.println("Deployed process " + deploy.getName() + " in version " + deploy.getVersion() + ", id=" + deploy.getId());
            if (!args.contains("-n")) {
                getProcessAPI().enableProcess(deploy.getId());
                System.out.println("Enabled process");
            }
        } catch (final IOException e) {
            throw new InvalidBusinessArchiveFormatException(e);
        }
    }

    @Override
    public String getName() {
        return "deploy";
    }

    @Override
    public void printHelp() {
        System.out.println("Usage: deploy <path to bar file> [-n]");
    }

    @Override
    public boolean validate(final List<String> args) {
        if (args.size() >= 1) {
            final File file = new File(args.get(0));
            final boolean b = file.exists() && file.isFile();
            if (!b) {
                System.out.println("file " + file.getAbsolutePath() + " does not exists or is a folder");
            }
            return b;
        }
        return false;
    }

    @Override
    public List<Completer> getCompleters() {
        return Arrays.asList((Completer) new FileNameCompleter());
    }
}
