package com.rdp.hosptialfrontdesk.model;

public class NoSpecialistFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public NoSpecialistFoundException(String message) {
		super(message);
	}
}
