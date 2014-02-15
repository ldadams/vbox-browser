/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.uscourts.cad.vbox.model;

import gov.uscourts.cad.vbox.model.HostDetail;
import gov.uscourts.cad.vbox.model.MachineDetail;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author adamsl
 */

@XmlType(name = "operationRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class OperationResult {
    private HostDetail host;
    private MachineDetail machine;
    private boolean successful;
    private String message;

    public HostDetail getHost() {
        return host;
    }

    public void setHost(HostDetail host) {
        this.host = host;
    }

    public MachineDetail getMachine() {
        return machine;
    }

    public void setMachine(MachineDetail machine) {
        this.machine = machine;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
