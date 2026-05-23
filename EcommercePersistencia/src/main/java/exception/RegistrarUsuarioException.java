/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exception;

/**
 *
 * @author Fernando Garces 
 */
public class RegistrarUsuarioException extends Exception{

    public RegistrarUsuarioException() {
    }

    public RegistrarUsuarioException(String message) {
        super(message);
    }

    public RegistrarUsuarioException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
