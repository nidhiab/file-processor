package com.oracle.file.processor.exceptions;

/**
 * ApplicationException class. This class extends from RuntimeException and represents any exception cases that were unexpected.
 * 
 * @author NBhasin
 *
 */
public class ApplicationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	/**
	 * Parameterized constructor that accepts a String message and Throwble
	 * 
	 * @param errorMessage - String message
	 * @param err          - throwable
	 */
	public ApplicationException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}

	/**
	 * Parameterized constructor that accepts a String message
	 * 
	 * @param errorMessage - String message
	 */
	public ApplicationException(String errorMessage) {
		super(errorMessage);
	}
}
