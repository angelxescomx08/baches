
package com.escom.controlador;


public class Usuario {
    private int id;
    private String nombre;
    private String apellidop;
    private String apellidom;
    private String email;
    private String pass;
    public Usuario(String nombre, String apellidop, String apellidom,
            String email, String pass) {
        this.nombre = nombre;
        this.apellidop = apellidop;
        this.apellidom = apellidom;
        this.email = email;
        this.pass = pass;
    }

    public Usuario(int id, String nombre, String apellidop, String apellidom,
            String email, String pass) {
        this.id = id;
        this.nombre = nombre;
        this.apellidop = apellidop;
        this.apellidom = apellidom;
        this.email = email;
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidop() {
        return apellidop;
    }

    public String getApellidom() {
        return apellidom;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }
    public Ciudadano converToCiudadano(){
        Ciudadano cid = new Ciudadano(this.id,this.nombre,this.apellidop,this.apellidom,this.email,this.pass);
        return cid;
    }
    
}
