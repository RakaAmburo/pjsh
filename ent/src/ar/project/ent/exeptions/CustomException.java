package ar.project.ent.exeptions;

public class CustomException extends Exception{

	
	private static final long serialVersionUID = 1L;
	
	public CustomException() {
		super();
	}

	public CustomException(String message) {
		super(message);
	}

	public CustomException(Throwable e) {
		super(e);
	}
	
	public CustomException(String message, Throwable e) {
		super(message, e);
	}

}
