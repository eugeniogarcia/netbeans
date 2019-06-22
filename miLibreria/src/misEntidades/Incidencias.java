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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Eugenio
 */
@Entity
@Table(name = "INCIDENCIAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Incidencias.findAll", query = "SELECT i FROM Incidencias i"),
    @NamedQuery(name = "Incidencias.findById", query = "SELECT i FROM Incidencias i WHERE i.id = :id"),
    @NamedQuery(name = "Incidencias.findByTitulo", query = "SELECT i FROM Incidencias i WHERE i.titulo = :titulo"),
    @NamedQuery(name = "Incidencias.findByDescripcion", query = "SELECT i FROM Incidencias i WHERE i.descripcion = :descripcion"),
    @NamedQuery(name = "Incidencias.findByCreacion", query = "SELECT i FROM Incidencias i WHERE i.creacion = :creacion"),
    @NamedQuery(name = "Incidencias.findByTs", query = "SELECT i FROM Incidencias i WHERE i.ts = :ts"),
    @NamedQuery(name = "Incidencias.findByEstado", query = "SELECT i FROM Incidencias i WHERE i.estado = :estado")})
public class Incidencias implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "TITULO")
    private String titulo;
    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "CREACION")
    @Temporal(TemporalType.DATE)
    private Date creacion;
    @Basic(optional = false)
    @Column(name = "TS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ts;
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private char estado;

    public Incidencias() {
    }

    public Incidencias(Integer id) {
        this.id = id;
    }

    public Incidencias(Integer id, String titulo, String descripcion, Date creacion, Date ts, char estado) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.creacion = creacion;
        this.ts = ts;
        this.estado = estado;
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

    public Date getCreacion() {
        return creacion;
    }

    public void setCreacion(Date creacion) {
        this.creacion = creacion;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
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
        if (!(object instanceof Incidencias)) {
            return false;
        }
        Incidencias other = (Incidencias) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "misEntidades.Incidencias[ id=" + id + " ]";
    }
    
}
