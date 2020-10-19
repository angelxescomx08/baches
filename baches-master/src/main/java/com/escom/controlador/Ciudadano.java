package com.escom.controlador;

public class Ciudadano extends Usuario {

    public Ciudadano(String nombre, String apellidop, String apellidom, String email, String pass) {
        super(nombre, apellidop, apellidom, email, pass);
    }

    public Ciudadano(int id, String nombre, String apellidop, String apellidom, String email, String pass) {
        super(id, nombre, apellidop, apellidom, email, pass);
    }

    

}
