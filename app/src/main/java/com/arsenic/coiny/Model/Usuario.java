package com.arsenic.coiny.Model;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String nombre;
    private String apellido;
    private String numero;
    private int password;
    private double saldo_sol;
    private double saldo_dol;

    public Usuario(String nombre, String apellido, String numero, int password, double saldo_sol, double saldo_dol) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.numero = numero;
        this.password = password;
        this.saldo_sol = saldo_sol;
        this.saldo_dol = saldo_dol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public double getSaldo_sol() {
        return saldo_sol;
    }

    public void setSaldo_sol(double saldo_sol) {
        this.saldo_sol = saldo_sol;
    }

    public double getSaldo_dol() {
        return saldo_dol;
    }

    public void setSaldo_dol(double saldo_dol) {
        this.saldo_dol = saldo_dol;
    }
}
