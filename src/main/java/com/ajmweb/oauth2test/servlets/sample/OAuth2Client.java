/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajmweb.oauth2test.servlets.sample;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author udumbara
 */
class OAuth2Client {
    
    // Client IDとSecretは事前登録時に設定する
    // 仕様上の規定があまりないので自由に登録していいはず
    private String clientId;
    private String clientSecret;
    
    // Redirect Uriは事前に登録させる必要がある
    // 仕様上、複数持つ可能性がある
    private List<String> redirectUri = new ArrayList<>();

    /**
     * @return the clientId
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * @param clientId the clientId to set
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * @return the clientSecret
     */
    public String getClientSecret() {
        return clientSecret;
    }

    /**
     * @param clientSecret the clientSecret to set
     */
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    /**
     * @return the redirectUri
     */
    public List<String> getRedirectUri() {
        return redirectUri;
    }

    /**
     * @param redirectUri the redirectUri to set
     */
    public void setRedirectUri(List<String> redirectUri) {
        this.redirectUri = redirectUri;
    }
    
    
}
