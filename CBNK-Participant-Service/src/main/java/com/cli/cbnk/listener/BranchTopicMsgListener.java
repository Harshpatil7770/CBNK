package com.cli.cbnk.listener;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.cli.cbnk.model.Branch;
import com.cli.cbnk.service.util.BNKMessageProcessingObject;
import com.cli.cbnk.service.util.KafkaConstant;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@SuppressWarnings({ "unused" })
@Component
@Slf4j
public class BranchTopicMsgListener {

	@Autowired
	private BNKMessageProcessingObject bnkMessageProcessingObject;

	@KafkaListener(topics = KafkaConstant.PARTICIPANT_IN_BRANCH_TOPIC, groupId = "myGroup")
	public void onMessage(String branch) {
		if (branch != null) {
			Thread.currentThread().setName("BranchTopicMsgListener-" + System.currentTimeMillis() + "-Thread");
			Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
			synchronized (branch) {
				log.info("Recieved Message : {} ", branch);
				Branch branchDetails = convertStringToJsonObject(branch);
				commitMessageToProcessingQueue(branchDetails);
			}
		}
	}
	

	private Branch convertStringToJsonObject(String branch) {
		Gson gson = new Gson();
		Branch branchDetails = gson.fromJson(branch, Branch.class);
		return branchDetails;
	}

	private void commitMessageToProcessingQueue(Branch branch) {
		try {
			bnkMessageProcessingObject.getBlockingQueue().put(branch);
		} catch (InterruptedException e) {
			log.error("Exception occured while inserting msg into processing queue {}", e);
		}
	}
}
