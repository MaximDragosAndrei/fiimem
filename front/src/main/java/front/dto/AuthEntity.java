package front.dto;

import javax.servlet.http.Cookie;

public class AuthEntity {
    private String username;
    private String token;

    public AuthEntity(String username, String token) {
        this.username = username;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static AuthEntity fromCookies(Cookie[] cookies){
        String username = null;
        String token = null;

        if (cookies == null){
            return null;
        }

        for (Cookie cookie : cookies){
            if (cookie.getName().equals("user-name")){
                username = cookie.getValue();
            }
            else if (cookie.getName().equals("user-token")){
                token = cookie.getValue();
            }
        }

        if (username != null && token != null){
            return new AuthEntity(username, token);
        }
        else{
            return null;
        }
    }
}
