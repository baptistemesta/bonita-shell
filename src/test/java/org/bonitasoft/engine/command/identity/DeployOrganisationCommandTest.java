package org.bonitasoft.engine.command.identity;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import org.bonitasoft.engine.api.IdentityAPI;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DeployOrganisationCommandTest {

    @Mock
    private IdentityAPI identityAPI;

    private DeployOrganisationCommand deployOrganisationCommand;

    @Before
    public void before() {
        deployOrganisationCommand = spy(new DeployOrganisationCommand());
        doReturn(identityAPI).when(deployOrganisationCommand).getIdentityApi();
    }

    @Test
    public void testExecuteTenantBusiness() throws Exception {
        // BonitaShellContext instance = BonitaShellContext.getInstance();
        // deployOrganisationCommand.executeTenantBusiness(Arrays.asList("filepath"), instance);
    }

}
