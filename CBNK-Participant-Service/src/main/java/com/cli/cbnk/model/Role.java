package com.cli.cbnk.model;

public enum Role {

	MANAGER('M'), CUSTOMER('C');

	private char userType;

	private Role(char userType) {
		this.userType = userType;
	}

	public char getUserType() {
		return userType;
	}

	public void setUserType(char userType) {
		this.userType = userType;
	}

	public static char getAppropriateRole(char userType) {
		for (Role role : Role.values()) {
			if (role.getUserType() == userType) {
				return role.getUserType();
			}
		}
		throw new RuntimeException("Unable to identity user type");
	}
}
