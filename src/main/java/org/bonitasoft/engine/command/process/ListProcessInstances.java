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
import org.bonitasoft.engine.bpm.process.ProcessDeploymentInfo;
import org.bonitasoft.engine.exception.BonitaException;
import org.bonitasoft.engine.search.SearchOptionsBuilder;
import org.bonitasoft.engine.search.SearchResult;

/**
 * @author Baptiste Mesta
 */
public class ListProcessInstances extends ProcessCommand {

    @Override
    protected void executeTenantBusiness(final List<String> args, final BonitaShellContext context) throws BonitaException {
        final SearchResult<ProcessDeploymentInfo> searchProcessDeploymentInfos = getProcessAPI().searchProcessDeploymentInfos(
                new SearchOptionsBuilder(0, 100).done());
        for (final ProcessDeploymentInfo processDefinition : searchProcessDeploymentInfos.getResult()) {
            System.out.println(processDefinition.getName() + " -- " + processDefinition.getVersion() + "  activation state="
                    + processDefinition.getActivationState() + "  configuration state=" + processDefinition.getConfigurationState());
        }
    }

    @Override
    public String getName() {
        return "list-processes-definition";
    }

    @Override
    public void printHelp() {
    }

    @Override
    public boolean validate(final List<String> args) {
        return false;
    }

}
