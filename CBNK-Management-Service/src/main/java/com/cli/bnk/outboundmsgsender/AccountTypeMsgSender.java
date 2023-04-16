package com.cli.bnk.outboundmsgsender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.cli.bnk.model.SavingAccount;
import com.cli.bnk.util.KafkaConstant;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AccountTypeMsgSender {

	@Autowired
	private  KafkaTemplate<String, String> kafkaTemplate;

	public void msgSenderFromMangToParticipant(final String msg) {
		log.info("Sending msg from CBNK Management to CBNK Participant ", msg);
		long startTime = System.currentTimeMillis();
		kafkaTemplate.send(KafkaConstant.ACCOUNT_TYPE_DETAILS, msg);
		log.info("msg sent succesfully, required time is {} ms", System.currentTimeMillis() - startTime);
	}

}
