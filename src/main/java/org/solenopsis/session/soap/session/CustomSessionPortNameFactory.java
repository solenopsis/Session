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

import javax.xml.ws.Service;
import org.flossware.jcore.utils.soap.ServiceUtils;
import org.solenopsis.session.Credentials;
import org.solenopsis.session.LoginContext;

/**
 * Computes a port name for custom services.
 *
 * @author Scot P. Floess
 */
final class CustomSessionPortNameFactory implements SessionPortNameFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public String computePortName(final Credentials credentials, final Service service) {
        return ServiceUtils.getPortName(service.getClass());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String computePortName(final LoginContext loginContext, final Service service) {
        return ServiceUtils.getPortName(service.getClass());
    }
}
