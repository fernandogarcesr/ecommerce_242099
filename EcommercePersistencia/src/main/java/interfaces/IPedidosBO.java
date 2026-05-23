/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.List;

import dtos.EstadoPedidoDTO;
import dtos.PedidoDTO;
import exception.AgregarPedidoException;
import exception.CambiarEstadoException;
import exception.ObtenerPedidoException;

/**
 *
 * @author Fernando Garces 
 */
public interface IPedidosBO {

    public void cambiarEstadoPedido(Long id, EstadoPedidoDTO estado) throws CambiarEstadoException;

    public PedidoDTO obtenerPedidoIndividual(Long id) throws ObtenerPedidoException;

    public List<PedidoDTO> obtenerTodosPedidos() throws ObtenerPedidoException;

    public PedidoDTO agregarPedido(PedidoDTO pedido) throws AgregarPedidoException;
    
    public List<PedidoDTO> obtenerPedidosPorUsuario(Long idUsuario) throws ObtenerPedidoException;
    
    public PedidoDTO crearPedido(Long idUsuario, String tipoPago, String direccionEnvio) throws AgregarPedidoException;
}
