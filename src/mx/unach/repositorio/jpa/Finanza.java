/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unach.repositorio.jpa;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author javier
 */
@Entity
@Table(name = "finanzas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Finanza.findAll", query = "SELECT f FROM Finanza f")
    , @NamedQuery(name = "Finanza.findById", query = "SELECT f FROM Finanza f WHERE f.id = :id")
    , @NamedQuery(name = "Finanza.findByCantidad", query = "SELECT f FROM Finanza f WHERE f.cantidad = :cantidad")
    , @NamedQuery(name = "Finanza.findByServicionombre", query = "SELECT f FROM Finanza f WHERE f.servicionombre = :servicionombre")})
public class Finanza implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Short id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "cantidad")
    private Double cantidad;
    @Basic(optional = false)
    @Column(name = "servicionombre")
    private String servicionombre;
    @JoinColumn(name = "servicioid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Servicio servicioid;

    public Finanza() {
    }

    public Finanza(Short id) {
        this.id = id;
    }

    public Finanza(Short id, Servicio servicioid, String servicionombre, Double cantidad) {
        this.id = id;
        this.servicioid = servicioid;
        this.cantidad = cantidad;
        this.servicionombre = servicionombre;
    }
    
    public Finanza(Servicio servicioid, String servicionombre, Double cantidad) {
        this.servicioid = servicioid;
        this.cantidad = cantidad;
        this.servicionombre = servicionombre;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public String getServicionombre() {
        return servicionombre;
    }

    public void setServicionombre(String servicionombre) {
        this.servicionombre = servicionombre;
    }

    public Servicio getServicioid() {
        return servicioid;
    }

    public void setServicioid(Servicio servicioid) {
        this.servicioid = servicioid;
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
        if (!(object instanceof Finanza)) {
            return false;
        }
        Finanza other = (Finanza) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.unach.dao.entidades.Finanza[ id=" + id + " ]";
    }
    
}
