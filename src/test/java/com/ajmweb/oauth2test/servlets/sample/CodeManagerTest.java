package com.ajmweb.oauth2test.servlets.sample;

import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CodeManagerTest {

    OAuthTokenRequest oauthTokenRequestMock = mock(OAuthTokenRequest.class);

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception{

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testIssueCode() throws Exception {

        CodeManager cm = new CodeManager();

        assertEquals("TESTCODE", cm.issueCode("AAA"));

    }

    @Test
    public void testVerifyCode() throws Exception {

        when(oauthTokenRequestMock.getClientId()).thenReturn("AAA");
        when(oauthTokenRequestMock.getCode()).thenReturn("TESTCODE");

        CodeManager cm = new CodeManager();

        cm.verifyCode(oauthTokenRequestMock);
    }

    @Test
    public void testVerifyCodeNG1() throws Exception{

        thrown.expect(OAuthProblemException.class);
        thrown.expectMessage("Not Registed or Expired");

        when(oauthTokenRequestMock.getClientId()).thenReturn("AAA");
        when(oauthTokenRequestMock.getCode()).thenReturn("DUMMYCODE");

        CodeManager cm = new CodeManager();

        cm.verifyCode(oauthTokenRequestMock);
    }

    @Test
    public void testVerifyCodeNG2() throws Exception{

        thrown.expect(OAuthProblemException.class);
        thrown.expectMessage("Not Registed or Expired");

        when(oauthTokenRequestMock.getClientId()).thenReturn("CCC");
        when(oauthTokenRequestMock.getCode()).thenReturn("TESTCODE");

        CodeManager cm = new CodeManager();

        cm.verifyCode(oauthTokenRequestMock);
    }
}