package com.cli.cbnk.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.cli.cbnk.service.util.KafkaConstant;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PartcipantMsgProducer {

	@Autowired
	KafkaTemplate<String, Long> kafkaTemplate;

//	@Value("${inManagementTopic}")
//	private String managementTopic;

	public void sendingMsgToManagementTopic(final long branchId) throws KafkaException {
		log.info("Sending request to CBNK MANAGEMENT TOPIC : {} ", KafkaConstant.MANAGEMENT_IN_TOPIC);
		kafkaTemplate.send(KafkaConstant.MANAGEMENT_IN_TOPIC, branchId);
		log.info("Request Sent Successfully to CBNK MANAGEMENT SERVICE");
	}
}
