package com.priscripto.model;


public enum Role {
	ADMIN, DOCTOR, PATIENT;

//	@JsonCreator
//	public static Role fromString(String value) {
//		return Role.valueOf(value.toUpperCase());
//	}
//
//	@JsonValue
//	public String toValue() {
//		return name();
//	}
}
