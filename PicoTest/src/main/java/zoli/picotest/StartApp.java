package zoli.picotest;

import blueprint.ConnectionFactoryZoli;
import camel.RouteZoli;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;

/**
 * Created by Zoli on 15/09/2016.
 */
public class StartApp {

    //this is crap
    public static void main(String ... args){
        MutablePicoContainer pc = new DefaultPicoContainer();
        pc.addComponent("route", new RouteZoli());
        pc.addComponent("cf", new ConnectionFactoryZoli());

        RouteZoli rc = pc.getComponent(RouteZoli.class);


        rc.startRoute();

    }

}
