package pl.coherent.store.HTTPserver;

import com.sun.net.httpserver.BasicAuthenticator;

public class ApiBasicAuthentication extends BasicAuthenticator {
    private static final String USER = "admin";
    private static final String PASSWORD = "password";

    public ApiBasicAuthentication(String realm) {
        super(realm);
    }

    @Override
    public boolean checkCredentials(String user, String password) {
        return user.equals(USER) && password.equals(PASSWORD);
    }
}
