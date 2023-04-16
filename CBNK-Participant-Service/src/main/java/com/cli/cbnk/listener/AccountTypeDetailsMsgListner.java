package com.cli.cbnk.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.cli.cbnk.service.AccountTypeDetailsImpl;
import com.cli.cbnk.service.util.KafkaConstant;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AccountTypeDetailsMsgListner {

	@Autowired
	private AccountTypeDetailsImpl accountTypeDetailsImpl;

	@KafkaListener(topics = KafkaConstant.ACCOUNT_TYPE_DETAILS, groupId = "myGroup")
	public void onMessage(String msg) {
		if (msg != null) {
			Thread.currentThread().setName("AccountTypeDetailsMsgListner-" + System.currentTimeMillis() + "-Thread");
			log.info("Received msg from queue .... {} ",msg);
			accountTypeDetailsImpl.processAccountDetailsMsg(msg);
		}
	}

}
