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
package org.solenopsis.session.soap.login;

import com.sforce.soap.partner.Soap;
import org.solenopsis.session.Credentials;
import org.solenopsis.session.Login;

/**
 * Implementation using the partner web service.
 *
 * @author Scot P. Floess
 */
final class PartnerLoginMgr implements LoginMgr {
    @Override
    public Login login(Object port, Credentials credentials) {
        try {
            return new Login(((Soap) port).login(credentials.username(), credentials.securityPassword()), credentials);
        } catch (final Throwable t) {
            throw new LoginException(t);
        }
    }

    @Override
    public void logout(Object port) {
        try {
            ((Soap) port).logout();
        } catch (final Throwable t) {
            throw new LogoutException(t);
        }
    }
}
