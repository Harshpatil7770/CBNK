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
		default:
		}
	}

	private void processShutDownCmd(ErrorCode errorCode) {
		shutDownCMD.loadError(errorCode.gerErrorCode(), errorCode.getErrorDescription());
		shutDownCMD.shutDownApplication();
	}
}
