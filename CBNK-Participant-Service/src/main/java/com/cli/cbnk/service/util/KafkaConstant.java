package com.cli.cbnk.service.util;

import org.springframework.stereotype.Component;

@Component
public class KafkaConstant {
	//CLI.CBNK.MGNT.IN.QUEUE
	public static final String MANAGEMENT_IN_TOPIC = "CLI.CBNK.MGNT.IN.QUEUE";

	public static final String PARTICIPANT_IN_BRANCH_TOPIC = "CLI.CBNK.PART.BRANCH.QUEUE";

	public static final String PASSWORD_IN_TOPIC = "CLI.CBNK.PASSWD.IN.QUEUE";
	
	public static final String ACCOUNT_TYPE_DETAILS = "CLI.CBNK.ACC.IN.QUEUE";
}
