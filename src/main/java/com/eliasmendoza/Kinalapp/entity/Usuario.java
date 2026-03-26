package com.eliasmendoza.Kinalapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Usuarios")
public class Usuario {
    @Id
    @Column(name = "codigo_usuario")
    private String codigoUsuario;
    @Column
    private String nombreUsuario;
    @Column
    private String passwordUsuario;
    @Column
    private String emailUsuario;
    @Column
    private String rolUsuario;
    @Column
    private int estado;

    public Usuario(){
    }

    public Usuario(String codigoUsuario, String nombreUsuario, String passwordUsuario, String emailUsuario, String rolUsuario, int estado){
        this.codigoUsuario = codigoUsuario;
        this.nombreUsuario = nombreUsuario;
        this.passwordUsuario = passwordUsuario;
        this.emailUsuario = emailUsuario;
        this.rolUsuario = rolUsuario;
        this.estado = estado;
    }

    public String getCodigoUsuario() { return codigoUsuario; }

    public void setCodigoUsuario(String codigoUsuario) { this.codigoUsuario = codigoUsuario; }

    public String getNombreUsuario() { return nombreUsuario; }

    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }

    public String getPasswordUsuario() { return passwordUsuario; }

    public void setPasswordUsuario(String passwordUsuario) { this.passwordUsuario = passwordUsuario; }

    public String getEmailUsuario() { return emailUsuario; }

    public void setEmailUsuario(String emailUsuario) { this.emailUsuario = emailUsuario; }

    public String getRolUsuario() { return rolUsuario; }

    public void setRolUsuario(String rolUsuario) { this.rolUsuario = rolUsuario; }

    public int getEstado() { return estado; }

    public void setEstado(int estado) { this.estado = estado; }
}
