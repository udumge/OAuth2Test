package com.ajmweb.oauth2test.servlets.sample;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.*;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author udumbara
 */
public class AuthorizationTest {
    
    HttpServletRequest mockRequestOK = mock(HttpServletRequest.class);
    HttpServletRequest mockRequestNG = mock(HttpServletRequest.class);
    HttpServletResponse mockResponse = mock(HttpServletResponse.class);

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
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

        // http://localhost:8080/Authorization?response_type=code&client_id=AAA&redirect_uri=http%3A%2F%2Flocalhost&state=state&scope=test
        when(mockRequestOK.getMethod()).thenReturn("GET");
        when(mockRequestOK.getParameter("response_type")).thenReturn("code");
        when(mockRequestOK.getParameter("client_id")).thenReturn("AAA");
        when(mockRequestOK.getParameter("redirect_uri")).thenReturn("http://localhost");
        when(mockRequestOK.getParameter("state")).thenReturn("state");
        when(mockRequestOK.getParameter("scope")).thenReturn("test");
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

        Authorization instance = new Authorization();
        instance.processRequest(mockRequestOK, mockResponse);

        //assertEquals(HttpServletResponse.SC_FOUND, mockResponse);
    }

    /**
     * Test of processRequest method, of class Authorization.
     */
    @Test
    public void testProcessRequestNG1() throws Exception {
        System.out.println("processRequest NG");

        // http://localhost:8080/Authorization?response_type=code&client_id=AAA&redirect_uri=http%3A%2F%2Flocalhost&state=state&scope=test
        // Not Found Client ID
        when(mockRequestNG.getMethod()).thenReturn("GET");
        when(mockRequestNG.getParameter("response_type")).thenReturn("code");
        //when(mockRequestOK.getParameter("client_id")).thenReturn("AAA");
        when(mockRequestNG.getParameter("redirect_uri")).thenReturn("http://localhost");
        when(mockRequestNG.getParameter("state")).thenReturn("state");
        when(mockRequestNG.getParameter("scope")).thenReturn("test");

        Authorization instance = new Authorization();
        instance.processRequest(mockRequestNG, mockResponse);

    }
    
    /**
     * Test of processRequest method, of class Authorization.
     * not found parameter 
     */
    @Test
    public void testProcessRequest1() throws Exception {
        System.out.println("processRequest");
        Authorization instance = new Authorization();
        instance.processRequest(mockRequestOK, mockResponse);
    }

    /**
     * Test of doGet method, of class Authorization.
     */
    @Test
    public void testDoGet() throws Exception {
        System.out.println("doGet");
        Authorization instance = new Authorization();
        instance.doGet(mockRequestOK, mockResponse);
    }

    /**
     * Test of doPost method, of class Authorization.
     */
    @Test
    public void testDoPost() throws Exception {
        System.out.println("doPost");
        Authorization instance = new Authorization();
        instance.doPost(mockRequestOK, mockResponse);
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
