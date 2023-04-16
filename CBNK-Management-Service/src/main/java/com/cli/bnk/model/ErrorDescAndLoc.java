package com.cli.bnk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDescAndLoc {

	private String errorDesc;

	private String errorLoc;
}
