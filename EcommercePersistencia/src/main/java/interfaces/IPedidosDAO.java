/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.EstadoPedido;

import entidades.Pedido;

import java.util.List;

import exception.PersistenciaException;

/**
 *
 * @author Fernando Garces 
 */
public interface IPedidosDAO {

    public void cambiarEstadoPedido(Long id, EstadoPedido estado) throws PersistenciaException;

    public Pedido obtenerPedidoIndividual(Long id) throws PersistenciaException;

    public List<Pedido> obtenerTodosPedidos() throws PersistenciaException;
    
    public Pedido agregarPedido(Pedido nuevoPedido) throws PersistenciaException;
    
    public List<Pedido> obtenerPedidosPorUsuario(Long idUsuario) throws PersistenciaException;

}
