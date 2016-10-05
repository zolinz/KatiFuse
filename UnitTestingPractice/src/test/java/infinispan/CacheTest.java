package infinispan;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * Created by Zoli on 1/09/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class CacheTest {
    MyStore ms;
    MyCacheManager mcm;


    @Before
    public void setUp(){

         ms = new MyStore(mcm);
    }

    @Test
    public void when_cache_then_response(){
        ms.storeMessage("m1", "hello zoli");
        Assert.assertEquals("hello zoli", ms.retrieveMessage("m1"));


    }


}
