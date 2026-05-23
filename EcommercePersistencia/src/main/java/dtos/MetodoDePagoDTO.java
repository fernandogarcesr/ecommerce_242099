/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.util.Date;

/**
 *
 * @author Fernando Garces
 */
public class MetodoDePagoDTO {
    private Long id;
    private Double monto;
    private Date fechaHora;
    private TipoMetodoPagoDTO tipo;

    public MetodoDePagoDTO(Long id, Double monto, Date fechaHora, TipoMetodoPagoDTO tipo) {
        this.id = id;
        this.monto = monto;
        this.fechaHora = fechaHora;
        this.tipo = tipo;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public TipoMetodoPagoDTO getTipo() {
        return tipo;
    }

    public void setTipo(TipoMetodoPagoDTO tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "MetodoDePagoDTO{" + "id=" + id + ", monto=" + monto + ", fechaHora=" + fechaHora + ", tipo=" + tipo + '}';
    }
    
    
}
