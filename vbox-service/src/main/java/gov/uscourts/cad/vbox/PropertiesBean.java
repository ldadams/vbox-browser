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
    
<<<<<<< HEAD
    private final static String USERNAME = "vbox.username";
=======
    private static String userName = null;
    private static String password = null;
    private static String[] services = null;
    
    private final static String USERNAME = "vbox.user";
>>>>>>> f4355b1918ce1989b5bd357e91ba26435cd5b43e
    private final static String PASSWORD = "vbox.password";
    private final static String SERVICES = "vbox.services";
    
    protected PropertiesBean() {
     try {
            InputStream propsStream
                    = PropertiesBean.class.getResourceAsStream("/app.properties");
            props = new Properties();
            props.load(propsStream);
<<<<<<< HEAD
=======
            
            userName = props.getProperty(USERNAME);
            password = props.getProperty(PASSWORD);
            services = props.getProperty(SERVICES).split(",");
>>>>>>> f4355b1918ce1989b5bd357e91ba26435cd5b43e
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
<<<<<<< HEAD
        if(servicesString != null) {
            services = servicesString.split(" ");
=======
        if(services != null) {
            services = servicesString.split("'");
>>>>>>> f4355b1918ce1989b5bd357e91ba26435cd5b43e
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
