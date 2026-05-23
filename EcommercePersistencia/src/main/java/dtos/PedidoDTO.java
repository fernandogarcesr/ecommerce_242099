/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Fernando Garces
 */
public class PedidoDTO {

    private Long id;
    private String numeroPedido;
    private EstadoPedidoDTO estado;
    private Double total;
    private Date fecha;
    private MetodoDePagoDTO metodoPago;
    private List<DetallePedidoDTO> detallesPedido;
    private String direccion;
    private UsuarioDTO usuario;

    public PedidoDTO() {
    }
    
    

    public PedidoDTO(Long id, String numeroPedido, EstadoPedidoDTO estado, Double total, Date fecha, MetodoDePagoDTO metodoPago, List<DetallePedidoDTO> detallesPedido, String direccion, UsuarioDTO usuario) {
        this.id = id;
        this.numeroPedido = numeroPedido;
        this.estado = estado;
        this.total = total;
        this.fecha = fecha;
        this.metodoPago = metodoPago;
        this.detallesPedido = detallesPedido;
        this.direccion = direccion;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(String numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public EstadoPedidoDTO getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedidoDTO estado) {
        this.estado = estado;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public MetodoDePagoDTO getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoDePagoDTO metodoPago) {
        this.metodoPago = metodoPago;
    }

    public List<DetallePedidoDTO> getDetallesPedido() {
        return detallesPedido;
    }

    public void setDetallesPedido(List<DetallePedidoDTO> detallesPedido) {
        this.detallesPedido = detallesPedido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public String getFechaHoraFormateada() {
        if (fecha != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            return sdf.format(fecha);  // Formatea la fecha
        }
        return "";
    }

    @Override
    public String toString() {
        return "PedidoDTO{" + "id=" + id + ", numeroPedido=" + numeroPedido + ", estado=" + estado + ", total=" + total + ", fecha=" + fecha + ", metodoPago=" + metodoPago + ", detallesPedido=" + detallesPedido + ", direccion=" + direccion + ", usuario=" + usuario + '}';
    }
}
