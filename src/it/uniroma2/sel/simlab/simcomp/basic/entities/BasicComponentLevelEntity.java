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

import it.uniroma2.sel.simlab.simarch.data.ComponentLevelEntity;
import it.uniroma2.sel.simlab.simarch.data.Event;
import it.uniroma2.sel.simlab.simarch.data.GeneralEntity;
import it.uniroma2.sel.simlab.simarch.data.Name;
import it.uniroma2.sel.simlab.simarch.data.OutputPort;
import it.uniroma2.sel.simlab.simarch.data.RemoteEntity;
import it.uniroma2.sel.simlab.simarch.data.Time;
import it.uniroma2.sel.simlab.simarch.exceptions.InvalidNameException;
import it.uniroma2.sel.simlab.simarch.exceptions.layer2.Layer2InternalException;
import it.uniroma2.sel.simlab.simarch.exceptions.layer2.TimeAlreadyPassedException;
import it.uniroma2.sel.simlab.simarch.exceptions.layer2.UnableToRegisterEntityException;
import it.uniroma2.sel.simlab.simarch.exceptions.layer2.UnknownRecipientException;
import it.uniroma2.sel.simlab.simarch.exceptions.layer2.UnlinkedPortException;
import it.uniroma2.sel.simlab.simarch.exceptions.layer3.Layer3Exception;
import it.uniroma2.sel.simlab.simarch.factories.Layer3ToLayer2Factory;
import it.uniroma2.sel.simlab.simarch.interfaces.Layer3ToLayer2;
import it.uniroma2.sel.simlab.simcomp.basic.errors.BasicError;

/** Defines the basic simulation entity implementation. This includes the definition
 * of the send, hold, and wait methods that will then be used by developers of
 * custom simulation languages to describe the entity's simulation logic
 *
 * @author Daniele Gianni
 */
public abstract class BasicComponentLevelEntity<T, S extends Enum> implements ComponentLevelEntity {
/*    
    private T t;
    private S s;
  */
    // flag representing whether an event has been received since last event
    private boolean eventReceived;

    // the id identifying the simulation entity
    private Integer id;

    // the entity name
    private Name name;

    // the buffer containing the received event, once this has been signalled through
    // the above flag
    private Event receivedEvent;

    // the name of the system hosting this entity
    private Name systemName;

    /*
     * The reference to the underlying execution container
     */
    protected Layer3ToLayer2 executionContainer;
    
    /** Creates a new instance of BasicComponentLevelEntity */
    public BasicComponentLevelEntity(final Name name, final Layer3ToLayer2Factory factory)throws InvalidNameException {        
        setName(name);
        executionContainer = factory.create(this);
    }
        
    //////
    
    protected Time getClock() {
        return executionContainer.getClock();
    }

    protected void hold(final Time t) {
        try {
            executionContainer.hold(t);
        } catch (Layer2InternalException ex) {
            ex.printStackTrace();
            throw new BasicError(ex);
        }
    }
    
    protected boolean holdUnlessIncomingEvent(final Time t) {
        try {            
            return executionContainer.holdUnlessIncomingEvent(t);
        } catch (Layer2InternalException ex) {
            ex.printStackTrace();
            throw new BasicError(ex);
        }
    }
    
    protected Event nextEvent() {
        try {
            executionContainer.waitNextEvent();
        } catch (Layer2InternalException ex) {
            ex.printStackTrace();
            throw new BasicError(ex);
        }
        return receivedEvent;
    }

    protected void register() {
        try {
            executionContainer.registerEntity();  
        } catch (Layer2InternalException ex) {
            ex.printStackTrace();
            throw new BasicError(ex);
        } catch (UnableToRegisterEntityException ex) {
            ex.printStackTrace();
            throw new BasicError(ex);
        }  
    }
              
    protected void send(final GeneralEntity dest, final Time delay, final Enum tag, final Object data) throws UnknownRecipientException, TimeAlreadyPassedException {
        try {
            executionContainer.send(dest, delay, tag, data);
        } catch (Layer2InternalException ex) {
            ex.printStackTrace();
            throw new BasicError(ex);
        }
    }
                  
    protected void send(final OutputPort o, final Time delay, final Enum tag, final Object data) throws UnlinkedPortException, TimeAlreadyPassedException {
        try {    
            executionContainer.send(o, delay, tag, data);
        } catch (Layer2InternalException ex) {
            ex.printStackTrace();
            throw new BasicError(ex);
        }
    }
    
    protected void send(final Name dest, final Time delay, final Enum tag, final Object data) throws UnknownRecipientException, TimeAlreadyPassedException, InvalidNameException {
        try {
            executionContainer.send(dest, delay, tag, data); 
        } catch (Layer2InternalException ex) {
            ex.printStackTrace();
            throw new BasicError(ex);
        }            
    }

    public RemoteEntity getAsRemoteEntity() {
        return new BasicRemoteEntity(systemName, name);
    }

    public abstract void body() throws Layer3Exception;
           
    public Integer getId() {
        return id;
    }
    
    public Name getEntityName() {
        return name;
    }
    
    public Integer getEntityId() {
        return id;
    }
    
    public Layer3ToLayer2 getExecutionContainer() {
        return executionContainer;
    }
    
    public Name getFullName() {        
        return executionContainer.getDeveloperInterface().getFullName();       
    }    
    
    public Event getReceivedEvent() {
        unsetEventReceived();
        return receivedEvent;        
    }
    
    public Name getSystemName() {
        return systemName;
    }
    
    public boolean isLocal() {
        return true;
    }    
    
    protected boolean isEventReceived() {
        return eventReceived;
    }
    
    public abstract void printStatistics();
    
    public void setEventReceived() {
        eventReceived = true;
    }
    
    public void setReceivedEvent(final Event e) {        
        receivedEvent = e;
        setEventReceived();
    }
    
    public void setId(final Integer i) {
        id = i;
    }
        
    public void setSystemName(final Name s) {
        systemName = s;
    }
    
    protected void unsetEventReceived() {
        eventReceived = false;
    }

    
    ////////////////
    
    public T getEventData() {
        return (T) receivedEvent.getData();
    }
    
    public S getEventTag() {
        return (S) receivedEvent.getTag();
    }

    private void setName(final Name n) {
        name = n;
    }    
}
