
package com.escom.controlador;

import java.sql.Date;

public class Reporte {
    private int id;
    private Date fecha;
    private String calle;
    private String Colonia;
    private String alcaldia;
    private String cp;
    private String referencia;
    private String status;
    private int idAdmin;
    private int idEmpresa;
    private int idCiudadano;

    public Reporte(Date fecha, String calle, String Colonia, String alcaldia, String cp, String referencia, String status,int idCiudadano) {
        this.fecha = fecha;
        this.calle = calle;
        this.Colonia = Colonia;
        this.alcaldia = alcaldia;
        this.cp = cp;
        this.referencia = referencia;
        this.status = status;
        this.idCiudadano = idCiudadano;
    }

    public Reporte(int id, Date fecha, String calle, String Colonia, String alcaldia, String cp, String referencia, String status, int idAdmin, int idEmpresa, int idCiudadano) {
        this.id = id;
        this.fecha = fecha;
        this.calle = calle;
        this.Colonia = Colonia;
        this.alcaldia = alcaldia;
        this.cp = cp;
        this.referencia = referencia;
        this.status = status;
        this.idAdmin = idAdmin;
        this.idEmpresa = idEmpresa;
        this.idCiudadano = idCiudadano;
    }

    public int getId() {
        return id;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getCalle() {
        return calle;
    }

    public String getColonia() {
        return Colonia;
    }

    public String getAlcaldia() {
        return alcaldia;
    }

    public String getCp() {
        return cp;
    }

    public String getReferencia() {
        return referencia;
    }

    public String getStatus() {
        return status;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public int getIdCiudadano() {
        return idCiudadano;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public void setColonia(String Colonia) {
        this.Colonia = Colonia;
    }

    public void setAlcaldia(String alcaldia) {
        this.alcaldia = alcaldia;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public void setIdCiudadano(int idCiudadano) {
        this.idCiudadano = idCiudadano;
    }
    
    
}
