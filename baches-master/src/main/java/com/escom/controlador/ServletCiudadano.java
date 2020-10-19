
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

public class ServletCiudadano extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession sesion = request.getSession();
            String user = (String)sesion.getAttribute("user");
            String pass = (String)sesion.getAttribute("pass");
            String id = (String)sesion.getAttribute("id");
            Conexion conexion = new Conexion();
            List lu = conexion.buscarUsuario(user, pass);
            Ciudadano usr = ((Usuario)lu.get(0)).converToCiudadano();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">");
            out.println("<title>Home</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<nav class=\"navbar navbar-expand-lg navbar-dark bg-primary\">\n" +
"            \n" +
"            <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarNav\" aria-controls=\"navbarNav\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n" +
"                <span class=\"navbar-toggler-icon\"></span>\n" +
"            </button>\n" +
"            <div class=\"collapse navbar-collapse\" id=\"navbarNav\">\n" +
"                <ul class=\"navbar-nav\">\n" +
"                    <li class=\"nav-item active\">\n" +
"                        <a class=\"nav-link\" href=\"ServletCiudadano\">Home</a>\n" +
"                    </li>\n" +
"                    <li class=\"nav-item\">\n" +
"                        <a class=\"nav-link\" href=\"ServletGenerarReporte\">Generar reporte</a>\n" +
"                    </li>\n" +
"                    </li>\n" +
"                    <li class=\"nav-item\">\n" +
"                        <a class=\"nav-link\" href=\"ServletListaReportes\">Ver Reportes</a>\n" +
"                    </li>\n" +
                    "<li class=\"nav-item\">\n"+
                         "<a class=\"nav-link\" href=\"index.html\">Salir</a>\n" +
"                    </li>\n" +
"                </ul>\n" +
"            </div>\n" +
"        </nav>");
            out.println("<h1>"+usr.getEmail()+"  "+id+ "</h1>");
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
