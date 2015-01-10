package com.ajmweb.oauth2test.servlets.sample;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author udumbara
 */
public class AuthorizationTest {
    
    HttpServletRequest mockRequest = mock(HttpServletRequest.class);
    HttpServletResponse mockResponse = mock(HttpServletResponse.class);
    
    public AuthorizationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws FileNotFoundException, IOException {
        PrintWriter writer = new PrintWriter("testWrite.txt");
        when(mockResponse.getWriter()).thenReturn(writer);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of processRequest method, of class Authorization.
     */
    @Test
    public void testProcessRequestOK() throws Exception {
        System.out.println("processRequest OK");
        
        when(mockRequest.getParameter("response_type")).thenReturn("code");
        when(mockRequest.getParameter("client_id")).thenReturn("AAA");
        when(mockRequest.getParameter("redirect_uri")).thenReturn("http://localhost");
        when(mockRequest.getParameter("state")).thenReturn("state");

        Authorization instance = new Authorization();
        instance.processRequest(mockRequest, mockResponse);
        
        assertEquals(HttpServletResponse.SC_FOUND, mockResponse.getStatus());

    }
    
    /**
     * Test of processRequest method, of class Authorization.
     * not found parameter 
     */
    @Test
    public void testProcessRequest1() throws Exception {
        System.out.println("processRequest");
        Authorization instance = new Authorization();
        instance.processRequest(mockRequest, mockResponse);
    }

    /**
     * Test of doGet method, of class Authorization.
     */
    @Test
    public void testDoGet() throws Exception {
        System.out.println("doGet");
        Authorization instance = new Authorization();
        instance.doGet(mockRequest, mockResponse);
    }

    /**
     * Test of doPost method, of class Authorization.
     */
    @Test
    public void testDoPost() throws Exception {
        System.out.println("doPost");
        Authorization instance = new Authorization();
        instance.doPost(mockRequest, mockResponse);
    }

    /**
     * Test of getServletInfo method, of class Authorization.
     */
    @Test
    public void testGetServletInfo() {
        System.out.println("getServletInfo");
        Authorization instance = new Authorization();
        String expResult = "OAuth2 Authorization ";
        String result = instance.getServletInfo();
        assertEquals(expResult, result);
    }
    
}
