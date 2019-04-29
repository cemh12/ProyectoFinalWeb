package Entidades;
import org.h2.util.DateTimeUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class VisitaDia {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public long codigo;

    public int dia;

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public DireccionURL getUrls() {
        return urls;
    }

    public void setUrls(DireccionURL urls) {
        this.urls = urls;
    }

    public int mes;

    public VisitaDia() {

    }

    public VisitaDia(int dia, int mes, int anio, int contador, DireccionURL urls) {
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
        this.contador = contador;
        this.urls = urls;
    }

    public int anio;

    public int contador;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    public DireccionURL urls;
}
