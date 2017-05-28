package osgi;

import com.osgi.services.HelloService;
import com.zoli.bp.ZoliTraining;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * Created by Zoli on 28/05/2017.
 */
public class Activator implements BundleActivator{

    ServiceReference helloServiceReference;

    ServiceReference zoliTrainingRef;
    public void start(BundleContext bundleContext) throws Exception {
        System.out.println("hello world Started");

        helloServiceReference = bundleContext.getServiceReference(HelloService.class.getName());

        HelloService helloService =(HelloService)bundleContext.getService(helloServiceReference);
        System.out.println(helloService.sayHello());


        zoliTrainingRef =  bundleContext.getServiceReference(ZoliTraining.class.getName());

        ZoliTraining zoliTraining = (ZoliTraining) bundleContext.getService(zoliTrainingRef);

        System.out.println(zoliTraining.swim() + zoliTraining.bike()+ zoliTraining.run());




    }

    public void stop(BundleContext bundleContext) throws Exception {
        System.out.println("Hello world stopped");
        bundleContext.ungetService(helloServiceReference);

    }
}
