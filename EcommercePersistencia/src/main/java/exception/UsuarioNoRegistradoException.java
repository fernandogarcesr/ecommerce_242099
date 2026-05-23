
package exception;

/**
 *
 * @author Fernando Garces 
 */
public class UsuarioNoRegistradoException extends Exception{

    public UsuarioNoRegistradoException() {
    }

    public UsuarioNoRegistradoException(String message) {
        super(message);
    }

    public UsuarioNoRegistradoException(String message, Throwable cause) {
        super(message, cause);
    }
    
    
    
    
}
