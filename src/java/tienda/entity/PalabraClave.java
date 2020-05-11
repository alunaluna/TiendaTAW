/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author eugenio
 */
@Entity
@Table(name = "PALABRA_CLAVE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PalabraClave.findAll", query = "SELECT p FROM PalabraClave p")
    , @NamedQuery(name = "PalabraClave.findById", query = "SELECT p FROM PalabraClave p WHERE p.id = :id")
    , @NamedQuery(name = "PalabraClave.findByPalabra", query = "SELECT p FROM PalabraClave p WHERE LOWER(p.palabra) = LOWER(:palabra)")})
public class PalabraClave implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "PALABRA", length=45)
    private String palabra;
    @ManyToMany(mappedBy = "palabraClaveList")
    private List<Producto> productoList;

    public PalabraClave() {
    }

    public PalabraClave(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    @XmlTransient
    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
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
        if (!(object instanceof PalabraClave)) {
            return false;
        }
        PalabraClave other = (PalabraClave) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tienda.entity.PalabraClave[ id=" + id + " ]";
    }
    
}
