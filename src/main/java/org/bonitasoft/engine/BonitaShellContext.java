package org.bonitasoft.engine;

import org.bonitasoft.engine.api.IdentityAPI;
import org.bonitasoft.engine.api.LoginAPI;
import org.bonitasoft.engine.api.ProcessAPI;
import org.bonitasoft.engine.api.TenantAPIAccessor;
import org.bonitasoft.engine.exception.BonitaException;
import org.bonitasoft.engine.session.APISession;

public class BonitaShellContext implements ShellContext {

    private static BonitaShellContext INSTANCE = new BonitaShellContext();

    public static BonitaShellContext getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BonitaShellContext();
        }
        return INSTANCE;
    }

    private BonitaShellContext() {
    }

    private LoginAPI loginAPI;

    private APISession session;

    // ------------------ Tenant ------------------------
    /**
     * @return
     */
    public boolean isLogged() {
        return session != null;
    }

    public void logout() throws BonitaException {
        loginAPI.logout(session);
        loginAPI = null;
        session = null;
    }

    /**
     * @param tenant
     * @param username
     * @param password
     */
    public void login(final String username, final String password) throws BonitaException {
        loginAPI = TenantAPIAccessor.getLoginAPI();
        session = loginAPI.login(username, password);
    }

    public IdentityAPI getIdentityAPI() throws BonitaException {
        return TenantAPIAccessor.getIdentityAPI(session);

    }

    public ProcessAPI getProcessAPI() throws BonitaException {
        return TenantAPIAccessor.getProcessAPI(session);
    }

    public APISession getSession() {
        return session;
    }

}
