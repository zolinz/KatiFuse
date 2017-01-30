package sayhello.zoli.impl;

import sayhello.zoli.InputSayHello;
import sayhello.zoli.OutputSayHello;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * Created by Zoli on 13/11/2016.
 */
//Service Implementation
@WebService(endpointInterface = "sayhello.zoli.SayHelloEndpoint")
public class HelloServiceImpl {

    @WebMethod(operationName = "SayHello", action = "http://zoli.sayhello/SayHello")
    @WebResult(name = "outputSayHello", targetNamespace = "http://zoli.sayhello", partName = "out")
    public OutputSayHello sayHello(
            @WebParam(name = "inputSayHello", targetNamespace = "http://zoli.sayhello", partName = "in")
                    InputSayHello in){

        OutputSayHello osh = new OutputSayHello();
        osh.setGreeting("Hello bello " + in.getFirstName() + " " + in.getLastName() );
        return osh;
    };
}
