package ar.project.web.exceptions;

import ar.project.ent.exeptions.CustomException;

public class WebException extends CustomException {

	private static final long serialVersionUID = 1L;

	public WebException() {
		super();
	}

	public WebException(String message) {
		super(message);
	}

	public WebException(Throwable e) {
		super(e);
	}

	public WebException(String message, Throwable e) {
		super(message, e);
	}

}
