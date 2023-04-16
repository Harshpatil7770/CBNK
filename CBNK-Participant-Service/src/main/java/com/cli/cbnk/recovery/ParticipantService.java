package com.cli.cbnk.recovery;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.stereotype.Component;

import com.cli.cbnk.service.util.KafkaConstant;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ParticipantService {

	@EventListener(ApplicationReadyEvent.class)
	public void bootstrapRecovery() {

		log.info("BootStrap Recovery is started ......");

		/******************* Recover new account type details *******************/
		log.info("checking pending msg in account type kafka topic");

		consumerFactory();
	}

	@Bean
	private ConsumerFactory<String,String> consumerFactory() {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put(KafkaConstant.ACCOUNT_TYPE_DETAILS,StringDeserializer.class);
		return new DefaultKafkaConsumerFactory<>(map);
	}
}
