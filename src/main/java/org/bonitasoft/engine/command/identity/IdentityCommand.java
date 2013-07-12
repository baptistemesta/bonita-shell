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
import org.bonitasoft.engine.api.IdentityAPI;
import org.bonitasoft.engine.command.BonitaCommand;
import org.bonitasoft.engine.exception.BonitaException;

/**
 * @author Baptiste Mesta
 */
public abstract class IdentityCommand extends BonitaCommand {

    private IdentityAPI identityApi;

    @Override
    protected final void executeBusiness(final List<String> args, final BonitaShellContext context) throws BonitaException {
        identityApi = context.getIdentityAPI();
        executeTenantBusiness(args, context);
        identityApi = null;
    }

    public IdentityAPI getIdentityApi() {
        return identityApi;
    }

    protected abstract void executeTenantBusiness(List<String> args, BonitaShellContext context) throws BonitaException;

}
