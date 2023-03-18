package com.cli.bnk.dto.msglistener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.cli.bnk.model.Branch;
import com.cli.bnk.outboundmsgsender.BranchMsgSender;
import com.cli.bnk.service.BranchService;
import com.cli.bnk.util.KafkaConstant;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CBNKMangInTopicListener {

	@Autowired
	private BranchService branchService;

	@Autowired
	private BranchMsgSender branchMsgSender;

	@KafkaListener(topics = KafkaConstant.MANAGEMENT_IN_TOPIC, groupId = "myGroup")
	public void onMessage(Long branchId) {
		if (branchId != null) {
			Thread.currentThread().setName("CBNKMangInTopicListener-" + System.currentTimeMillis() + "-Thread");
			log.info("Recieved message from queue ...");
			synchronized (branchId) {
				log.info("Recieved Branch Id {} : ", branchId);
				Branch existingBranch = branchService.findBranchDetailsUsingBranchId(branchId);
				if (existingBranch != null) {
					branchMsgSender.sendMessageToPartcipantTopic(existingBranch);
				}
			}
		}
	}
}
