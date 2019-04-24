package Entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Usuario implements Serializable {
    @Id
    private String usuario;
    private String nombre;
    private String email;

    @Column(columnDefinition = "LONGTEXT")
    private String password;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
    private Set<DireccionURL> DireccionesURL;

    private String rol;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Set<DireccionURL> getDireccionesURL() {
        return DireccionesURL;
    }

    public void setDireccionesURL(Set<DireccionURL> DireccionesURL) {
        this.DireccionesURL = DireccionesURL;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getpassword() {
        return password;
    }

    public void setpassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
