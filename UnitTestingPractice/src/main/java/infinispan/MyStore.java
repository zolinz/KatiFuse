package infinispan;

/**
 * Created by Zoli on 1/09/2016.
 *
 */

public class MyStore {
    public MyCacheManager mcm;

    public MyStore(MyCacheManager mcm){
        this.mcm = mcm;
    }

    public void storeMessage(String key, String payload){
        mcm.getCache().put(key, payload);
    }


    public String retrieveMessage(String key){
        return mcm.getCache().get(key);
    }



}
