package ehcachetrail;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.CacheManagerBuilder;
import org.ehcache.config.CacheConfigurationBuilder;

public class Test {

	public static void main(String[] args) {

		CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
				.withCache("preConfigured", 
						CacheConfigurationBuilder.newCacheConfigurationBuilder()
						.buildConfig(Long.class, String.class))
				.build();

		Cache<Long, String> preConfigured = cacheManager.getCache("preConfigured", Long.class, String.class);

		Cache<Long, String> myCache = cacheManager.createCache("myCache", 
				CacheConfigurationBuilder.newCacheConfigurationBuilder().buildConfig(Long.class, String.class));

		myCache.put(1L, "da one!");
		String value = myCache.get(1L);

		cacheManager.removeCache("preConfigured");

		cacheManager.close();

	}
}
