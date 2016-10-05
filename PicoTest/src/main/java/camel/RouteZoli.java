package camel;

import blueprint.ConnectionFactoryZoli;

/**
 * Created by Zoli on 15/09/2016.
 */
public class RouteZoli {
    ConnectionFactoryZoli cf;

    public void startRoute(){
        cf.sayHi();
    }

}
