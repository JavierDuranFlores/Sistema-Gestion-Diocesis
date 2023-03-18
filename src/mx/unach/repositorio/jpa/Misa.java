/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unach.repositorio.jpa;

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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author javier
 */
@Entity
@Table(name = "misas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Misa.findAll", query = "SELECT m FROM Misa m")
    , @NamedQuery(name = "Misa.findById", query = "SELECT m FROM Misa m WHERE m.id = :id")
    , @NamedQuery(name = "Misa.findByDia", query = "SELECT m FROM Misa m WHERE m.dia = :dia")
    , @NamedQuery(name = "Misa.findByHora", query = "SELECT m FROM Misa m WHERE m.hora = :hora")})
public class Misa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Short id;
    @Basic(optional = false)
    @Column(name = "dia")
    @Temporal(TemporalType.DATE)
    private Date dia;
    @Basic(optional = false)
    @Column(name = "hora")
    private String hora;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "misaid")
    private List<Servicio> servicioList;
    @JoinColumn(name = "coroid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Coro coroid;
    @JoinColumn(name = "sacerdoteid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Sacerdote sacerdoteid;

    public Misa() {
    }

    public Misa(Short id) {
        this.id = id;
    }

    public Misa(Short id, Date dia, String hora, Sacerdote sacerdoteId, Coro coroId) {
        this.id = id;
        this.dia = dia;
        this.hora = hora;
        this.sacerdoteid = sacerdoteId;
        this.coroid = coroId;
    }
    
    public Misa (Date dia, String hora, Sacerdote sacerdoteId, Coro coroId) {
        this.dia = dia;
        this.hora = hora;
        this.sacerdoteid = sacerdoteId;
        this.coroid = coroId;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
    
    public Coro getCoroid() {
        return coroid;
    }

    public void setCoroid(Coro coroid) {
        this.coroid = coroid;
    }

    public Sacerdote getSacerdoteid() {
        return sacerdoteid;
    }

    public void setSacerdoteid(Sacerdote sacerdoteid) {
        this.sacerdoteid = sacerdoteid;
    }

    public void setServicioList(List<Servicio> servicioList) {
        this.servicioList = servicioList;
    }
    
    public List<Servicio> getServicioList() {
        return servicioList;
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
        if (!(object instanceof Misa)) {
            return false;
        }
        Misa other = (Misa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.unach.repositorio.jpa.Misa[ id=" + id + " ]";
    }
    
}
