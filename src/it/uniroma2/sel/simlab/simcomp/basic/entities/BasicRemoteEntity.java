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

package it.uniroma2.sel.simlab.simcomp.basic.entities;

import it.uniroma2.sel.simlab.simarch.data.Name;
import it.uniroma2.sel.simlab.simarch.data.RemoteEntity;
import it.uniroma2.sel.simlab.simarch.exceptions.InvalidNameException;


/** Represents a proxy class for a remote {@code SimjEntity} (a {@code LocalEntity} running on a remote system)
 *
 *  @author     Daniele Gianni
 *  @version    2.1 06-01-06
 */
public class BasicRemoteEntity implements RemoteEntity {

    // the entity name
    private Name entityName;

    // the name of the system (i.e. federate) in which the entity is running
    private Name systemName;
        
    //private static DistributedProcessEngine engine;
       
    /** Creates a new instance of {@code RemoteEntity} 
     *  
     *  @param  sn the system name
     *  @param  en the entity name
     */
    public BasicRemoteEntity(final Name sn, final Name en) {        
        setEntityName(en);
        setSystemName(sn);
    }    
    
    public Name getEntityName() {
        return entityName;
    }
    
    public Integer getEntityId() {
        return -1;        
    }
    
    public Name getFullName() {        
        return null;//name;
                //systemName.getValue() + SimjName.TOKEN + entityName.getValue();
    }
    
    public Name getSystemName() {
        return systemName;
    }
    
    /*
    public void setName(final SimjFullName n) {
        name = n;
    }    
      */   
    
    /** {@inheritDoc}
     *  
     *  @return false
     */
    public boolean isLocal() {
        return false;
    }    

    private void setEntityName(final Name en) {
        entityName = en;
    }

    private void setSystemName(final Name sn) {
        systemName = sn;
    }

    public RemoteEntity getAsRemoteEntity() {
        return this;
    }
}
