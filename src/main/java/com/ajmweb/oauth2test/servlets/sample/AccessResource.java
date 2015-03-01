package com.ajmweb.oauth2test.servlets.sample;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * access Resource
 * Created by udumbara on 2015/03/01.
 */
@WebServlet(name = "AccessResource")
public class AccessResource extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // Tokenの有効性を確認する
        TokenManager tm = new TokenManager();

        tm.checkAccessToken(request);

        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter pw = response.getWriter();
        pw.print("{\"UserData\" : \"Accept\"}");
        pw.flush();
        pw.close();
    }

    @Override
    public String getServletInfo() {
        return "Resource Access with Access Token";
    }
}
