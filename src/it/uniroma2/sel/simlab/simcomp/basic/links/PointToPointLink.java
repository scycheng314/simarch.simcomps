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

/*
 * Implement the link that connects one sender to one recipient
 *
 * @author Daniele Gianni
 */
public final class PointToPointLink extends BasicLink {
         
    public PointToPointLink(final OutPort o, final InPort i) {
        super(1, 1);
        connect(o,i); 
    }
                
    private void connect(final OutPort op, final InPort ip) {    
        inputPorts.add(ip);
        ip.setLink(this);
        
        outputPorts.add(op);
        op.setLink(this);
    }     
    
    public static PointToPointLink makeLink(final OutPort o, final InPort i) {
        return new PointToPointLink(o, i);
    }        
}
