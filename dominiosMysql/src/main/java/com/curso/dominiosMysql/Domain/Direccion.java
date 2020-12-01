package com.curso.dominiosMysql.Domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "direcciones")
public class Direccion implements Serializable {
    @Id
    private Integer id;
    private String provincia;
    private String direccion;


   /* @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente")*/
    private Integer idcliente;

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public Integer getId() {
        return id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String poblacion) {
        this.provincia = poblacion;
    }


}
