package com.osgi.services.impl;

import com.osgi.services.HelloService;

/**
 * Created by Zoli on 28/05/2017.
 */
public class HelloServiceImpl implements HelloService {
    public String sayHello() {
        System.out.println("in Hello world Service");
        return "Hello from osgi";
    }
}
