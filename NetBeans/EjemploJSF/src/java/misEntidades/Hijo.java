/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package misEntidades;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Eugenio
 */
@Entity
@Table(name = "prueba")
@NamedQueries({
    @NamedQuery(name = "Hijo.findAll", query = "SELECT h FROM Hijo h"),
    @NamedQuery(name = "Hijo.findByIdprueba", query = "SELECT h FROM Hijo h WHERE h.idprueba = :idprueba"),
    @NamedQuery(name = "Hijo.findByNombre", query = "SELECT h FROM Hijo h WHERE h.nombre = :nombre"),
    @NamedQuery(name = "Hijo.findByCumple", query = "SELECT h FROM Hijo h WHERE h.cumple = :cumple")})
public class Hijo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idprueba", nullable = false)
    private Integer idprueba;
    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;
    @Column(name = "cumple")
    @Temporal(TemporalType.DATE)
    private Date cumple;
    @JoinColumn(name = "estado", referencedColumnName = "idpadre")
    @ManyToOne
    private Padre estado;

    public Hijo() {
    }

    public Hijo(Integer idprueba) {
        this.idprueba = idprueba;
    }

    public Hijo(Integer idprueba, String nombre) {
        this.idprueba = idprueba;
        this.nombre = nombre;
    }

    public Integer getIdprueba() {
        return idprueba;
    }

    public void setIdprueba(Integer idprueba) {
        this.idprueba = idprueba;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getCumple() {
        return cumple;
    }

    public void setCumple(Date cumple) {
        this.cumple = cumple;
    }

    public Padre getEstado() {
        return estado;
    }

    public void setEstado(Padre estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprueba != null ? idprueba.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hijo)) {
            return false;
        }
        Hijo other = (Hijo) object;
        if ((this.idprueba == null && other.idprueba != null) || (this.idprueba != null && !this.idprueba.equals(other.idprueba))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "misEntidades.Hijo[idprueba=" + idprueba + "]";
    }

}
