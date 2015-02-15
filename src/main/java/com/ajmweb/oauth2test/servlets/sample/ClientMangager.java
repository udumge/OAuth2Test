package com.ajmweb.oauth2test.servlets.sample;

import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by udumbara on 2015/02/15.
 */
public class ClientMangager {

    private List<OAuth2Client> clients = new ArrayList<>();

    public ClientMangager(){
        setupClient();
    }

    public void setupClient(){

        // create Dummy Client
        OAuth2Client client = new OAuth2Client();
        client.setClientId("AAA");
        client.setClientSecret("BBB");
        List<String> redirectUris = new ArrayList<>();

        redirectUris.add("https://");
        redirectUris.add("http://");

        client.setRedirectUri(redirectUris);

        clients.add(client);

    }

    public void verifyClient(OAuthAuthzRequest oauthRequest) throws OAuthProblemException {

        // TODO 作りは考えもの

        OAuth2Client findClient = null;
        for (OAuth2Client client : clients) {

            // clientId は必ず送信されるはず
            if (client.getClientId().equals(oauthRequest.getClientId())) {
                findClient = client;
            }
        }
        if (findClient == null) {
            Logger.getLogger(Authorization.class.getName()).log(Level.WARNING, "RequestClientID=" + oauthRequest.getClientId());
            throw OAuthProblemException.error(OAuthError.CodeResponse.UNAUTHORIZED_CLIENT, "Client not registration(01)");
        }


        // 仕様上、Redirect Uriは登録されている完全マッチがよいとされる
        // ただし、実際には動的にRedirectUriが生成されるサイトも多いので、
        // 戻り先Uriまでの部分マッチ（URI scheme/authority/path）を登録させる、でも良いとなっている
        // このサンプルは事前に上記URIが登録されていて、その前方一致とする
        // なお、このサンプルのチェックはろくにチェックしていない作りなので基本的にダメである
        if(oauthRequest.getRedirectURI() == null) {
            Logger.getLogger(Authorization.class.getName()).log(Level.WARNING, "RequestRedirectUri=" + oauthRequest.getRedirectURI());
            throw OAuthProblemException.error(OAuthError.CodeResponse.UNAUTHORIZED_CLIENT, "Client not registration(02)");
        }

        boolean checkUri = false;
        for (String uri : findClient.getRedirectUri()) {
            checkUri = oauthRequest.getRedirectURI().startsWith(uri);
            if (checkUri) break;
        }

        if (!checkUri) {
            Logger.getLogger(Authorization.class.getName()).log(Level.WARNING, "RequestRedirectUri=" + oauthRequest.getRedirectURI());
            throw OAuthProblemException.error(OAuthError.CodeResponse.UNAUTHORIZED_CLIENT, "Client not registration(02)");
        }
    }

}
