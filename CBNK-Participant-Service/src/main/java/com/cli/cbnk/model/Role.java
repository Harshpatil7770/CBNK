package com.cli.cbnk.model;

public enum Role {

	MANAGER('M'), CUSTOMER('C');

	private char userType;

	private Role(char userType) {
		this.userType= userType;
	}

	public char getId() {
		return userType;
	}

	public void setId(char userType) {
		this.userType = userType;
	}

	public static Role getAppropriateRole(char userType) {
		for (Role role : Role.values()) {
			if (role.getId() == userType) {
				return role;
			}
		}
		throw new RuntimeException("Unable to identity user type");
	}
}
