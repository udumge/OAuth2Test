package com.ajmweb.oauth2test.servlets.sample;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * manage Access Token
 * Created by udumbara on 2015/03/01.
 */
public class TokenManager {

    public void checkAccessToken(HttpServletRequest request){
        // 本来はここで送信されたアクセストークンをチェックする
        // 今回のサンプルは何もチェックしない

        // Access Tokenについては、RFCが別なので注意する
        // このサンプルはRFC 6750 Bearer Token Usageのつもり
        String header = request.getHeader("Authorization");
        String body = request.getParameter("access_token");
        Logger.getLogger(TokenManager.class.getName()).log(Level.INFO, "Token : Header=" + header + " Body=" + body);

    }
}
