package Entidades;
import org.h2.util.DateTimeUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Visita implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public long codigo;

    public String ip;

    public String navegador;

    public String sistema_operativo;

    public Time hora;

    public Date fecha;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    public DireccionURL urls;

    public Time getHora() {
        return hora;
    }
    public Date getFecha() {
        return fecha;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getNavegador() {
        return navegador;
    }

    public void setNavegador(String navegador) {
        this.navegador = navegador;
    }

    public String getSistema_operativo() {
        return sistema_operativo;
    }

    public void setSistema_operativo(String sistema_operativo) {
        this.sistema_operativo = sistema_operativo;
    }


    public DireccionURL getUrlS() {
        return urls;
    }

    public void setUrlS(DireccionURL urls) {
        this.urls = urls;
    }
}
