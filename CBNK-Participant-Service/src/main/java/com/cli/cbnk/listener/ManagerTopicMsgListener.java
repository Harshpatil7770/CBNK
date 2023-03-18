package com.cli.cbnk.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.cli.cbnk.model.Branch;
import com.cli.cbnk.model.Manager;
import com.cli.cbnk.service.ManagerService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author patil_ha
 *
 */

/**
 * 
 * @Component annotations is used to mark a class is a java bean.
 * @Slf4j used for logger
 *
 */
@Component
@Slf4j
public class ManagerTopicMsgListener {

	/**
	 * @Autowired - annotations is used when we want instance of class
	 */
	@Autowired
	private ManagerService managerService;

	/**
	 * @KafkaListener - annotations is used to listen or consume the message from
	 *                producer
	 * @param manager
	 */
	@KafkaListener(topics = "CLI.CBNK.MGNT.IN.QUEUE")
	public void onMessage(Manager manager) {

		if (manager != null) {
			Thread.currentThread().setName("ParticipantMsgListener-" + System.currentTimeMillis() + "-Thread");
			log.info("Recived Manager details : {} ", manager);
			managerService.proceedRecivedData(manager);
		}
	}
}
