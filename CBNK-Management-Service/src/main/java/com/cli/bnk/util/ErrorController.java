package com.cli.bnk.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ErrorController {

	@Autowired
	private ShutDownCMD shutDownCMD;

	public void getErrorController(ErrorCode errorCode) {

		switch (errorCode) {
		case FAILED_WHILE_SENDING_MSG_TO_QUEUE:
			log.error("********* FAILED WHILE SENDING MSG TO QUEUE **********");
			processShutDownCmd(errorCode);
			break;
		case DB_CONNECTION_FAILURE:
			log.error("******* FAILED WHILE PERFORMING DB OPERATIONS ********");
			processShutDownCmd(errorCode);
			break;
		default:
			log.error("Unknow Error Type ErrorCode : {} and ErrorDescription : {} ", errorCode.getErrorCode(),
					errorCode.getErrorDescription());
		}
	}

	private void processShutDownCmd(ErrorCode errorCode) {
		shutDownCMD.loadError(errorCode.getErrorCode(), errorCode.getErrorDescription());
		shutDownCMD.shutDownApplication();
	}
}
