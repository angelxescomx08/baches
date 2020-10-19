
package com.escom.controlador;

import com.escom.accesoDatos.Conexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletActualizarReporte extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Reporte r = new Reporte(Integer.parseInt(request.getParameter("idReporte")),
            Date.valueOf(request.getParameter("fechaReporte")),
            request.getParameter("calleReporte"),
            request.getParameter("coloniaReporte"),
            request.getParameter("alcaldiaReporte"),
            request.getParameter("cpReporte"),
            request.getParameter("referenciaReporte"),
            request.getParameter("statusReporte"),
            Integer.parseInt(request.getParameter("idAdministrador")),
            Integer.parseInt(request.getParameter("idEmpresa")),
            Integer.parseInt(request.getParameter("idCiudadano"))
                    
            );
            Conexion conexion = new Conexion();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">");
            out.println("<title>Servlet ServletActualizarReporte</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<nav class=\"navbar navbar-expand-lg navbar-dark bg-primary\">\n" +
"            \n" +
"            <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarNav\" aria-controls=\"navbarNav\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n" +
"                <span class=\"navbar-toggler-icon\"></span>\n" +
"            </button>\n" +
"            <div class=\"collapse navbar-collapse\" id=\"navbarNav\">\n" +
"                <ul class=\"navbar-nav\">\n" +
"                    <li class=\"nav-item e\">\n" +
"                        <a class=\"nav-link\" href=\"ServletAdmin\">Home</a>\n" +
"                    </li>\n" +
"                    </li>\n" +
"                    <li class=\"nav-item\">\n" +
"                        <a class=\"nav-link\" href=\"ServletListaReportesCreados\">Ver Reportes</a>\n" +
"                    </li>\n" + 
"                    <li class=\"nav-item\">\n" +
"                        <a class=\"nav-link\" href=\"RegistrarEmpresa.html\">Registrar Empresa</a>\n" +
"                    </li>\n" + 
"                    <li class=\"nav-item \">\n" +
"                        <a class=\"nav-link\" href=\"ServletListaEmpresas\">Ver Empresas</a>\n" +
"                    </li>\n" + 
"                </ul>\n" +
"            </div>\n" +
"        </nav>");
            out.println(" <div class = \"w-25 p-3\" >");
            out.println("<div class=\"form-group\">\n" +
"                    <form action='ServletActualizacion' method='POST'>" +
"                <label>Fecha: "+r.getFecha()+"</label><br/>\n" +
"                <label>Calle: "+r.getCalle()+"</label><br/>\n" +
"                <label>Colonia: "+r.getColonia()+"</label><br/>\n" +
"                <label>Alcaldia: "+r.getAlcaldia()+"</label><br/>\n" +
"                <label>Codigo Postal: "+r.getCp()+"</label><br/>\n" +
"                <label>Referencia: "+r.getReferencia()+"</label><br/>\n" +
"            ");
            
            
            out.println("<label>Estado: </label><select class=\"form-control\" name='status'>");
            out.println("<option>"+r.getStatus()+"</option>");
            out.println("<option>Creado</option>");
            out.println("<option>Asignado a empresa</option>");
            out.println("<option>Resuelto</option>");
            out.println("</select>");
            
            if(r.getIdEmpresa()==0){
                out.println("</label>RFC empresa:</label><select class=\"form-control\" name='rfc'>");
                out.println("<option>Sin asignar</option>");
                List emps = conexion.ListaEmpresas();
                for(int i =0;i<emps.size();i++){
                    out.println("<option>"+((Empresa)(emps.get(i))).getRfc()+"</option>");
                }
                out.println("</select>");
                
            }
            else{
                Empresa em = conexion.buscarEmpresaId(r.getIdEmpresa());
                out.println("</label>RFC empresa:</label><select class=\"form-control\" name='rfc'>");
                out.println("<option>"+em.getRfc()+"</option>");
                List emps = conexion.ListaEmpresas();
                for(int i =0;i<emps.size();i++){
                    if(em.getRfc().equals(((Empresa)(emps.get(i))).getRfc())){
                        continue;
                    }
                    out.println("<option>"+((Empresa)(emps.get(i))).getRfc()+"</option>");
                }
                out.println("<option>Sin asignar</option>");
                out.println("</select>");
            }
            Usuario usr = conexion.buscarUsuarioId(Integer.parseInt(request.getParameter("idCiudadano")));
            
            out.println("<label>Creado por: "+usr.getEmail()+"    Nombre:  "+usr.getNombre()+" "+usr.getApellidop()+"</label><br/>");
            out.println("<input type='hidden' value='"+r.getId()+"' name='id' >");
            out.println("<input type='hidden' value='"+r.getFecha()+"' name='fecha'> ");
            out.println("<input type='hidden' value='"+r.getCalle()+"' name='calle'>");
            out.println("<input type='hidden' value='"+r.getColonia()+"'name='colonia' >");
            out.println("<input type='hidden' value='"+r.getAlcaldia()+"' name='alcaldia'>");
            out.println("<input type='hidden' value='"+r.getCp()+"' name='cp'>");
            out.println("<input type='hidden' value='"+r.getReferencia()+"' name='ref'>");
            out.println("<input type='hidden' value='"+r.getIdCiudadano()+"' name='idCiudadano'>");
            out.println("<input type='hidden' value='"+r.getIdEmpresa()+"' name='idEmpresa'>");
            out.println("<input type='hidden' value='"+r.getIdAdmin()+"' name='idAdmin'>");
            
            
            out.println("<button type=\"submit\" class=\"btn btn-primary\">Actualizar</button>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
