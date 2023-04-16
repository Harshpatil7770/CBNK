package com.cli.bnk.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ErrorVO {

	private String rejectReason;

	private String reasonDescription;

	private String errorLocation;

	private String additionalData;

	private String rejectionDate;

	private String errorMsgType;

	private String errorMsgSubType;
}
