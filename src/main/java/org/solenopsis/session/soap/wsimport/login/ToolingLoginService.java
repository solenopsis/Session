/*
 * Copyright (C) 2015 Scot P. Floess
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.solenopsis.session.soap.wsimport.login;

import org.solenopsis.session.Credentials;
import org.solenopsis.session.Session;
import org.solenopsis.session.soap.login.LoginException;
import org.solenopsis.session.soap.login.LoginService;
import org.solenopsis.session.soap.login.LogoutException;
import org.solenopsis.soap.service.ServiceSubUrlEnum;
import org.solenopsis.soap.wsimport.port.factory.PortFactoryEnum;
import org.solenopsis.soap.wsimport.tooling.LoginResult;
import org.solenopsis.soap.wsimport.tooling.SforceServicePortType;

/**
 * Uses the Enterprise service for login/logout.
 *
 * @author Scot P. Floess
 */
class ToolingLoginService implements LoginService {
    Session toSession(final LoginResult loginResult, final Credentials credentials) {
        return
            new Session(
                loginResult.getMetadataServerUrl(),
                loginResult.isPasswordExpired(),
                loginResult.isSandbox(),
                loginResult.getServerUrl(),
                ServiceSubUrlEnum.TOOLING,
                loginResult.getSessionId(),
                loginResult.getUserId(),
                credentials
            );
    }

    Session login(final SforceServicePortType port, final Credentials credentials) {
        try {
            return toSession(port.login(credentials.username(), credentials.password()), credentials);
        } catch (final Exception exception) {
            throw new LoginException("Could not login using the Tooling service", exception);
        }
    }

    void logout(final SforceServicePortType port) {
        try {
            port.logout();
        } catch(final Exception exception) {
            throw new LogoutException("Logout trouble from the Tooling service", exception);
        }
    }

    /**
     * {@inheritDoc}
     */
    public Session login(final Credentials credentials) {
        return login(PortFactoryEnum.TOOLING.createPort(), credentials);
    }

    /**
     * {@inheritDoc}
     */
    public void logout(final Session session) {
//        logout(PortFactoryEnum.TOOLING.createPort());
    }
}
