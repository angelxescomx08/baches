package com.escom.accesoDatos;

import com.escom.controlador.Ciudadano;
import com.escom.controlador.Empresa;
import com.escom.controlador.Reporte;
import com.escom.controlador.Usuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {

    private static String SQL_INSERT = "insert into ciudadano(nombre,apellidop,apellidom,email,pass)values(?,?,?,?,?)";
    private static String SQL_INSERT_EMPRESA = "insert into empresa(rfc,nombre,email,tel)values(?,?,?,?)";
    private static String SQL_INSERT_REPORTE = "insert into reporte(fecha,calle,colonia,alcaldia,cp,referencia,estado,id_ciudadano)values(?,?,?,?,?,?,?,?)";
    private static String SQL_UPDATE = "update Evento set nombreEvento = ?,sedeEvento = ?,duracion = ?,fechaInicio = ?,fechaTermino = ? where idEvento = ?";
    private static String SQL_DELETE = "delete from Evento where idEvento = ?";
    private static String SQL_SELECTALL = "select * from Evento";
    private static String SQL_SELECT_USER_ADMIN = "select * from administrador where email= ?";
    private static String SQL_SELECT_USER_CIUDADANO = "select * from ciudadano where email= ?";
    private static String SQL_SELECT_USER_CIUDADANO_ID = "select * from ciudadano where id= ?";
    private static String SQL_SELECT_REPORTE = "select * from reporte where id_ciudadano = ?";
    private static String SQL_SELECT_REPORTES_CREADOS = "select * from reporte ";
    private static String SQL_SELECT_EMPRESAS = "select * from empresa ";
    private static String SQL_SELECT_EMPRESA_RFC = "select * from empresa where rfc= ? ";
    private static String SQL_SELECT_EMPRESA_ID = "select * from empresa where id= ? ";
    private static String SQL_UPDATE_REPORTE = "update reporte set fecha= ?, calle= ?, colonia= ?, alcaldia= ?, cp= ?, referencia= ?, estado= ?, id_administrador= ?, id_empresa= ? where id= ? ";
    private static String SQL_SELECT_ADMIN_ID = "select * from administrador where id = ?";
    
    private Connection conexion = null;

    private void obtenerConexion() {
        String usr = "root";
        String pwd = "Ang0445531049458";
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/baches?serverTimezone=America/Mexico_City&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&useSSL=false";
        try {
            Class.forName(driver);
            conexion = DriverManager.getConnection(url, usr, pwd);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String registrarCiudadano(Ciudadano usr) {
        obtenerConexion();
        try {
            PreparedStatement ps = conexion.prepareStatement(SQL_INSERT);
            ps.setString(1, usr.getNombre());
            ps.setString(2, usr.getApellidop());
            ps.setString(3, usr.getApellidom());
            ps.setString(4, usr.getEmail());
            ps.setString(5, usr.getPass());
            ps.executeUpdate();
            conexion.close();
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getMessage();
        }

    }

    public List buscarUsuario(String usr, String pass) {
        obtenerConexion();
        try {
            PreparedStatement ps = conexion.prepareStatement(SQL_SELECT_USER_ADMIN);
            ps.setString(1, usr);
            ResultSet rs = ps.executeQuery();
            List results = obtenerResultados(rs);
            if(results.isEmpty()){
                ps = conexion.prepareStatement(SQL_SELECT_USER_CIUDADANO);
                ps.setString(1, usr);
                rs = ps.executeQuery();
                results = obtenerResultados(rs);
                if(results.isEmpty()){
                    results.add("0");
                    results.add("0");
                    return results;
                }
                if(((Usuario)(results.get(0))).getPass().equals(pass)){
                    results.add("1");
                    return results;
                }
                results.add("0");
                return results;
            }
            if(((Usuario)(results.get(0))).getPass().equals(pass)){
                results.add("2");
                return results;
            }
            results.add("0");
            return results;
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            List results = new ArrayList();
            results.add(ex.getMessage());
            return results;
        }
    }
    private List obtenerResultados(ResultSet rs) throws SQLException{
        List results = new ArrayList();
        while(rs.next()){
            Usuario usr = new Usuario(rs.getInt("id"),rs.getString("nombre"),rs.getString("apellidop"),rs.getString("apellidom"),rs.getString("email"),rs.getString("pass"));
            results.add(usr);
        }
        return results;
    }
    public String guardarReporte(String calle, String colonia, String alcaldia, String cp, String ref,int id){
        obtenerConexion();
        try {
            PreparedStatement ps = conexion.prepareStatement(SQL_INSERT_REPORTE);
            ps.setDate(1, Date.valueOf(java.time.LocalDate.now()));
            ps.setString(2,calle);
            ps.setString(3,colonia);
            ps.setString(4,alcaldia);
            ps.setString(5,cp);
            ps.setString(6,ref);
            ps.setString(7,"Creado");
            ps.setInt(8,id);
            ps.executeUpdate();
            conexion.close();
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getMessage();
        }
    }
    public List listaReportes(int id){
        obtenerConexion();
        PreparedStatement ps;
        try {
            ps = conexion.prepareStatement(SQL_SELECT_REPORTE);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            List reports = obtenerReportes(rs);
            return reports;
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            List e = new ArrayList();
            e.add(ex.getMessage());
            return e;
        }
        
    }
    public List listaReportesCreados(){
        obtenerConexion();
        PreparedStatement ps;
        try {
            ps = conexion.prepareStatement(SQL_SELECT_REPORTES_CREADOS);

            ResultSet rs = ps.executeQuery();
            List reports = obtenerReportes(rs);
            return reports;
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            List e = new ArrayList();
            e.add(ex.getMessage());
            return e;
        }
    }
    private List obtenerReportes(ResultSet rs) throws SQLException{
        List reports = new ArrayList();
        while(rs.next()){
            Reporte r = new Reporte(rs.getInt("id"),
                    rs.getDate("fecha"),
                    rs.getString("calle"),
                    rs.getString("colonia"),
                    rs.getString("alcaldia"),
                    rs.getString("cp"),
                    rs.getString("referencia"),
                    rs.getString("estado"),
                    rs.getInt("id_administrador"),
                    rs.getInt("id_empresa"),
                    rs.getInt("id_ciudadano"));
            reports.add(r);
        }
        return reports;
    }
    public Usuario buscarUsuarioId(int id){
        obtenerConexion();
        PreparedStatement ps;
        try {
            ps = conexion.prepareStatement(SQL_SELECT_USER_CIUDADANO_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            List ciud = obtenerResultados(rs);
            if(ciud.isEmpty()){
                return null;
            }
            return (Usuario)ciud.get(0);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    public String registrarEmpresa(String rfc,String nombre, String email,String tel){
        obtenerConexion();
        try {
            PreparedStatement ps = conexion.prepareStatement(SQL_INSERT_EMPRESA);
            ps.setString(1, rfc);
            ps.setString(2, nombre);
            ps.setString(3, email);
            ps.setString(4, tel);
            ps.executeUpdate();
            conexion.close();
            return null;
        } catch (SQLException ex) {
            return ex.getMessage();
        }
    }
    public List ListaEmpresas(){
        obtenerConexion();
        try {
            PreparedStatement ps = conexion.prepareStatement(SQL_SELECT_EMPRESAS);
            ResultSet rs = ps.executeQuery();
            List emps = obtenerEmpresas(rs);
            return emps;
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            List e = new ArrayList();
            e.add(ex.getMessage());
            return e;
        }
        
    }
    private List obtenerEmpresas(ResultSet rs) throws SQLException{
        List emps = new ArrayList();
        while(rs.next()){
            Empresa em = new Empresa(rs.getInt("id"),
            rs.getString("rfc"),
            rs.getString("nombre"),
            rs.getString("email"),
            rs.getString("tel")
            );
            emps.add(em);
        }
        return emps;
    }
    public Empresa buscarEmpresaRfc(String rfc){
        obtenerConexion();
        try {
            PreparedStatement ps = conexion.prepareStatement(SQL_SELECT_EMPRESA_RFC);
            ps.setString(1, rfc);
            ResultSet rs = ps.executeQuery();
            List emp = obtenerEmpresas(rs);
            return (Empresa)emp.get(0);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    public Empresa buscarEmpresaId(int id){
        obtenerConexion();
        try {
            PreparedStatement ps = conexion.prepareStatement(SQL_SELECT_EMPRESA_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            List emp = obtenerEmpresas(rs);
            return (Empresa)emp.get(0);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    public String actualizarReporte(Reporte r){
        obtenerConexion();
        try {
            PreparedStatement ps = conexion.prepareStatement(SQL_UPDATE_REPORTE);
            ps.setDate(1, r.getFecha());
            ps.setString(2, r.getCalle());
            ps.setString(3, r.getColonia());
            ps.setString(4, r.getAlcaldia());
            ps.setString(5, r.getCp());
            ps.setString(6, r.getReferencia());
            ps.setString(7, r.getStatus());
            ps.setInt(8, r.getIdAdmin());
            ps.setInt(9, r.getIdEmpresa());
            ps.setInt(10, r.getId());
            ps.executeUpdate();
            System.out.println(ps.toString());
            conexion.close();
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getMessage();
        }
        
    }
    public Usuario buscarAdminId(int id){
        obtenerConexion();
        PreparedStatement ps;
        try {
            ps = conexion.prepareStatement(SQL_SELECT_ADMIN_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            List users = obtenerResultados(rs);
            return (Usuario)users.get(0);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    public static void main(String[] args) {

    }
}
