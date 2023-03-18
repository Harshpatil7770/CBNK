package com.cli.cbnk.service.util;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.cli.cbnk.model.Branch;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BNKMessageProcessingObject {

	private BlockingQueue<Branch> blockingQueue;

	@PostConstruct
	public void init() {
		blockingQueue = new LinkedBlockingQueue<>();
	}

	public BlockingQueue<Branch> getBlockingQueue() {
		log.info("Putting Msg Into Blocking Queue");
		return blockingQueue;
	}

	public void setBlockingQueue(BlockingQueue<Branch> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}
	
	
}
