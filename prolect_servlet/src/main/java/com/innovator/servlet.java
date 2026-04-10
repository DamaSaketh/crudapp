package com.innovator;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class servlet extends HttpServlet{


    @SuppressWarnings({"CallToPrintStackTrace", "ConvertToTryWithResources"})
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws
    IOException{
        response.setContentType("tet/html;charset-utf-8");
        PrintWriter out=response.getWriter();
        try{
        out.println("<html>");
        out.println("<body>");
        out.println("<h1>Hello all from servlet</h1> ");
              out.println("<h1> Servlet NewServlet at "+request.getContextPath()+"</h1>");
              String user=request.getParameter("user");
              out.println("<h2> welcome"+user+"</h2>");
              out.println("<body>");
        out.println("</html>");}
        catch(Exception e){
            e.printStackTrace();
        }
        finally{out.close();
    }}
   @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws  ServletException,IOException{
        processRequest(request, response);
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws  ServletException,IOException{
        processRequest(request, response);
    }
}
