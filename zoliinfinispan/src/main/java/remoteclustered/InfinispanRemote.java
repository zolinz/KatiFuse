package remoteclustered;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;

/**
 * Created by Zoli on 12/02/2017.
 */
public class InfinispanRemote {

    public static void main(String[] args) {
        // 01 testE branch
        // Create a configuration for a locally-running server
        ConfigurationBuilder builder = new ConfigurationBuilder();
        builder.addServers("192.168.178.156:11222;192.168.178.150:11222");
        // Connect to the server
        RemoteCacheManager cacheManager = new RemoteCacheManager(builder.build());
        // Obtain the remote cache
        RemoteCache<String, String> cache = cacheManager.getCache();
        /// Store a value
        cache.put("key", "value");
        // Retrieve the value and print it out
        System.out.printf("key = %s\n", cache.get("key"));
        // Stop the cache manager and release all resources
        cacheManager.stop();
    }
}
