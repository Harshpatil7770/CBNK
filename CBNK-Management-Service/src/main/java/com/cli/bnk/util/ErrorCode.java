package com.cli.bnk.util;

public enum ErrorCode {

	FAILED_WHILE_SENDING_MSG_TO_QUEUE(501, "Failed while sending message to queue");

	private int errorCode;

	private String errorDescription;

	private ErrorCode(int errorCode, String errorDescription) {
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public int gerErrorCode() {
		return errorCode;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

}
