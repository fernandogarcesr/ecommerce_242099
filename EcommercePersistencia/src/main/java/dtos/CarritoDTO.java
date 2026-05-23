/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.util.List;

/**
 *
 * @author Fernando Garces
 */
public class CarritoDTO {
    private Long id;

    private Double total;
    
    private List<DetallesCarritoDTO> detallesCarrito;

    private UsuarioDTO usuario;

    public CarritoDTO(Long id, Double total, List<DetallesCarritoDTO> detallesCarrito, UsuarioDTO usuario) {
        this.id = id;
        this.total = total;
        this.detallesCarrito = detallesCarrito;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<DetallesCarritoDTO> getDetallesCarrito() {
        return detallesCarrito;
    }

    public void setDetallesCarrito(List<DetallesCarritoDTO> detallesCarrito) {
        this.detallesCarrito = detallesCarrito;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "CarritoDTO{" + "id=" + id + ", total=" + total + ", detallesCarrito=" + detallesCarrito + ", usuario=" + usuario + '}';
    }
    
    
}
