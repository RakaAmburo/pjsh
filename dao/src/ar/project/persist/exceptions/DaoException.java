package ar.project.persist.exceptions;

import ar.project.ent.exeptions.CustomException;

public class DaoException extends CustomException {

	private static final long serialVersionUID = 1L;
	
	public DaoException() {
		super();
	}

	public DaoException(String message) {
		super(message);
	}

	public DaoException(Throwable e) {
		super(e);
	}
	
	public DaoException(String message, Throwable e) {
		super(message, e);
	}

}
