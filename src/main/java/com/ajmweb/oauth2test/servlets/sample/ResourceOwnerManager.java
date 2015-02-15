package com.ajmweb.oauth2test.servlets.sample;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by udumbara on 2015/02/15.
 */
public class ResourceOwnerManager {

    public void verifyResourceOwner(HttpServletRequest request) {

        // NOP
        // 本来ならCookieとかでユーザの認証状態を確認するが、本サンプルでは特に認証しない
        // 頑張って作って欲しい

        Logger.getLogger(Authorization.class.getName()).log(Level.INFO,"This user is authorized");
    }
}
