package com.sizatn.ssd.configuration;

import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheManager.RedisCacheManagerBuilder;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class RedisCacheConfig extends CachingConfigurerSupport {
	
	@Autowired
	private RedisProperties redisProperties;

	@Bean
	@Override
	public KeyGenerator keyGenerator() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object target, Method method, Object... params) {
				StringBuilder sb = new StringBuilder();
				sb.append(target.getClass().getName());
				sb.append(method.getName());
				for (Object obj : params) {
					sb.append(obj.toString());
				}
				return sb.toString();
			}
		};
	}

//	@Bean
//	public RedisCacheManager cacheManager(RedisConnectionFactory factory) {
//		return RedisCacheManagerBuilder.fromConnectionFactory(factory).build();
//	}

	@Bean
	public RedisCacheManager cacheManager(LettuceConnectionFactory factory) {
		return RedisCacheManagerBuilder.fromConnectionFactory(factory).build();
	}

	@Bean
	public RedisTemplate<String, String> redisTemplate(LettuceConnectionFactory factory) {
		StringRedisTemplate template = new StringRedisTemplate(factory);
		template.afterPropertiesSet();
		template.setValueSerializer(jackson2JsonRedisSerializer());
		return template;
	}

	private Jackson2JsonRedisSerializer<String> jackson2JsonRedisSerializer() {
		Jackson2JsonRedisSerializer<String> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<String>(String.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		return jackson2JsonRedisSerializer;
	}
	

	@Bean
	public LettuceConnectionFactory lettuceConnectionFactory() {
		LettuceConnectionFactory lcf = new LettuceConnectionFactory(redisStandaloneConfiguration());
		return lcf;
	}

	@Bean
	public RedisStandaloneConfiguration redisStandaloneConfiguration() {
		RedisStandaloneConfiguration rsc = new RedisStandaloneConfiguration();
		rsc.setDatabase(redisProperties.getDatabase());
		rsc.setHostName(redisProperties.getHost());
		rsc.setPassword(RedisPassword.of(redisProperties.getPassword()));
		return rsc;
	}
}
