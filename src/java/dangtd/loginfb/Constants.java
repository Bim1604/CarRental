/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dangtd.loginfb;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class Constants implements Serializable{

    public static String FACEBOOK_APP_ID = "897988791001126";
    public static String FACEBOOK_APP_SECRET = "a1b0fefc125d19583bc81a1df653a474";
    public static String FACEBOOK_REDIRECT_URL = "https://localhost:8084/CarRental/loginFB";
    public static String FACEBOOK_LINK_GET_TOKEN = "https://graph.facebook.com/oauth/access_token?client_id=%s&client_secret=%s&redirect_uri=%s&code=%s";
}
