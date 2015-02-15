package com.ajmweb.oauth2test.servlets.sample;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;

/**
 * Authorization SeverのEndpoint
 * 仕様上、本endpoint URIはTLSを利用しなければならない
 *
 * @author udumbara
 */
public class Authorization extends HttpServlet {



    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws org.apache.oltu.oauth2.common.exception.OAuthSystemException OAuth2 library System Error oWo
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, OAuthSystemException {

        try {
            
            // OAuthで利用するGrant Typeに合わせて適切にリクエストをチェックすること
            // ここではAuthorization Code を利用するものとする
            OAuthAuthzRequest oauthRequest = new OAuthAuthzRequest(request);
            
            // Resource Owner verify
            // 自システムのユーザとして認証を行う
            // この処理はサンプルとしては特に頑張らない
            ResourceOwnerManager rom = new ResourceOwnerManager();
            rom.verifyResourceOwner(request);
            
            // Respones Typeのvalidateはライブラリがしてくれるかな、、、と思ってやらない
            // validate Response Type
            // validateResponseType(oauthRequest);
            
            // 仕様として、oauth clientは全てサーバに事前登録されているべきである
            // 本サンプルでは、DBとかに登録するのが面倒なので決め打ちのクライアントとする
            ClientMangager cm = new ClientMangager();
            cm.verifyClient(oauthRequest);
            
            // 問題ない場合は、Authorization Codeを返却する
            // codeは本来、動的に推測不可のコードを生成すべき
            OAuthResponse oauthResponse = OAuthASResponse
                    .authorizationResponse(request,HttpServletResponse.SC_FOUND)
                    .setCode("TESTCODE")
                    .location(oauthRequest.getRedirectURI())
                    .buildQueryMessage();
            
            response.sendRedirect(oauthResponse.getLocationUri());

        } catch (OAuthProblemException ex) {
            Logger.getLogger(Authorization.class.getName()).log(Level.SEVERE, "OAuth Protocol Error", ex);

            // バグってRedirectUriを返却しないクライアントを考えた方が良い
            
            OAuthResponse oauthResponse = OAuthASResponse
                    .errorResponse(HttpServletResponse.SC_FOUND)
                    .error(ex)
                    .location(ex.getRedirectUri())
                    .buildQueryMessage();
            response.sendRedirect(oauthResponse.getLocationUri());
        } 

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            // 仕様上は HTTP GET methodをサポートしなければならない
            // このサンプルはGET,POSTどちらもサポートする
            processRequest(request, response);
            
        } catch (OAuthSystemException ex) {
            Logger.getLogger(Authorization.class.getName()).log(Level.SEVERE, "OAuth System Error", ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            // 仕様上はHTTP POST methodは任意
            // このサンプルはGET,POSTどちらもサポートする
            processRequest(request, response);
        } catch (OAuthSystemException ex) {
            Logger.getLogger(Authorization.class.getName()).log(Level.SEVERE, "OAuth System Error", ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "OAuth2 Authorization ";
    }// </editor-fold>

}
