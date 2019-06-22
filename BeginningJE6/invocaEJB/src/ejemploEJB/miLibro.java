/*
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemploEJB;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author e.garcia.san.martin
 */

@Entity(name="libro")
@NamedQueries({
        @NamedQuery(name = "encuentraTodos", query = "SELECT b FROM libro b"),
        @NamedQuery(name = "encuentraISBN", query = "SELECT b FROM libro b where b.isbn= :isbn")
})
public class miLibro implements Serializable  {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String titulo;
    private Float precio;
    @Column(length = 2000)
    private String descripcion;
    private String isbn;

    public miLibro()
    {
    
    }
    
    public miLibro(String titulo, Float precio, String descripcion, String isbn) {
        this.titulo = titulo;
        this.precio = precio;
        this.descripcion = descripcion;
        this.isbn = isbn;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

        public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String title) {
        this.titulo = title;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float price) {
        this.precio= price;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String description) {
        this.descripcion= description;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
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
        if (!(object instanceof miLibro)) {
            return false;
        }
        miLibro other = (miLibro) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Libro");
        sb.append("{id=").append(id);
        sb.append(", titulo='").append(titulo).append('\'');
        sb.append(", precio=").append(precio);
        sb.append(", descripcion='").append(descripcion).append('\'');
        sb.append(", isbn='").append(isbn).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
