package com.cli.bnk.outboundmsgsender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.cli.bnk.model.Manager;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ManagementMsgSender {

	@Autowired
	private KafkaTemplate<String, Manager> kafkaTemplate;

	@Value("${PARTICIPANT_IN_QUEUE}")
	private String PARTICIPANT_IN_QUEUE;

	public void msgSendingToParticipantQueue(final Manager msg) throws KafkaException {
		log.info("Sending msg to Participant in queue : {} ", msg);
		kafkaTemplate.send(PARTICIPANT_IN_QUEUE, msg);
		log.info("Message sent succesfully !");
	}
}
