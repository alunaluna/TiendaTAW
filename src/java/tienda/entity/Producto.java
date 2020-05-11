/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author eugenio
 */
@Entity
@Table(name = "PRODUCTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p")
    , @NamedQuery(name = "Producto.findById", query = "SELECT p FROM Producto p WHERE p.id = :id")
    , @NamedQuery(name = "Producto.findByTitulo", query = "SELECT p FROM Producto p WHERE p.titulo = :titulo")
    , @NamedQuery(name = "Producto.findByDescripcion", query = "SELECT p FROM Producto p WHERE p.descripcion = :descripcion")
    , @NamedQuery(name = "Producto.findByPrecio", query = "SELECT p FROM Producto p WHERE p.precio = :precio")
    , @NamedQuery(name = "Producto.findByValoracionMedia", query = "SELECT p FROM Producto p WHERE p.valoracionMedia = :valoracionMedia")
    , @NamedQuery(name = "Producto.findByNumeroValoraciones", query = "SELECT p FROM Producto p WHERE p.numeroValoraciones = :numeroValoraciones")
    , @NamedQuery(name = "Producto.findByVendedorId", query = "SELECT p FROM Producto p WHERE p.vendedorId = :vendedorId")})
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "TITULO", length = 45)
    private String titulo;
    @Column(name = "DESCRIPCION", length = 300)
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRECIO")
    private Double precio;
    @Column(name = "VALORACION_MEDIA")
    private Double valoracionMedia;
    @Column(name = "NUMERO_VALORACIONES")
    private Integer numeroValoraciones;
    @JoinColumn(name = "CATEGORIA_ID", referencedColumnName = "ID")
    @ManyToOne
    private Categoria categoriaId;
    @JoinTable(name = "PRODUCTO_PALABRASCLAVE", joinColumns = {
        @JoinColumn(name = "PRODUCTO_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "PALABRACLAVE_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<PalabraClave> palabraClaveList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productoId")
    private List<Valoracion> valoracionList;
    @JoinColumn(name = "VENDEDOR_ID", referencedColumnName = "ID")
    @ManyToOne
    private Usuario vendedorId;
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Column(name = "IMAGEN", length = 100)
    private String imagen;
    @Column(name = "HORA_CREACION")
    @Temporal(TemporalType.TIME)
    private Date horaCreacion;

    public Producto() {
    }

    public Producto(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getValoracionMedia() {
        return valoracionMedia;
    }

    public void setValoracionMedia(Double valoracionMedia) {
        this.valoracionMedia = valoracionMedia;
    }

    public Integer getNumeroValoraciones() {
        return numeroValoraciones;
    }

    public void setNumeroValoraciones(Integer numeroValoraciones) {
        this.numeroValoraciones = numeroValoraciones;
    }

    public Categoria getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Categoria categoriaId) {
        this.categoriaId = categoriaId;
    }

    @XmlTransient
    public List<PalabraClave> getPalabraClaveList() {
        return palabraClaveList;
    }

    public void setPalabraClaveList(List<PalabraClave> palabraClaveList) {
        this.palabraClaveList = palabraClaveList;
    }

    @XmlTransient
    public List<Valoracion> getValoracionList() {
        return valoracionList;
    }

    public void setValoracionList(List<Valoracion> valoracionList) {
        this.valoracionList = valoracionList;
    }

    public Usuario getVendedorId() {
        return vendedorId;
    }

    public void setVendedorId(Usuario vendedorId) {
        this.vendedorId = vendedorId;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    public Date getHoraCreacion() {
        return horaCreacion;
    }

    public void setHoraCreacion(Date horaCreacion) {
        this.horaCreacion = horaCreacion;
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
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tienda.entity.Producto[ id=" + id + " ]";
    }
    
    
    
}
