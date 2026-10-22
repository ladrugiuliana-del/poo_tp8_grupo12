package ar.edu.unju.fi.poo.exceptions;

public class EmpleadoException extends Exception {
	private static final long serialVersionUID = 1L;

	public EmpleadoException() {
        super();
    }

    public EmpleadoException(String message) {
        super(message);
    }

    public EmpleadoException(String message, Throwable cause) {
        super(message, cause);
    }
    
    
}
