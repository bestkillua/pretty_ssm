package com.pretty.ssm.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pretty.ssm.cache.RedisCache;

/**
 * 定时任务 
 */
public class Quartz {
	@Component
	public class BizQuartz {

		private final Logger LOG = LoggerFactory.getLogger(this.getClass());
		@Autowired
		private RedisCache cache;
		
		/**
		 * 每隔3分钟定时清理缓存
		 */
		@Scheduled(cron = "0 0/3 * * * ? ")
		public void cacheClear() {
			LOG.info("@Scheduled-------cacheClear()，开始清理缓存");
			cache.clearCache();
		}
		
	}
}
