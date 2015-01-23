package gov.uscourts.cad.vbox.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "machine")
@XmlAccessorType(XmlAccessType.FIELD)
public class MachineDetail implements Serializable {

    private String name;
    private String status;
    private List<String> groups;
    private String id;
    private Calendar stateChangedOn;
    private Long sessionPid;
    private Long cpuCount;
    private Long memorySize;
        
    public MachineDetail() {

    }

    public Long getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(Long memorySize) {
        this.memorySize = memorySize;
    }

    public Long getCpuCount() {
        return cpuCount;
    }

    public void setCpuCount(Long cpuCount) {
        this.cpuCount = cpuCount;
    }

    public Long getSessionPid() {
        return sessionPid;
    }

    public void setSessionPid(Long sessionPid) {
        this.sessionPid = sessionPid;
    }

    public Calendar getStateChangedOn() {
        return stateChangedOn;
    }

    public void setStateChangedOn(Calendar stateChangedOn) {
        this.stateChangedOn = stateChangedOn;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getGroups() {
        return groups;
    }

    public void setGroups(List<String> groups) {
        this.groups = groups;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
