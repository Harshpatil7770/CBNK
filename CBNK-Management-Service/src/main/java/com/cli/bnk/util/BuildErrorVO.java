package com.cli.bnk.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cli.bnk.model.ErrorVO;
import com.cli.bnk.model.ErrorVOType;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BuildErrorVO {

	@Autowired
	private ErrorVO errorVO;

	public ErrorVO buildErrorVO(String data, String msgType, String msgCode) {

		log.error("Building Error VO");
		switch (msgType) {

		case ErrorVOType.MSG_TYPE_JSON:
			errorVO.setErrorMsgType(ErrorVOType.MSG_TYPE_JSON);
			errorVO.setReasonDescription("recieved unknown type of message");
			setAddtionalErrorVOData(data, msgType, msgCode);
			break;
		default:
			log.error("Unable to identify error vo msg type.");
		}
		return errorVO;
	}

	private void setAddtionalErrorVOData(String data, String msgType, String msgCode) {
		errorVO.setRejectionDate(new Date().toString());
		errorVO.setErrorMsgSubType(msgCode);
	}
}
