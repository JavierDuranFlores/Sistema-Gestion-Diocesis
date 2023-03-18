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
@Table(name = "coros")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Coro.findAll", query = "SELECT c FROM Coro c")
    , @NamedQuery(name = "Coro.findById", query = "SELECT c FROM Coro c WHERE c.id = :id")
    , @NamedQuery(name = "Coro.findByNombre", query = "SELECT c FROM Coro c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Coro.findByMiembros", query = "SELECT c FROM Coro c WHERE c.miembros = :miembros")
    , @NamedQuery(name = "Coro.findByConductor", query = "SELECT c FROM Coro c WHERE c.conductor = :conductor")
    , @NamedQuery(name = "Coro.findByPianistaprincipal", query = "SELECT c FROM Coro c WHERE c.pianistaprincipal = :pianistaprincipal")
    , @NamedQuery(name = "Coro.findByCreado", query = "SELECT c FROM Coro c WHERE c.creado = :creado")
    , @NamedQuery(name = "Coro.numeroCoros", query = "SELECT COUNT(c) FROM Coro c")
    , @NamedQuery(name = "Coro.findIdMax", query = "SELECT MAX(c.id) FROM Coro c")})
public class Coro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Short id;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "miembros")
    private short miembros;
    @Basic(optional = false)
    @Column(name = "conductor")
    private String conductor;
    @Basic(optional = false)
    @Column(name = "pianistaprincipal")
    private String pianistaprincipal;
    @Column(name = "creado")
    @Temporal(TemporalType.DATE)
    private Date creado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "coroid")
    private List<Servicio> servicioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "coroid")
    private List<Misa> misaList;

    public Coro() {
    }

    public Coro(Short id) {
        this.id = id;
    }

    public Coro(Short id, String nombre, short miembros, String conductor, String pianistaprincipal, Date creado) {
        this.id = id;
        this.nombre = nombre;
        this.miembros = miembros;
        this.conductor = conductor;
        this.pianistaprincipal = pianistaprincipal;
        this.creado = creado;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public short getMiembros() {
        return miembros;
    }

    public void setMiembros(short miembros) {
        this.miembros = miembros;
    }

    public String getConductor() {
        return conductor;
    }

    public void setConductor(String conductor) {
        this.conductor = conductor;
    }

    public String getPianistaprincipal() {
        return pianistaprincipal;
    }

    public void setPianistaprincipal(String pianistaprincipal) {
        this.pianistaprincipal = pianistaprincipal;
    }

    public Date getCreado() {
        return creado;
    }

    public void setCreado(Date creado) {
        this.creado = creado;
    }

    @XmlTransient
    public List<Servicio> getServicioList() {
        return servicioList;
    }

    public void setServicioList(List<Servicio> servicioList) {
        this.servicioList = servicioList;
    }

    @XmlTransient
    public List<Misa> getMisaList() {
        return misaList;
    }

    public void setMisaList(List<Misa> misaList) {
        this.misaList = misaList;
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
        if (!(object instanceof Coro)) {
            return false;
        }
        Coro other = (Coro) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.unach.dao.entidades.Coro[ id=" + id + " ]";
    }
    
}
