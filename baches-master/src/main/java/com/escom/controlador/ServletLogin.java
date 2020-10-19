
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

public class ServletLogin extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String email = request.getParameter("correoe");
            String pass = request.getParameter("pass");
            Conexion conexion = new Conexion();
            List e = conexion.buscarUsuario(email, pass);
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">");
            out.println("<title>Login</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<nav class=\"navbar navbar-expand-lg navbar-dark bg-primary\">\n" +
"            \n" +
"            <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarNav\" aria-controls=\"navbarNav\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n" +
"                <span class=\"navbar-toggler-icon\"></span>\n" +
"            </button>\n" +
"            <div class=\"collapse navbar-collapse\" id=\"navbarNav\">\n" +
"                <ul class=\"navbar-nav\">\n" +
"                    <li class=\"nav-item\">\n" +
"                        <a class=\"nav-link\" href=\"index.html\">Ingresar</a>\n" +
"                    </li>\n" +
"                    <li class=\"nav-item\">\n" +
"                        <a class=\"nav-link\" href=\"registro.html\">Registrarse</a>\n" +
"                    </li>\n" +
"                </ul>\n" +
"            </div>\n" +
"        </nav>");
            if(e.get(1).equals("0")){
                out.println("<h1>Usuario o contraseña incorrectos</h1>");
            }else if(e.get(1).equals("1")){
                HttpSession sesion = request.getSession();
                sesion.setAttribute("user", email);
                sesion.setAttribute("pass", pass);
                Usuario usr = (Usuario)e.get(0);
                
                sesion.setAttribute("id", String.valueOf(usr.getId()));
                response.sendRedirect("ServletCiudadano");
                
            }else if(e.get(1).equals("2")){
                HttpSession sesion = request.getSession();
                sesion.setAttribute("user", email);
                sesion.setAttribute("pass", pass);
                Usuario usr = (Usuario)e.get(0);
                
                sesion.setAttribute("id", String.valueOf(usr.getId()));
                response.sendRedirect("ServletAdmin");
            }
            
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
