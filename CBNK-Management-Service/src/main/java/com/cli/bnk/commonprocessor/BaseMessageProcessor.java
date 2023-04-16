package com.cli.bnk.commonprocessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cli.bnk.constant.CBNKResponseSubCode;
import com.cli.bnk.model.ErrorVO;
import com.cli.bnk.model.ErrorVOType;
import com.cli.bnk.util.BuildErrorVO;

@Component
public abstract class BaseMessageProcessor {

	@Autowired
	private BuildErrorVO buildErrorVO;

	public ErrorVO constructAccountTypeJsonDataErrorVo(String accountTypeErrorData) {
		ErrorVO errorVO = buildErrorVO.buildErrorVO(accountTypeErrorData, ErrorVOType.MSG_TYPE_JSON, "001");
		return errorVO;
	}
}
