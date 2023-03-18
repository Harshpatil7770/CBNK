package com.cli.bnk.outboundmsgsender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.cli.bnk.model.Branch;
import com.cli.bnk.util.KafkaConstant;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BranchMsgSender {

	@Autowired
	private KafkaTemplate<String, Branch> kafkaTemplate;

//	@Value("${partBranchTopic}")
//	private String partTopic;

	public void sendMessageToPartcipantTopic(final Branch branch) throws KafkaException {
		log.info("Sending msg to participant topic : {} ", KafkaConstant.PARTICIPANT_IN_BRANCH_TOPIC);
		kafkaTemplate.send(KafkaConstant.PARTICIPANT_IN_BRANCH_TOPIC, branch);
		log.info("Message Sent Succesfully");
	}

	public void sendNewBranchAddedMsgToParticipantTopic(final Branch branch) {

		log.info("Sending high prioertiy message to participant topic.");
		kafkaTemplate.send(KafkaConstant.HIGH_PRIORITY_PART_TOPIC, branch);
		log.info("Message Sent Succesfully");
	}
}
