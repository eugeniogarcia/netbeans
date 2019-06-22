/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package misEntidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Eugenio
 */
@Entity
@Table(name = "padre")
@NamedQueries({
    @NamedQuery(name = "Padre.findAll", query = "SELECT p FROM Padre p"),
    @NamedQuery(name = "Padre.findByIdpadre", query = "SELECT p FROM Padre p WHERE p.idpadre = :idpadre"),
    @NamedQuery(name = "Padre.findByDescripcion", query = "SELECT p FROM Padre p WHERE p.descripcion = :descripcion")})
public class Padre implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpadre", nullable = false)
    private Integer idpadre;
    @Column(name = "descripcion", length = 45)
    private String descripcion;
    @OneToMany(mappedBy = "estado")
    private Collection<Hijo> hijoCollection;

    public Padre() {
    }

    public Padre(Integer idpadre) {
        this.idpadre = idpadre;
    }

    public Integer getIdpadre() {
        return idpadre;
    }

    public void setIdpadre(Integer idpadre) {
        this.idpadre = idpadre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Collection<Hijo> getHijoCollection() {
        return hijoCollection;
    }

    public void setHijoCollection(Collection<Hijo> hijoCollection) {
        this.hijoCollection = hijoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpadre != null ? idpadre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Padre)) {
            return false;
        }
        Padre other = (Padre) object;
        if ((this.idpadre == null && other.idpadre != null) || (this.idpadre != null && !this.idpadre.equals(other.idpadre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "misEntidades.Padre[idpadre=" + idpadre + "]";
    }

}
