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

import jakarta.xml.ws.Service;
import org.apache.commons.lang3.StringUtils;
import org.solenopsis.session.Credentials;
import org.solenopsis.session.Login;
import org.solenopsis.session.soap.SessionUrlFactory;

/**
 * Used to compute session urls.
 *
 * @author Scot P. Floess
 */
public enum SessionUrlFactoryEnum implements SessionUrlFactory {
    APEX_SESSION_URL_FACTORY("services/Soap/s", SessionServerHostNameFactory.DEFAULT_SESSION_SERVER_FACTORY, SessionPortNameFactory.API_PORT_NAME_FACTORY),
    CUSTOM_SESSION_URL_FACTORY("services/Soap/class", SessionServerHostNameFactory.DEFAULT_SESSION_SERVER_FACTORY, SessionPortNameFactory.CUSTOM_PORT_NAME_FACTORY),
    ENTERPRISE_SESSION_URL_FACTORY("services/Soap/c", SessionServerHostNameFactory.DEFAULT_SESSION_SERVER_FACTORY, SessionPortNameFactory.API_PORT_NAME_FACTORY),
    METADATA_SESSION_URL_FACTORY("services/Soap/m", SessionServerHostNameFactory.METADATA_SESSION_SERVER_FACTORY, SessionPortNameFactory.API_PORT_NAME_FACTORY),
    PARTNER_SESSION_URL_FACTORY("services/Soap/u", SessionServerHostNameFactory.DEFAULT_SESSION_SERVER_FACTORY, SessionPortNameFactory.API_PORT_NAME_FACTORY),
    TOOLING_SESSION_URL_FACTORY("services/Soap/T", SessionServerHostNameFactory.DEFAULT_SESSION_SERVER_FACTORY, SessionPortNameFactory.API_PORT_NAME_FACTORY);

    /**
     * Our partial URL.
     */
    private final String partialUrl;

    /**
     * Computes the server host name.
     */
    private final SessionServerHostNameFactory sessionServerFactory;

    /**
     * Computes the port name.
     */
    private final SessionPortNameFactory portNameFactory;

    /**
     * Return our partial URL.
     */
    String getPartialUrl() {
        return partialUrl;
    }

    /**
     * Return our session server factory.
     */
    SessionServerHostNameFactory getSessionServerFactory() {
        return sessionServerFactory;
    }

    /**
     * Return the port name factory.
     */
    SessionPortNameFactory getPortNameFactory() {
        return portNameFactory;
    }

    /**
     * This constructor sets the SFDC web service, port type and partial URL (as defined in the Java doc header).
     *
     * @param webServiceType       the SFDC web service.
     * @param sessionServerFactory is the factory that can compute a server name for a session.
     * @param webServiceSubUrl     the port for the web service.
     */
    private SessionUrlFactoryEnum(final String partialUrl, final SessionServerHostNameFactory sessionServerFactory, final SessionPortNameFactory portNameFactory) {
        this.partialUrl = partialUrl;
        this.sessionServerFactory = sessionServerFactory;
        this.portNameFactory = portNameFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String computeUrl(Credentials credentials, Service service) {
        return StringUtils.concatWithSeparator(false, "/", credentials.url(), getPartialUrl(), getPortNameFactory().computePortName(credentials, service));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String computeSessionUrl(final Login loginContext, final Service service) {
        return StringUtils.concatWithSeparator(false, "/", getSessionServerFactory().computeServer(loginContext), getPartialUrl(), getPortNameFactory().computePortName(loginContext, service));
    }
}
