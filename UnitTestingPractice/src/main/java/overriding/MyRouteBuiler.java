package overriding;

/**
 * Created by Zoli on 11/11/2016.
 */
public class MyRouteBuiler  extends RouteBuilder{

    public void configure() throws Exception {

        System.out.print("hello");
        throw new Exception("hello zolika from exception");

    }
}
