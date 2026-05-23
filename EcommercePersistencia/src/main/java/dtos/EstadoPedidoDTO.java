/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package dtos;
// Se cambio el enum para manejar el estado del pedido, se agregó el estado cancelado y se eliminaron los estados que no se van a manejar como devuelto y en proceso
public enum EstadoPedidoDTO {
    PENDIENTE, ENVIADO, ENTREGADO, CANCELADO
}
