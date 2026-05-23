/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exception;

/**
 *
 * @author Fernando Garces 
 */
public class UsuarioInexistenteException extends Exception {

    public UsuarioInexistenteException(String message) {
        super(message);
    }

    public UsuarioInexistenteException(String message, Throwable cause) {
        super(message, cause);
    }
    
    
}
