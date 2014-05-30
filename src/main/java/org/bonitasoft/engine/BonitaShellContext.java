package org.bonitasoft.engine;

import org.bonitasoft.engine.api.IdentityAPI;
import org.bonitasoft.engine.api.LoginAPI;
import org.bonitasoft.engine.api.ProcessAPI;
import org.bonitasoft.engine.api.ProfileAPI;
import org.bonitasoft.engine.api.TenantAPIAccessor;
import org.bonitasoft.engine.exception.BonitaException;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.shell.ShellContext;

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
    @Override
    public boolean isLogged() {
        return session != null;
    }

    @Override
    public void logout() throws Exception {
        loginAPI.logout(session);
        loginAPI = null;
        session = null;
    }

    /**
     * @param tenant
     * @param username
     * @param password
     */
    @Override
    public void login(final String username, final String password) throws Exception {
        loginAPI = TenantAPIAccessor.getLoginAPI();
        session = loginAPI.login(username, password);
    }

    public IdentityAPI getIdentityAPI() throws BonitaException {
        return TenantAPIAccessor.getIdentityAPI(session);

    }

    public ProcessAPI getProcessAPI() throws BonitaException {
        return TenantAPIAccessor.getProcessAPI(session);
    }
    
    public ProfileAPI getProfileAPI() throws BonitaException {
    	return TenantAPIAccessor.getProfileAPI(session);
    }

    public APISession getSession() {
        return session;
    }

    @Override
    public Object getApi(final String apiName) throws Exception {
        if (apiName.equals("process")) {
            return getProcessAPI();
        }
        if (apiName.equals("identity")) {
            return getIdentityAPI();
        }
        if (apiName.equals("profile")) {
        	return getProfileAPI();
        }
        throw new IllegalArgumentException("Unknown API: " + apiName);
    }

}
