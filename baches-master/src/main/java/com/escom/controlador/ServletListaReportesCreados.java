
package com.escom.controlador;

import com.escom.accesoDatos.Conexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletListaReportesCreados extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           HttpSession sesion = request.getSession();
            int id = Integer.parseInt((String)sesion.getAttribute("id"));
            Conexion conexion = new Conexion();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">");
            out.println("<title>Ver Reportes</title>");            
            out.println("</head>");
            out.println("<nav class=\"navbar navbar-expand-lg navbar-dark bg-primary\">\n" +
"            \n" +
"            <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarNav\" aria-controls=\"navbarNav\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n" +
"                <span class=\"navbar-toggler-icon\"></span>\n" +
"            </button>\n" +
"            <div class=\"collapse navbar-collapse\" id=\"navbarNav\">\n" +
"                <ul class=\"navbar-nav\">\n" +
"                    <li class=\"nav-item \">\n" +
"                        <a class=\"nav-link\" href=\"ServletAdmin\">Home</a>\n" +
"                    </li>\n" +
"                    </li>\n" +
"                    <li class=\"nav-item active\">\n" +
"                        <a class=\"nav-link\" href=\"ServletListaReportesCreados\">Ver Reportes</a>\n" +
"                    </li>\n" + 
"                    <li class=\"nav-item\">\n" +
"                        <a class=\"nav-link\" href=\"RegistrarEmpresa.html\">Registrar Empresa</a>\n" +
"                    </li>\n" + 
"                    <li class=\"nav-item \">\n" +
"                        <a class=\"nav-link\" href=\"ServletListaEmpresas\">Ver Empresas</a>\n" +
"                    </li>\n" + 
                    "<li class=\"nav-item\">\n"+
                         "<a class=\"nav-link\" href=\"index.html\">Salir</a>\n" +
"                    </li>\n" +
"                </ul>\n" +
"            </div>\n" +
"        </nav>");
            out.println("<body>");
            List reports = conexion.listaReportesCreados();
            if(reports.isEmpty()){
                out.println("<h1>No se ha generado ningun reporte</h1>");
            }
            else{
                out.println("<table style=\"width:100%\">"
            +   "<th>Creado por</th>"
            +   "<th>Fecha</th>"
            +   "<th>Calle</th>"
            +   "<th>Colonia</th>"
            +   "<th>Alcaldia</th>"
            +   "<th>Codigo Postal</th>"
            +   "<th>Referencia</th>"
            +   "<th>Status</th>"
            +   "<th>Empresa Licitada</th>"
            +   "<th>Atendido por</th>"
            +   "<th>Actualizar</th>");
                for(int i=0; i<reports.size();i++){
                    Usuario usr = conexion.buscarUsuarioId(((Reporte)reports.get(0)).getIdCiudadano());
                    out.println("<tr>"
                +   "<td>"+usr.getEmail()+"</td>"
                +   "<td>"+((Reporte)reports.get(i)).getFecha()+"</td>"
                +   "<td>"+((Reporte)reports.get(i)).getCalle()+"</td>"
                +   "<td>"+((Reporte)reports.get(i)).getColonia()+"</td>"
                +   "<td>"+((Reporte)reports.get(i)).getAlcaldia()+"</td>"
                +   "<td>"+((Reporte)reports.get(i)).getCp()+"</td>"
                +   "<td>"+((Reporte)reports.get(i)).getReferencia()+"</td>"
                +   "<td>"+((Reporte)reports.get(i)).getStatus()+"</td>" );
                    if(((Reporte)reports.get(i)).getIdEmpresa() != 0){
                        Empresa emp = conexion.buscarEmpresaId(1);
                        out.println( "<td>"+emp.getNombre()+"</td>" );
                    }
                    else{
                        out.println( "<td>S/A</td>" );
                    }
                    if(((Reporte)reports.get(i)).getIdEmpresa() != 0){
                        Usuario user = conexion.buscarAdminId(((Reporte)reports.get(i)).getIdAdmin());
                        out.println("<td>"+user.getNombre()+"   "+user.getApellidom()+"</td>" );
                    }
                    else{
                        out.println( "<td>S/A</td>" );
                    }
                    out.println("<td><form action='ServletActualizarReporte' method='POST'>"
                            +"<input type='hidden' name= 'idReporte' value='"+((Reporte)reports.get(i)).getId()+"'/>"
                            +"<input type='hidden' name= 'fechaReporte' value='"+((Reporte)reports.get(i)).getFecha()+"'/>"
                            +"<input type='hidden' name= 'calleReporte' value='"+((Reporte)reports.get(i)).getCalle()+"'/>"
                            +"<input type='hidden' name= 'coloniaReporte' value='"+((Reporte)reports.get(i)).getColonia()+"'/>"
                            +"<input type='hidden' name= 'alcaldiaReporte' value='"+((Reporte)reports.get(i)).getAlcaldia()+"'/>"
                            +"<input type='hidden' name= 'cpReporte' value='"+((Reporte)reports.get(i)).getCp()+"'/>"
                            +"<input type='hidden' name= 'referenciaReporte' value='"+((Reporte)reports.get(i)).getReferencia()+"'/>"
                            +"<input type='hidden' name= 'statusReporte' value='"+((Reporte)reports.get(i)).getStatus()+"'/>"
                            +"<input type='hidden' name= 'idAdministrador' value='"+((Reporte)reports.get(i)).getIdAdmin()+"'/>"
                            +"<input type='hidden' name= 'idEmpresa' value='"+((Reporte)reports.get(i)).getIdEmpresa()+"'/>"
                            +"<input type='hidden' name= 'idCiudadano' value='"+((Reporte)reports.get(i)).getIdCiudadano()+"'/>"
                            +"<button type=\"submit\">Actualizar</button>"
                            
                            + "</form></td>");
                }
                out.println("</table>");
            }
            
            out.println("</body>");
            out.println("</html>");}
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
