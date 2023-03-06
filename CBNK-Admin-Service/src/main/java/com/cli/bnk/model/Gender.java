package com.cli.bnk.model;

public enum Gender {

	MALE('M'), FEMALE('F');

	private char genderType;

	private Gender(char genderType) {
		this.genderType = genderType;
	}

	public void setGenderType(char genderType) {
		this.genderType = genderType;
	}

	public char getGenderType() {
		return genderType;
	}

	public static Gender verifyGenders(char genderType) {
		for (Gender existingType : Gender.values()) {
			if (existingType.getGenderType() == genderType) {
				return existingType;
			}
		}
		throw new RuntimeException("Unknow Gender Type");
	}

}
