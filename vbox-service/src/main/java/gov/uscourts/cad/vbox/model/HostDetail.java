package gov.uscourts.cad.vbox.model;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author adamsl
 */
@XmlType(name = "host")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class HostDetail implements Serializable {

    private List<MachineDetail> virtualMachines;
    private String name;
    private String serviceUri;
    private String version;
    private Long memoryAvailable;
    private Long memoryTotal;
    private Long processorCoreCount;
    private String operatingSystem;

    public HostDetail() {
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public Long getMemoryAvailable() {
        return memoryAvailable;
    }

    public void setMemoryAvailable(Long memoryAvailable) {
        this.memoryAvailable = memoryAvailable;
    }

    public Long getMemoryTotal() {
        return memoryTotal;
    }

    public void setMemoryTotal(Long memoryTotal) {
        this.memoryTotal = memoryTotal;
    }

    public Long getProcessorCoreCount() {
        return processorCoreCount;
    }

    public void setProcessorCoreCount(Long processorCoreCount) {
        this.processorCoreCount = processorCoreCount;
    }
    
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getServiceUri() {
        return serviceUri;
    }

    public void setServiceUri(String serviceUri) {
        this.serviceUri = serviceUri;
    }

    public List<MachineDetail> getVirtualMachines() {
        return virtualMachines;
    }

    public void setVirtualMachines(List<MachineDetail> virtualMachines) {
        this.virtualMachines = virtualMachines;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
