/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unach.repositorio.jpa;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import mx.unach.repositorio.jpa.Coro;
import mx.unach.repositorio.jpa.Finanza;
import mx.unach.repositorio.jpa.Misa;
import mx.unach.repositorio.jpa.Sacerdote;

/**
 *
 * @author javier
 */
@Entity
@Table(name = "servicios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Servicio.findAll", query = "SELECT s FROM Servicio s")
    , @NamedQuery(name = "Servicio.findById", query = "SELECT s FROM Servicio s WHERE s.id = :id")
    , @NamedQuery(name = "Servicio.findByNombre", query = "SELECT s FROM Servicio s WHERE s.nombre = :nombre")
    , @NamedQuery(name = "Servicio.findByTelefono", query = "SELECT s FROM Servicio s WHERE s.telefono = :telefono")
    , @NamedQuery(name = "Servicio.findBySacerdotenombre", query = "SELECT s FROM Servicio s WHERE s.sacerdotenombre = :sacerdotenombre")
    , @NamedQuery(name = "Servicio.findByCoronombre", query = "SELECT s FROM Servicio s WHERE s.coronombre = :coronombre")
    , @NamedQuery(name = "Servicio.findByLectura", query = "SELECT s FROM Servicio s WHERE s.lectura = :lectura")
    , @NamedQuery(name = "Servicio.numeroServicios", query = "SELECT COUNT(s) FROM Servicio s")})
public class Servicio implements Serializable {

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
    @Column(name = "telefono")
    private String telefono;
    @Basic(optional = false)
    @Column(name = "sacerdotenombre")
    private String sacerdotenombre;
    @Basic(optional = false)
    @Column(name = "coronombre")
    private String coronombre;
    @Basic(optional = false)
    @Column(name = "lectura")
    private String lectura;
    @JoinColumn(name = "coroid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Coro coroid;
    @JoinColumn(name = "misaid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Misa misaid;
    @JoinColumn(name = "sacerdoteid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Sacerdote sacerdoteid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "servicioid")
    private List<Finanza> finanzasList;

    public Servicio() {
    }

    public Servicio(Short id) {
        this.id = id;
    }

    public Servicio(Short id, String nombre, String telefono, Sacerdote sacerdoteId, String sacerdotenombre, Coro coroId, String coronombre, String lectura, Misa misaId) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.sacerdoteid = sacerdoteId;
        this.sacerdotenombre = sacerdotenombre;
        this.coroid = coroId;
        this.coronombre = coronombre;
        this.lectura = lectura;
        this.misaid = misaId;
    }
    
    public Servicio(String nombre, Sacerdote sacerdoteId, String sacerdotenombre, Coro coroId, String coronombre, String lectura, Misa misaId) {
        this.nombre = nombre;
        //this.telefono = telefono;
        this.sacerdoteid = sacerdoteId;
        this.sacerdotenombre = sacerdotenombre;
        this.coroid = coroId;
        this.coronombre = coronombre;
        this.lectura = lectura;
        this.misaid = misaId;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getSacerdotenombre() {
        return sacerdotenombre;
    }

    public void setSacerdotenombre(String sacerdotenombre) {
        this.sacerdotenombre = sacerdotenombre;
    }

    public String getCoronombre() {
        return coronombre;
    }

    public void setCoronombre(String coronombre) {
        this.coronombre = coronombre;
    }

    public String getLectura() {
        return lectura;
    }

    public void setLectura(String lectura) {
        this.lectura = lectura;
    }

    public Coro getCoroid() {
        return coroid;
    }

    public void setCoroid(Coro coroid) {
        this.coroid = coroid;
    }

    public Misa getMisaid() {
        return misaid;
    }

    public void setMisaid(Misa misaid) {
        this.misaid = misaid;
    }

    public Sacerdote getSacerdoteid() {
        return sacerdoteid;
    }

    public void setSacerdoteid(Sacerdote sacerdoteid) {
        this.sacerdoteid = sacerdoteid;
    }

    @XmlTransient
    public List<Finanza> getFinanzasList() {
        return finanzasList;
    }

    public void setFinanzasList(List<Finanza> finanzasList) {
        this.finanzasList = finanzasList;
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
        if (!(object instanceof Servicio)) {
            return false;
        }
        Servicio other = (Servicio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

}
