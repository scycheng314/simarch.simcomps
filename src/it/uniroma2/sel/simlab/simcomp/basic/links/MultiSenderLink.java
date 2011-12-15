/*
 * 	Copyright (C) 2005-2011 Department of Enteprise Engineering, University of Rome "Tor Vergata"
 *                              ( http://www.dii.uniroma2.it )
 *
 *      This file is part of SimArch and was developed at the Software Engineering Laboratory
 *      ( http://www.sel.uniroma2.it )
 *
 *      SimArch is free software: you can redistribute it and/or modify
 *      it under the terms of the GNU General Public License as published by
 *      the Free Software Foundation, either version 3 of the License, or
 *      (at your option) any later version.
 *
 *      SimArch is distributed in the hope that it will be useful,
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *      GNU General Public License for more details.
 *
 *      You should have received a copy of the GNU General Public License
 *      along with SimArch.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package it.uniroma2.sel.simlab.simcomp.basic.links;


import it.uniroma2.sel.simlab.simcomp.basic.ports.InPort;
import it.uniroma2.sel.simlab.simcomp.basic.ports.OutPort;

import java.util.List;


/** Implements a link that connects two or more sender to a single recipient
 *
 * @author  Daniele Gianni
 */
public final class MultiSenderLink extends BasicLink {
        
    public MultiSenderLink(final List<OutPort> outputPorts, final InPort inputPort) {
        super(outputPorts.size(), 1);
        
        connect(outputPorts, inputPort);
    }
            
    private void connect(final List<OutPort> ops, final InPort ip) {
        
        inputPorts.add(ip);
        ip.setLink(this);
                
        for (OutPort o : ops) {            
            outputPorts.add(o);
            o.setLink(this);
        }
    }
        
    public static MultiSenderLink makeLink(final List<OutPort> ops, final InPort ip) {
        return new MultiSenderLink(ops, ip);
    }            
}
