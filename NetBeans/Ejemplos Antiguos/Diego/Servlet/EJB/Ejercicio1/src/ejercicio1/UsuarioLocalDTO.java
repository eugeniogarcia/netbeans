package ejercicio1;

import java.io.Serializable;

public class UsuarioLocalDTO implements Serializable {
    private String clave;
    private String usuario;

    public UsuarioLocalDTO() {
    }

    public UsuarioLocalDTO(UsuarioLocal usuarioLocal) {
        copyFromEntity(usuarioLocal);
    }

    public void copyFromEntity(UsuarioLocal usuarioLocal) {
        clave = usuarioLocal.getClave();
        usuario = usuarioLocal.getUsuario();
    }

    public void copyToEntity(UsuarioLocal usuarioLocal) {
        usuarioLocal.setClave(clave);
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
