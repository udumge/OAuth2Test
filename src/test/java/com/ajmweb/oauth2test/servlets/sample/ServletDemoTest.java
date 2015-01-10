package com.ajmweb.oauth2test.servlets.sample;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
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
public class ServletDemoTest {
    
    HttpServletRequest mockRequest = mock(HttpServletRequest.class);
    HttpServletResponse mockResponse = mock(HttpServletResponse.class);
    
    public ServletDemoTest() {
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
     * Test of processRequest method, of class ServletDemo.
     */
    @Test
    public void testProcessRequest() throws Exception {
        System.out.println("processRequest");
        ServletDemo instance = new ServletDemo();
        instance.processRequest(mockRequest, mockResponse);
        assertSame(0,mockResponse.getBufferSize());
    }

    /**
     * Test of doGet method, of class ServletDemo.
     */
    @Test
    public void testDoGet() throws Exception {
        System.out.println("doGet");
        ServletDemo instance = new ServletDemo();
        instance.doGet(mockRequest, mockResponse);
        assertSame(0, mockResponse.getBufferSize());
    }

    /**
     * Test of doPost method, of class ServletDemo.
     */
    @Test
    public void testDoPost() throws Exception {
        System.out.println("doPost");
        ServletDemo instance = new ServletDemo();
        instance.doPost(mockRequest, mockResponse);
        assertSame(0, mockResponse.getBufferSize());
    }

    /**
     * Test of getServletInfo method, of class ServletDemo.
     */
    @Test
    public void testGetServletInfo() {
        System.out.println("getServletInfo");
        ServletDemo instance = new ServletDemo();
        String expResult = "This is Servelet Sample";
        String result = instance.getServletInfo();
        assertEquals(expResult, result);
    }
}
