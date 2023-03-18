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
@Table(name = "sacerdotes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sacerdote.findAll", query = "SELECT s FROM Sacerdote s")
    , @NamedQuery(name = "Sacerdote.findById", query = "SELECT s FROM Sacerdote s WHERE s.id = :id")
    , @NamedQuery(name = "Sacerdote.findByNombre", query = "SELECT s FROM Sacerdote s WHERE s.nombre = :nombre")
    , @NamedQuery(name = "Sacerdote.findByNacimiento", query = "SELECT s FROM Sacerdote s WHERE s.nacimiento = :nacimiento")
    , @NamedQuery(name = "Sacerdote.findByDireccion", query = "SELECT s FROM Sacerdote s WHERE s.direccion = :direccion")
    , @NamedQuery(name = "Sacerdote.findByTelefono", query = "SELECT s FROM Sacerdote s WHERE s.telefono = :telefono")
    , @NamedQuery(name = "Sacerdote.findByTiempoministerio", query = "SELECT s FROM Sacerdote s WHERE s.tiempoministerio = :tiempoministerio")
    , @NamedQuery(name = "Sacerdote.findByContra", query = "SELECT s FROM Sacerdote s WHERE s.contra = :contra")
    , @NamedQuery(name = "Sacerdote.findIdMax", query = "SELECT MAX(s.id) FROM Sacerdote s")
    , @NamedQuery(name = "Sacerdote.numeroSacerdotes", query = "SELECT COUNT(s) FROM Sacerdote s")
    , @NamedQuery(name = "Sacerdote.findByNombreAndContra", query = "SELECT s FROM Sacerdote s WHERE s.nombre = :nombre AND s.contra = :contra")})
public class Sacerdote implements Serializable {

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
    @Column(name = "nacimiento")
    @Temporal(TemporalType.DATE)
    private Date nacimiento;
    @Basic(optional = false)
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @Column(name = "telefono")
    private String telefono;
    @Basic(optional = false)
    @Column(name = "tiempoministerio")
    private short tiempoministerio;
    @Basic(optional = false)
    @Column(name = "contra")
    private String contra;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sacerdoteid")
    private List<Servicio> servicioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sacerdoteid")
    private List<Misa> misaList;

    public Sacerdote() {
    }

    public Sacerdote(Short id) {
        this.id = id;
    }

    public Sacerdote(Short id, String nombre, Date nacimiento, String direccion, String telefono, short tiempoministerio, String contra) {
        this.id = id;
        this.nombre = nombre;
        this.nacimiento = nacimiento;
        this.direccion = direccion;
        this.telefono = telefono;
        this.tiempoministerio = tiempoministerio;
        this.contra = contra;
    }

    public Sacerdote(String nombre, Date nacimiento, String direccion, String telefono, short tiempoministerio, String contra) {
        this.nombre = nombre;
        this.nacimiento = nacimiento;
        this.direccion = direccion;
        this.telefono = telefono;
        this.tiempoministerio = tiempoministerio;
        this.contra = contra;
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

    public Date getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public short getTiempoministerio() {
        return tiempoministerio;
    }

    public void setTiempoministerio(short tiempoministerio) {
        this.tiempoministerio = tiempoministerio;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
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
        if (!(object instanceof Sacerdote)) {
            return false;
        }
        Sacerdote other = (Sacerdote) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.unach.dao.entidades.Sacerdote[ id=" + id + " ]";
    }

}
