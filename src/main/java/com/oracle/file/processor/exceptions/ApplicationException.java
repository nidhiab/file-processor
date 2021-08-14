package com.oracle.file.processor.exceptions;

/**
 * ApplicationException class. This class extends from RuntimeException and represents any exception cases that were unexpected.
 * 
 * @author NBhasin
 *
 */
public class ApplicationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ApplicationException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}

	public ApplicationException(String errorMessage) {
		super(errorMessage);
	}
}
