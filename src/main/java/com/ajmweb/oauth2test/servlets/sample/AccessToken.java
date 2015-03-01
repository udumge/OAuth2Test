package com.ajmweb.oauth2test.servlets.sample;

import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuer;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * issue Access Token
 * Created by udumbara on 2015/02/21.
 */
@WebServlet(name = "AccessToken")
public class AccessToken extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request,response);
        } catch (OAuthSystemException e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request,response);
        } catch (OAuthSystemException e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws OAuthSystemException, IOException {

        OAuthTokenRequest oauthRequest = null;
        OAuthIssuer oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());

        try {
            oauthRequest = new OAuthTokenRequest(request);

            // クライアントの認証をしなければならない
            // Basic認証の方式は必須、それ以外にボディのパラメータとして渡しても良い
            validateClient(oauthRequest);

            // コードが対象のクライアントに発行されたものであることを確認しなければならない
            validateCode(oauthRequest);

            // オッケーだったらアクセストークン、リフレッシュトークンを発行する
            // 当然だが、これも後ほどチェックが入るので必要なことは記録が必要

            String accessToken = oauthIssuerImpl.accessToken();
            String refreshToken = oauthIssuerImpl.refreshToken();

            OAuthResponse oAuthResponse = OAuthASResponse
                    .tokenResponse(HttpServletResponse.SC_OK)
                    .setAccessToken(accessToken)
                    .setRefreshToken(refreshToken)
                    .setExpiresIn("3600")
                    .buildJSONMessage();

            response.setStatus(oAuthResponse.getResponseStatus());
            PrintWriter pw = response.getWriter();
            pw.print(oAuthResponse.getBody());
            pw.flush();
            pw.close();

        } catch (OAuthProblemException e) {

            // RFCには401と400を見分けて応答することが記載されている
            // このサンプルは手抜き
            OAuthResponse oAuthResponse = OAuthResponse
                    .errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                    .error(e)
                    .buildJSONMessage();

            response.setStatus(oAuthResponse.getResponseStatus());
            PrintWriter pw = response.getWriter();
            pw.print(oAuthResponse.getBody());
            pw.flush();
            pw.close();

            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void validateCode(OAuthTokenRequest oauthRequest) throws OAuthProblemException {
        CodeManager cm = new CodeManager();

        cm.verifyCode(oauthRequest);
    }

    private void validateClient(OAuthTokenRequest oauthRequest) throws OAuthProblemException {

        ClientManager cm = new ClientManager();

        cm.verifyClient(oauthRequest);
    }

    @Override
    public String getServletInfo() {
        return "OAuth2 Access Token Endpoint";
    }
}
