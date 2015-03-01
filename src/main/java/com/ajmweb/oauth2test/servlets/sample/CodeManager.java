package com.ajmweb.oauth2test.servlets.sample;

import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * manage Authorization Code
 * Created by udumbara on 2015/02/22.
 */
public class CodeManager {

    private static final String testCode = "TESTCODE";

    public String issueCode(String clientId){

        // 認可コードを発行する
        // authorization codeの時は、認可後のトークン発行時に
        // チェックする必要があるので、どのコードをどのクライアントに発行したか
        // 覚えておく必要があるかもしれない
        // このサンプルでは決め打ちなので覚えていない
        Logger.getLogger(CodeManager.class.getName()).log(Level.INFO,"Issue Code for " + clientId);

        return testCode;
    }

    public void verifyCode(OAuthTokenRequest oauthRequest) throws OAuthProblemException {

        String authzCode = oauthRequest.getCode();
        String clientId = oauthRequest.getClientId();

        // このサンプルでは決め打ちチェック

        if(!testCode.equals(authzCode)){
            Logger.getLogger(CodeManager.class.getName()).log(Level.WARNING,"Request Code="+authzCode);
            throw OAuthProblemException.error(OAuthError.TokenResponse.INVALID_CLIENT,"Not Registed or Expired");
        }

        if(!"AAA".equals(clientId)){
            Logger.getLogger(CodeManager.class.getName()).log(Level.WARNING,"Request Client ID="+clientId);
            throw OAuthProblemException.error(OAuthError.TokenResponse.UNAUTHORIZED_CLIENT,"Not Registed or Expired");
        }
    }
}
