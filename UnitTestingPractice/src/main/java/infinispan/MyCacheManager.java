package infinispan;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zoli on 1/09/2016.
 * This class will have the actual cache (Map)
 */
public class MyCacheManager {


    public MyCacheManager(){
        this.cache = new HashMap<String, String>();
    }

    private Map<String,String> cache;


    public Map<String,String> getCache(){
        return cache;
    }

}
