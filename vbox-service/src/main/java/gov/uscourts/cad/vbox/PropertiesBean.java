/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.uscourts.cad.vbox;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import javax.ejb.EJBException;

/**
 *
 * @author adamsl
 */
public class PropertiesBean {

    private Properties props;
    static private PropertiesBean instance;
    
    private static String userName = null;
    private static String password = null;
    private static String[] services = null;
    
    private final static String USERNAME = "vbox.user";
    private final static String PASSWORD = "vbox.password";
    private final static String SERVICES = "vbox.services";
    
    protected PropertiesBean() {
     try {
            InputStream propsStream
                    = PropertiesBean.class.getResourceAsStream("/app.properties");
            props = new Properties();
            props.load(propsStream);
            
            userName = props.getProperty(USERNAME);
            password = props.getProperty(PASSWORD);
            services = props.getProperty(SERVICES).split(",");
        } catch (Exception ex) {
            throw new EJBException("PropertiesBean initialization error", ex);
        }   
    }
    
    public String getUsername() {
        return getProperty(USERNAME);
    }
    
    public String getPassword() {
        return getProperty(PASSWORD);
    }
    
    public String[] getServices() {
        String servicesString = getProperty(SERVICES);
        String[] services = null;
        if(services != null) {
            services = servicesString.split("'");
        }
        return services;
    }
    
    static public PropertiesBean getInstance() {
        if(instance == null) {
            instance = new PropertiesBean();
        }
        return instance;
    }

    public String getProperty(String name) {
        return props.getProperty(name);
    }
}
