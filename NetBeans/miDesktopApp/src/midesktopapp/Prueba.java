/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midesktopapp;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Eugenio
 */
@Entity
@Table(name = "prueba", catalog = "AdventureWorks", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Prueba.findAll", query = "SELECT p FROM Prueba p"),
    @NamedQuery(name = "Prueba.findByIDEmpleado", query = "SELECT p FROM Prueba p WHERE p.iDEmpleado = :iDEmpleado"),
    @NamedQuery(name = "Prueba.findByVacacionesPendientes", query = "SELECT p FROM Prueba p WHERE p.vacacionesPendientes = :vacacionesPendientes"),
    @NamedQuery(name = "Prueba.findByAjusteVacaciones", query = "SELECT p FROM Prueba p WHERE p.ajusteVacaciones = :ajusteVacaciones"),
    @NamedQuery(name = "Prueba.findByDireccion", query = "SELECT p FROM Prueba p WHERE p.direccion = :direccion"),
    @NamedQuery(name = "Prueba.findByCiudad", query = "SELECT p FROM Prueba p WHERE p.ciudad = :ciudad")})
public class Prueba implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID Empleado")
    private Integer iDEmpleado;
    @Basic(optional = false)
    @Column(name = "Vacaciones Pendientes")
    private String vacacionesPendientes;
    @Basic(optional = false)
    @Column(name = "Ajuste Vacaciones")
    private String ajusteVacaciones;
    @Basic(optional = false)
    @Column(name = "Direccion")
    private String direccion;
    @Basic(optional = false)
    @Column(name = "Ciudad")
    private String ciudad;

    public Prueba() {
    }

    public Prueba(Integer iDEmpleado) {
        this.iDEmpleado = iDEmpleado;
    }

    public Prueba(Integer iDEmpleado, String vacacionesPendientes, String ajusteVacaciones, String direccion, String ciudad) {
        this.iDEmpleado = iDEmpleado;
        this.vacacionesPendientes = vacacionesPendientes;
        this.ajusteVacaciones = ajusteVacaciones;
        this.direccion = direccion;
        this.ciudad = ciudad;
    }

    public Integer getIDEmpleado() {
        return iDEmpleado;
    }

    public void setIDEmpleado(Integer iDEmpleado) {
        Integer oldIDEmpleado = this.iDEmpleado;
        this.iDEmpleado = iDEmpleado;
        changeSupport.firePropertyChange("IDEmpleado", oldIDEmpleado, iDEmpleado);
    }

    public String getVacacionesPendientes() {
        return vacacionesPendientes;
    }

    public void setVacacionesPendientes(String vacacionesPendientes) {
        String oldVacacionesPendientes = this.vacacionesPendientes;
        this.vacacionesPendientes = vacacionesPendientes;
        changeSupport.firePropertyChange("vacacionesPendientes", oldVacacionesPendientes, vacacionesPendientes);
    }

    public String getAjusteVacaciones() {
        return ajusteVacaciones;
    }

    public void setAjusteVacaciones(String ajusteVacaciones) {
        String oldAjusteVacaciones = this.ajusteVacaciones;
        this.ajusteVacaciones = ajusteVacaciones;
        changeSupport.firePropertyChange("ajusteVacaciones", oldAjusteVacaciones, ajusteVacaciones);
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        String oldDireccion = this.direccion;
        this.direccion = direccion;
        changeSupport.firePropertyChange("direccion", oldDireccion, direccion);
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        String oldCiudad = this.ciudad;
        this.ciudad = ciudad;
        changeSupport.firePropertyChange("ciudad", oldCiudad, ciudad);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDEmpleado != null ? iDEmpleado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prueba)) {
            return false;
        }
        Prueba other = (Prueba) object;
        if ((this.iDEmpleado == null && other.iDEmpleado != null) || (this.iDEmpleado != null && !this.iDEmpleado.equals(other.iDEmpleado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "midesktopapp.Prueba[iDEmpleado=" + iDEmpleado + "]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
