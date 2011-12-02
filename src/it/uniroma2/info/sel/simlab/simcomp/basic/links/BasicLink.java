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

package it.uniroma2.info.sel.simlab.simcomp.basic.links;

import it.uniroma2.info.sel.simlab.simarch.data.ComponentLevelEntity;
import it.uniroma2.info.sel.simlab.simarch.data.Link;
import it.uniroma2.info.sel.simlab.simcomp.basic.ports.InPort;
import it.uniroma2.info.sel.simlab.simcomp.basic.ports.OutPort;
import java.util.ArrayList;
import java.util.List;

/*
 * Implements the structures for a generic link
 *
 * @author Daniele Gianni
 */
public class BasicLink implements Link {

    /*
     * the ports belonging to the recipient entities and through which the events
     * are received
     */
    protected List<InPort> inputPorts;

    /*
     * the ports belonging to the sender entities and through which the events
     * are sent
     */
    protected List<OutPort> outputPorts;
    
    protected BasicLink(final int outputPorts, final int inputPorts) {
        
        setInputPorts(new ArrayList<InPort>(inputPorts));
        setOutputPorts(new ArrayList<OutPort>(outputPorts));
    }
    
    public List<InPort> getInputPorts() {
        return inputPorts;
    }
    
    public List<OutPort> getOutputPorts() {
        return outputPorts;
    }
    
    protected void setInputPorts(final List<InPort> p) {
        inputPorts = p;
    }
    
    protected void setOutputPorts(final List<OutPort> p) {
        outputPorts = p;
    }
    
    public boolean isAllowedToSendThroughThisLink(final ComponentLevelEntity entity) {
        return true;
    }
}
