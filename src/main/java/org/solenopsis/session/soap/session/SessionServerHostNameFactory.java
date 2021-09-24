/*
 * Copyright (C) 2017 Scot P. Floess
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
package org.solenopsis.session.soap.session;

import org.solenopsis.session.LoginContext;

/**
 * Computes a server host name for a session. For non metadata API calls, this is the LoginContext.getBaseServerUrl(). For metadata
 * API calls it is LoginContext.getMetadataServerUrl().
 *
 * @author Scot P. Floess
 */
interface SessionServerHostNameFactory {
    /**
     * The default session server factory.
     */
    SessionServerHostNameFactory DEFAULT_SESSION_SERVER_FACTORY = new DefaultSessionServerHostNameFactory();

    /**
     * The metadata session server factory.
     */
    SessionServerHostNameFactory METADATA_SESSION_SERVER_FACTORY = new MetadataSessionServerHostNameFactory();

    /**
     * Will compute a server name.
     *
     * @param loginContext our session login data.
     *
     * @return a server name.
     */
    String computeServer(LoginContext loginContext);
}
