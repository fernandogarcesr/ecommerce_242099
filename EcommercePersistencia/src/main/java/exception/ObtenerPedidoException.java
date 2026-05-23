/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exception;

/**
 *
 * @author Fernando Garces 
 */
public class ObtenerPedidoException extends Exception{

    public ObtenerPedidoException() {
    }

    public ObtenerPedidoException(String message) {
        super(message);
    }

    public ObtenerPedidoException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObtenerPedidoException(Throwable cause) {
        super(cause);
    }

    public ObtenerPedidoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
