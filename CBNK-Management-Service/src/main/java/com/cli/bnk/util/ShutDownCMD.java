package com.cli.bnk.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
class ShutDownCMD {

	@Autowired
	private ShutDownManager shutDownManager;

	private int errorCode;
	private String errorDescription;

	public void loadError(int errorCode, String errorDescription) {
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}

	public void shutDownApplication() {
		log.error("***************************** SYSTEM FAILURE ***********************************");
		log.error("***************** CBK MANAGEMENT APPLICATION IS SHUTTING DOWN ******************");
		log.error("****** Error Code : {}  Error Description : {} ", errorCode, errorDescription);
		shutDownManager.initiateShutDown(errorCode);
		System.exit(0);
	}
}
