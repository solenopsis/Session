package org.solenopsis.session.soap.login;

import org.solenopsis.session.login.LoginService;

/**
 *
 * @author sfloess
 */
public enum LoginServiceEnum {
    ENTERPRISE(new EnterpriseLoginService()),
    PARTNER(new PartnerLoginService()),
    TOOLING(new ToolingLoginService());

    private final LoginService loginService;

    private LoginServiceEnum(final LoginService loginService) {
        this.loginService = loginService;
    }

    public LoginService getLoginService() {
        return loginService;
    }
}
