//package com.cli.bnk.outboundmsgsender;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.kafka.KafkaException;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Component;
//
//import com.cli.bnk.model.Manager;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Component
//@Slf4j
//public class ManageamentMsgSender {
//
//	@Autowired
//	private KafkaTemplate<String, Manager> kafkaTemplate;
//
////	@Value("${spring.kafka.topic}")
////	private String inQueue;
//	
//	@Value("${outTopic}")
//	private String outTopic;
//
//	public void msgSendingToParticipantQueue(final Manager msg) throws KafkaException {
//		log.info("Sending msg to Participant {} in queue : {} ", outTopic, msg);
//		kafkaTemplate.send(outTopic, msg);
//		log.info("Message sent succesfully !");
//	}
//}
