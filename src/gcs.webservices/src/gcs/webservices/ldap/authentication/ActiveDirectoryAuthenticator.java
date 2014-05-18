package gcs.webservices.ldap.authentication;

import gcs.webapp.utils.exceptions.InternalException;
import gcs.webapp.utils.exceptions.WrongCredentialsException;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

/**
 * Ldap authenticator implementation that authenticates user through active
 * directory.
 * 
 * @author Simon Turcotte-Langevin
 */
public class ActiveDirectoryAuthenticator implements ILdapAuthenticator
{
    /** Regex for error codes within login exceptions. */
    private static final String errorCodeRegex = "\\([0-9]*\\)";

    /** Precompiled pattern for login exceptions error codes. */
    private static Pattern errorCodePattern = Pattern.compile(errorCodeRegex);

    /** */
    private String defaultUserDomain;

    /**
     * Authenticate a user through a LDAP.
     * 
     * @param uid User identification in the LDAP
     * @param password User password for authentication
     * @return Whether the authentication was successful or not
     */
    @Override
    public LdapAuthenticationToken authenticate(String uid, String password) throws InternalException
    {
        return authenticate(defaultUserDomain, uid, password);
    }

    /**
     * Authenticate a user through a LDAP.
     * 
     * @param domain Domain of the user in the ldap
     * @param uid User identification in the LDAP
     * @param password User password for authentication
     * @return Whether the authentication was successful or not
     * @throws InternalException
     */
    @Override
    public LdapAuthenticationToken authenticate(String domain, String uid, String password) throws InternalException
    {
        LdapAuthenticationToken token = null;

        try {
            LoginContext loginContext = new LoginContext(System.getProperty("gcs.login.context.id"),
                    new AuthenticationCallbackHandler(uid, password));
            loginContext.login();

            // Emit a new token
            token = new LdapAuthenticationToken(uid, loginContext);
        } catch (LoginException ex) {
            throwGoodException(ex);
            // throw new InternalException(getErrorMessageKey(ex), ex);
        }

        return token;
    }

    /**
     * Returns the error code of the login exception.
     * 
     * @param loginException The login exception.
     * @return The error code.
     */
    private static int parseErrorCode(Throwable loginExceptionCause)
    {
        Matcher matcher = errorCodePattern.matcher(loginExceptionCause.getMessage());
        if (matcher.find()) {
            String unparsedErrorCode = matcher.group(0).replace("(", "").replace(")", "");
            return Integer.parseInt(unparsedErrorCode);
        }

        return -1;
    }

    /**
     * Wraps the login exception into the good type.
     * 
     * @param loginException The login exception.
     * @throws InternalException If the exception was generated because of a
     *             server failure.
     * @throws WrongCredentialsException If the exception was generated because
     *             of a client's bad credentials.
     */
    private void throwGoodException(LoginException loginException)
    {
        Throwable inner = loginException.getCause();

        // Case 1: the message key cannot be determined because there's no
        // cause.
        if (inner == null) {
            throw new InternalException("ldap_authentication_general_error", loginException);
        }

        // Case 2: the message key can be determined only by type
        // check on the inner exception.
        if (inner instanceof UnknownHostException) {
            throw new InternalException("ldap_authentication_unreachable_host", loginException);
        }

        // Case 3: the message key can be determined based on the error code
        // embedded in the inner exception message.
        switch (parseErrorCode(inner)) {
            case 6:
                throw new WrongCredentialsException("ldap_authentication_login_wrong_username", loginException);
            case 24:
                throw new WrongCredentialsException("ldap_authentication_login_wrong_password", loginException);
            default:
                throw new InternalException("ldap_authentication_general_error", loginException);
        }
    }

    /**
     * Getter for the default user domain.
     * 
     * @return The default user domain
     */
    public String getDefaultUserDomain()
    {
        return defaultUserDomain;
    }

    /**
     * Setter for the default user domain.
     * 
     * @param defaultUserDomain The new default user domain
     */
    public void setDefaultUserDomain(String defaultUserDomain)
    {
        this.defaultUserDomain = defaultUserDomain;
    }

    /**
     * @author Simon Turcotte-Langevin
     */
    private class AuthenticationCallbackHandler implements CallbackHandler
    {
        private String username;
        private String password;

        public AuthenticationCallbackHandler(final String username, final String password)
        {
            super();
            this.username = username;
            this.password = password;
        }

        public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException
        {
            for (int i = 0; i < callbacks.length; i++) {
                // Provide username
                if (callbacks[i] instanceof NameCallback) {
                    NameCallback ncb = (NameCallback) callbacks[i];
                    ncb.setName(username);
                }

                // Provide password
                if (callbacks[i] instanceof PasswordCallback) {
                    PasswordCallback pcb = (PasswordCallback) callbacks[i];
                    pcb.setPassword(password.toCharArray());
                }
            }
        }
    }
}
