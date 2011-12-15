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

package it.uniroma2.sel.simlab.simcomp.basic.ports;

import it.uniroma2.sel.simlab.simarch.data.GeneralEntity;
import it.uniroma2.sel.simlab.simarch.data.Name;
import it.uniroma2.sel.simlab.simarch.data.Port;
import it.uniroma2.sel.simlab.simcomp.basic.links.BasicLink;

/*
 * Provides a basic implementation of the port interface
 *
 * @author Daniele Gianni
 */
public class BasicPort implements Port {

    // port name
    private Name name;

    // link to which this port is connected
    private BasicLink link;

    // simulation entity defining this port
    private GeneralEntity owner;
    
    public BasicPort(final Name n, final GeneralEntity e) {                

        setName(n);
        setOwner(e);
    }
        
    public GeneralEntity getOwner() {
        return owner;
    }
        
    public BasicLink getLink() {
        return link;
    }
    
    public Name getName() {
        return name;
    }
        
    private void setOwner(final GeneralEntity e) {
        owner = e;
    }

    public void setLink(final BasicLink l) {
        link = l;
    }

    private void setName(final Name s) {
        name = s;
    }        
}
