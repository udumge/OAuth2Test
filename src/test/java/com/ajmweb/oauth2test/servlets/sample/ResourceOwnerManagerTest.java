package com.ajmweb.oauth2test.servlets.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ResourceOwnerManagerTest {

    HttpServletRequest mockRequest = mock(HttpServletRequest.class);

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testVerifyResourceOwner() throws Exception {

        ResourceOwnerManager rom = new ResourceOwnerManager();

        rom.verifyResourceOwner(mockRequest);
    }
}