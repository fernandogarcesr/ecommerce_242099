/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Fernando Garces
 */
@Entity
@Table(name = "pedidos")
public class Pedido implements Serializable {

    @Id
    @Column(name = "id_pedido")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "numero_pedido",  nullable = false)
    private String numeroPedido;
    
    @Column(name = "estado", nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoPedido estado;
    
    @Column(name = "total", length = 50, nullable = false)
    private Double total;
    
    @Column(name = "fecha", nullable = false)
    private Date fecha;
    
    @Column(name = "direccion", nullable = false)
    private String direccion;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
    
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="id_metodo_pago", nullable=false)
    private MetodoPago metodoPago;
    
    
    
    //un pedido tiene muchos detallePedido, si pedido camina detallespedido tambien. 
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<DetallesPedido> detallesPedido;

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

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
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

    

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public List<DetallesPedido> getDetallesPedido() {
        return detallesPedido;
    }

    public void setDetallesPedido(List<DetallesPedido> detallesPedido) {
        this.detallesPedido = detallesPedido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
    public Pedido() {
    }

    public Pedido(Long id, String numeroPedido, EstadoPedido estado, Double total, Date fecha, MetodoPago metodoPago, List<DetallesPedido> detallesPedido) {
        this.id = id;
        this.numeroPedido = numeroPedido;
        this.estado = estado;
        this.total = total;
        this.fecha = fecha;
        this.metodoPago = metodoPago;
        this.detallesPedido = detallesPedido;
    }

    public Pedido(Long id, String numeroPedido, EstadoPedido estado, Double total, Date fecha, String direccion, Usuario usuario, MetodoPago metodoPago, List<DetallesPedido> detallesPedido) {
        this.id = id;
        this.numeroPedido = numeroPedido;
        this.estado = estado;
        this.total = total;
        this.fecha = fecha;
        this.direccion = direccion;
        this.usuario = usuario;
        this.metodoPago = metodoPago;
    }
    
    

    public Pedido(String numeroPedido, EstadoPedido estado, Double total, Date fecha, MetodoPago metodoPago, List<DetallesPedido> detallesPedido) {
        this.numeroPedido = numeroPedido;
        this.estado = estado;
        this.total = total;
        this.fecha = fecha;
        this.metodoPago = metodoPago;
        this.detallesPedido = detallesPedido;
    }

    public Pedido(String numeroPedido, EstadoPedido estado, Double total, Date fechaHora, String direccion, Usuario usuario, MetodoPago metodoPago) {
        this.numeroPedido = numeroPedido;
        this.estado = estado;
        this.total = total;
        this.fecha = fechaHora;
        this.direccion = direccion;
        this.usuario = usuario;
        this.metodoPago = metodoPago;
    }
    
    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Pedido[ id=" + id + " ]";
    }
    
}
