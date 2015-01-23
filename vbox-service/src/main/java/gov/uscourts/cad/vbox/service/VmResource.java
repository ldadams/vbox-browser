package gov.uscourts.cad.vbox.service;

import gov.uscourts.cad.vbox.model.OperationRequest;
import gov.uscourts.cad.vbox.model.OperationResult;
import gov.uscourts.cad.vbox.PropertiesBean;
import gov.uscourts.cad.vbox.model.HostDetail;
import gov.uscourts.cad.vbox.model.MachineDetail;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import org.virtualbox_4_2.IHost;
import org.virtualbox_4_2.IMachine;
import org.virtualbox_4_2.IProgress;
import org.virtualbox_4_2.ISession;
import org.virtualbox_4_2.IVirtualBox;
import org.virtualbox_4_2.LockType;
import org.virtualbox_4_2.VirtualBoxManager;

/**
 * REST Web Service
 *
 * @author adamsl
 */
@Path("vm")
public class VmResource {

    @GET
    @Produces("application/json")
    public List<HostDetail> getVms() {
        VirtualBoxManager manager = VirtualBoxManager.createInstance(null);

        List<HostDetail> machineValues = new ArrayList<HostDetail>();
        
        for (String hostUri : PropertiesBean.getInstance().getServices()) {
            try {
                manager.connect(hostUri, PropertiesBean.getInstance().getUsername(), PropertiesBean.getInstance().getPassword());
                IVirtualBox vbox = manager.getVBox();
                IHost host = vbox.getHost();

                URL url = null;
                try {
                    url = new URL(hostUri);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(VmResource.class.getName()).log(Level.SEVERE, null, ex);
                }

                HostDetail hostDetail = createHostDetail(host, url.toString(), 
                        vbox.getVersion(), url.getHost());
                for (IMachine machine : vbox.getMachines()) {
                    hostDetail.getVirtualMachines().add(createMachineDetail(machine));
                }
                machineValues.add(hostDetail);
            } catch (Exception ex) {
<<<<<<< HEAD
                String s = ex.getMessage();
=======

>>>>>>> f4355b1918ce1989b5bd357e91ba26435cd5b43e
            } finally {
                manager.disconnect();
            }
        }
        return machineValues;
    }

    @POST
    @Path("savestate")
    @Produces("application/json")
    @Consumes({"application/xml", "application/json"})
    public OperationResult saveVmState(OperationRequest operationRequest) {
        VirtualBoxManager manager = VirtualBoxManager.createInstance(null);
        manager.connect(operationRequest.getHost(), operationRequest.getUsername(), operationRequest.getPassword());
        IVirtualBox vbox = manager.getVBox();
        OperationResult result = new OperationResult();

        try {
            ISession session = manager.getSessionObject();
            IMachine machineToSave = vbox.findMachine(operationRequest.getMachineId());
            machineToSave.lockMachine(session, LockType.Shared);
            IProgress progress = session.getConsole().saveState();
            progress.waitForCompletion(90000);

            result.setMachine(createMachineDetail(machineToSave));
            result.setHost(createHostDetail(vbox.getHost(),
                    machineToSave.getName(), vbox.getVersion(),
                    urlToHostName(operationRequest.getHost())));
            result.setSuccessful(true);

            if (!progress.getCompleted()) {
                result.setMessage("Process Timed out");
                result.setSuccessful(false);
                return result;
            }

            return result;
        } catch (Exception ex) {
            Logger.getLogger(VmResource.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(500);
        } finally {
            manager.disconnect();
        }
    }

    @POST
    @Path("start")
    @Produces("application/json")
    @Consumes({"application/xml", "application/json"})
    public OperationResult startVm(OperationRequest operationRequest) {
        VirtualBoxManager manager = VirtualBoxManager.createInstance(null);
        manager.connect(operationRequest.getHost(), operationRequest.getUsername(), operationRequest.getPassword());
        boolean started = manager.startVm(operationRequest.getMachineId(), "headless", -1);

        OperationResult result = new OperationResult();
        IVirtualBox vbox = manager.getVBox();
        result.setMachine(createMachineDetail(vbox.findMachine(operationRequest.getMachineId())));
        //result.setHost(createHostDetail(vbox.getHost(), vbox.get, vbox.getVersion(), operationRequest.getHost()));
        result.setSuccessful(started);
        result.setMessage(!started ? "Machine failed to start" : "");
        result.setMessage("Machine failed to start");
        return result;
    }

    private HostDetail createHostDetail(IHost host, String machineName, String version, String hostName) {
        HostDetail hostDetail = new HostDetail();
        hostDetail.setMemoryAvailable(host.getMemoryAvailable());
        hostDetail.setMemoryTotal(host.getMemorySize());
        hostDetail.setProcessorCoreCount(host.getProcessorCount());
        hostDetail.setServiceUri(machineName.toString());
        hostDetail.setOperatingSystem(host.getOperatingSystem() + " " + host.getOSVersion());
        hostDetail.setMemoryAvailable(Long.MIN_VALUE);
        hostDetail.setName(hostName);
        hostDetail.setVersion(version);
        hostDetail.setVirtualMachines(new ArrayList<MachineDetail>());
        return hostDetail;
    }

    private MachineDetail createMachineDetail(IMachine machine) {
        MachineDetail detail = new MachineDetail();
        detail.setName(machine.getName());
        detail.setStatus(machine.getState().name());
        detail.setId(machine.getId());
<<<<<<< HEAD
        //detail.setSessionPid(machine.getSessionPID());
=======
        detail.setSessionPid(machine.getSessionPID());
>>>>>>> f4355b1918ce1989b5bd357e91ba26435cd5b43e
        detail.setCpuCount(machine.getCPUCount());
        detail.setMemorySize(machine.getMemorySize());
        Calendar stateChangedOn = Calendar.getInstance();
        stateChangedOn.setTimeInMillis(machine.getLastStateChange());
        detail.setStateChangedOn(stateChangedOn);
        return detail;
    }

    private String urlToHostName(String url) {
        try {
            URL uurl = new URL(url);
            return uurl.getHost().substring(0, uurl.getHost().indexOf(":"));
        } catch (MalformedURLException ex) {
            Logger.getLogger(VmResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return url;
    }
}
