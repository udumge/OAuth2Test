package com.ajmweb.oauth2test.servlets.sample;

import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ClientMangagerTest {

    OAuthAuthzRequest mockOAuthAuthzRequest = mock(OAuthAuthzRequest.class);

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testSetupClient1() throws Exception {

        ClientMangager cm = new ClientMangager();
        cm.setupClient();

    }

    @Test
    public void testVerifyClient1() throws Exception {
        ClientMangager cm = new ClientMangager();

        when(mockOAuthAuthzRequest.getClientId()).thenReturn("AAA");
        when(mockOAuthAuthzRequest.getRedirectURI()).thenReturn("http://");

        cm.verifyClient(mockOAuthAuthzRequest);
    }

    @Test
    public void testVerifyClient2() throws Exception{

        thrown.expect(OAuthProblemException.class);
        thrown.expectMessage("Client not registration(02)");

        ClientMangager cm = new ClientMangager();

        when(mockOAuthAuthzRequest.getClientId()).thenReturn("AAA");
        when(mockOAuthAuthzRequest.getRedirectURI()).thenReturn("ftp://");

        cm.verifyClient(mockOAuthAuthzRequest);
    }

    @Test
         public void testVerifyClient3() throws Exception{

        thrown.expect(OAuthProblemException.class);
        thrown.expectMessage("Client not registration(01)");

        ClientMangager cm = new ClientMangager();

        when(mockOAuthAuthzRequest.getClientId()).thenReturn("");
        when(mockOAuthAuthzRequest.getRedirectURI()).thenReturn("http://");

        cm.verifyClient(mockOAuthAuthzRequest);
    }

    @Test
    public void testVerifyClient4() throws Exception{

        thrown.expect(OAuthProblemException.class);
        thrown.expectMessage("Client not registration(01)");

        ClientMangager cm = new ClientMangager();

        when(mockOAuthAuthzRequest.getClientId()).thenReturn(null);
        when(mockOAuthAuthzRequest.getRedirectURI()).thenReturn("http://");

        cm.verifyClient(mockOAuthAuthzRequest);
    }

    @Test
    public void testVerifyClient5() throws Exception{

        thrown.expect(OAuthProblemException.class);
        thrown.expectMessage("Client not registration(01)");

        ClientMangager cm = new ClientMangager();

        when(mockOAuthAuthzRequest.getClientId()).thenReturn("CCC");
        when(mockOAuthAuthzRequest.getRedirectURI()).thenReturn("http://");

        cm.verifyClient(mockOAuthAuthzRequest);
    }

    @Test
    public void testVerifyClient6() throws Exception{

        ClientMangager cm = new ClientMangager();

        when(mockOAuthAuthzRequest.getClientId()).thenReturn("AAA");
        when(mockOAuthAuthzRequest.getRedirectURI()).thenReturn("http://hogehoge");

        cm.verifyClient(mockOAuthAuthzRequest);
    }

    @Test
    public void testVerifyClient7() throws Exception{

        ClientMangager cm = new ClientMangager();

        when(mockOAuthAuthzRequest.getClientId()).thenReturn("AAA");
        when(mockOAuthAuthzRequest.getRedirectURI()).thenReturn("https://hogehoge");

        cm.verifyClient(mockOAuthAuthzRequest);
    }

    @Test
    public void testVerifyClient8() throws Exception{

        ClientMangager cm = new ClientMangager();

        when(mockOAuthAuthzRequest.getClientId()).thenReturn("AAA");
        when(mockOAuthAuthzRequest.getRedirectURI()).thenReturn("https://");

        cm.verifyClient(mockOAuthAuthzRequest);
    }

    @Test
    public void testVerifyClient9() throws Exception{

        thrown.expect(OAuthProblemException.class);
        thrown.expectMessage("Client not registration(02)");

        ClientMangager cm = new ClientMangager();

        when(mockOAuthAuthzRequest.getClientId()).thenReturn("AAA");
        when(mockOAuthAuthzRequest.getRedirectURI()).thenReturn("http:/");

        cm.verifyClient(mockOAuthAuthzRequest);
    }

}