package com.ajmweb.oauth2test.servlets.sample;

import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * manage Client
 * Created by udumbara on 2015/02/15.
 */
public class ClientManager {

    private List<OAuth2Client> clients = new ArrayList<>();

    public ClientManager(){
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

        // client が登録されていることを確認する
        OAuth2Client findClient = findRegistClient(oauthRequest.getClientId());

        if (findClient == null) {
            Logger.getLogger(ClientManager.class.getName()).log(Level.WARNING, "RequestClientID=" + oauthRequest.getClientId());
            throw OAuthProblemException.error(OAuthError.CodeResponse.UNAUTHORIZED_CLIENT, "Client not registration(01)");
        }

        // 戻り先が、登録済みのredirect_uriと一致していることを確認する
        if (!checkRegistRedirectUri(findClient,oauthRequest.getRedirectURI())) {
            Logger.getLogger(ClientManager.class.getName()).log(Level.WARNING, "RequestRedirectUri=" + oauthRequest.getRedirectURI());
            throw OAuthProblemException.error(OAuthError.CodeResponse.UNAUTHORIZED_CLIENT, "Client not registration(02)");
        }
    }

    public void verifyClient(OAuthTokenRequest oauthRequest) throws OAuthProblemException {

        // トークン発行時のチェック

        // クライアントの認証をしなければならない
        // RFCのサンプルはBasic認証を実施している
        // それ以外でも良いとある

        // このサンプルでは、まじめにする気がないので、client_idが登録されていることだけチェックしておく
        // 本当はclient credencials をもって検証すべき

        OAuth2Client findClient = findRegistClient(oauthRequest.getClientId());

        if (findClient == null) {
            Logger.getLogger(ClientManager.class.getName()).log(Level.WARNING, "RequestClientID=" + oauthRequest.getClientId());
            throw OAuthProblemException.error(OAuthError.CodeResponse.UNAUTHORIZED_CLIENT, "Client not registration(01)");
        }
    }

    private OAuth2Client findRegistClient(String clientId){
        OAuth2Client findClient = null;
        for (OAuth2Client client : clients) {

            // clientId は必ず送信されるはず
            if (client.getClientId().equals(clientId)) {
                findClient = client;
            }
        }
        return findClient;
    }

    private boolean checkRegistRedirectUri(OAuth2Client client, String redirectUri) throws OAuthProblemException {
        // 仕様上、Redirect Uriは登録されている完全マッチがよいとされる
        // ただし、実際には動的にRedirectUriが生成されるサイトも多いので、
        // 戻り先Uriまでの部分マッチ（URI scheme/authority/path）を登録させる、でも良いとなっている
        // このサンプルは事前に上記URIが登録されていて、その前方一致とする
        // なお、このサンプルのチェックはろくにチェックしていない作りなので基本的にダメである
        if(redirectUri == null) {
            Logger.getLogger(ClientManager.class.getName()).log(Level.WARNING, "RequestRedirectUri=" + redirectUri);
            throw OAuthProblemException.error(OAuthError.CodeResponse.UNAUTHORIZED_CLIENT, "Client not registration(02)");
        }

        boolean checkUri = false;
        for (String uri : client.getRedirectUri()) {
            checkUri = redirectUri.startsWith(uri);
            if (checkUri) break;
        }
        return checkUri;
    }

}
