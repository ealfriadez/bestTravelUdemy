package pe.edu.unfv.besttraveludemy.config;

import java.util.Map;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableCaching
@Slf4j
public class RedisConfig {

	@Value(value = "${cache.redis.address}")
	private String serverAddress;
	@Value(value = "${cache.redis.password}")
	private String serverPassword;

	/*
	 * Con esta configuracion cargamos el cliente de redis al contenedor de spring
	 */
	@Bean
	public RedissonClient redissonClient() {
		var config = new Config();
		config.useSingleServer().setAddress(serverAddress).setPassword(serverPassword);

		return Redisson.create(config);
	}

	/*
	 * Con esta configuracion podemos habilitar las anotaciones de spring
	 * cache @Cacheable
	 */
	@Bean
	@Autowired
	public CacheManager cacheManager(RedissonClient redissonClient) {
		var configs = Map.of(
							CacheConstants.FLY_CACHE_NAME, new CacheConfig(),
							CacheConstants.HOTEL_CACHE_NAME, new CacheConfig()
						);
		
		log.info("CacheManager: {}", configs.toString());
		
		return new RedissonSpringCacheManager(redissonClient, String.valueOf(configs));
	}
	
	//Añadir esta clase de constantes al proyecto
	public class CacheConstants {
	public static final String FLY_CACHE_NAME = "flights"; //cache name for flights
	public static final String HOTEL_CACHE_NAME = "hotels"; // cache name for hotels
	public static final String SCHEDULED_RESET_CACHE = "0 0 0 * * ?"; //cron expresion every day at 12AM
	}
}
