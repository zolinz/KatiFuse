package com.osgi.services;

import com.osgi.services.impl.HelloServiceImpl;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

/**
 * Created by Zoli on 28/05/2017.
 */
public class Activator implements BundleActivator{
    ServiceRegistration helloServiceRegistration;

    public void start(BundleContext bundleContext) throws Exception {

        System.out.println("hello service activated");
        HelloService hs = new HelloServiceImpl();

        helloServiceRegistration = bundleContext.registerService(HelloService.class.getName(), hs, null);
    }

    public void stop(BundleContext bundleContext) throws Exception {
        helloServiceRegistration.unregister();
    }
}
