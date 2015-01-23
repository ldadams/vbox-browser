/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.uscourts.cad.vbox;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;
import javax.ejb.EJBException;

/**
 *
 * @author adamsl
 */
public class PropertiesBean {

    private Properties props;
    static private PropertiesBean instance;

    private final static String USERNAME = "vbox.username";
    private final static String PASSWORD = "vbox.password";
    private final static String SERVICES = "vbox.services";

    protected PropertiesBean() {
        try {
            InputStream propsStream
                    = PropertiesBean.class.getResourceAsStream("/app.properties");
            props = new Properties();
            props.load(propsStream);
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
        if (servicesString != null) {
            services = servicesString.split(" ");
        }
        return services;
    }

    static public PropertiesBean getInstance() {
        try {
            if (instance == null) {
                instance = new PropertiesBean();
            }
        } catch (Exception ex) {
            throw new EJBException("PropertiesBean initialization error", ex);
        }
        return instance;
    }

    public String getProperty(String name) {
        return props.getProperty(name);
    }
}
