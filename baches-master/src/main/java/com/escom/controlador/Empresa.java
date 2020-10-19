
package com.escom.controlador;


public class Empresa {
    private int id;
    private String rfc;
    private String nombre;
    private String email;
    private String tel;

    public Empresa(int id, String rfc, String nombre, String email, String tel) {
        this.id = id;
        this.rfc = rfc;
        this.nombre = nombre;
        this.email = email;
        this.tel = tel;
    }

    public int getId() {
        return id;
    }

    public String getRfc() {
        return rfc;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getTel() {
        return tel;
    }
    
    
}
