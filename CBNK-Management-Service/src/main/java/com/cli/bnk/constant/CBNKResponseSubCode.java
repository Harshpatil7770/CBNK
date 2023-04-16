package com.cli.bnk.constant;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.cli.bnk.model.ErrorDescAndLoc;

public class CBNKResponseSubCode {

	@Autowired
	public static final Map<String, ErrorDescAndLoc> jsonMap = new HashMap<>();

	static {
		jsonMap.put("001", new ErrorDescAndLoc("",""));
	}

}
