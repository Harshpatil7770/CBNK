package com.cli.cbnk.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.cli.cbnk.dto.CustomerCustomDTO;
import com.cli.cbnk.service.util.KafkaConstant;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PartcipantMsgProducer {

	@Autowired
	KafkaTemplate<String, Long> kafkaTemplate;

	@Autowired
	KafkaTemplate<String, CustomerCustomDTO> kafkaTemplateForPassword;

//	@Value("${inManagementTopic}")
//	private String managementTopic;

	/**
	 * final local variable means
	 * 
	 * @param branchId
	 * @throws KafkaException
	 */
	public void sendingMsgToManagementTopic(final long branchId) throws KafkaException {
		log.info("Sending request to CBNK MANAGEMENT TOPIC : {} ", KafkaConstant.MANAGEMENT_IN_TOPIC);
		kafkaTemplate.send(KafkaConstant.MANAGEMENT_IN_TOPIC, branchId);
		log.info("Request Sent Successfully to CBNK MANAGEMENT SERVICE");
	}

	public void sendingMsgToPasswordTopic(final CustomerCustomDTO customerCustomDTO) {
		log.info("Sending request to CBNK PASSWORD TOPIC : {} ", KafkaConstant.PASSWORD_IN_TOPIC);
		kafkaTemplateForPassword.send(KafkaConstant.PASSWORD_IN_TOPIC, customerCustomDTO);
		log.info("Request Sent Successfully to CBNK PASSWORD SERVICE");
	}
	
	public void test() {
		//WebSecurityConfiguration configuration=new WebSecurityConfiguration();
		//WebSecurityConfigurationAapter configuration=new WebSecurityConfigurationAdapter();
	}

//	public void test(CustomerCustomDTO customerCustomDTO) {
//		customerCustomDTO.setAccountNumber(100l);
//		log.info(" : {} ", customerCustomDTO);
//	}
}
