/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author phucl
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String txtA = request.getParameter("txtA".trim());
        String txtB = request.getParameter("txtB").trim();
        
        if(txtA==null || txtA.length()==0) {
            out.println("Please enter 'a' value!");
            return;
        }
        
        if(txtB==null || txtB.length()==0) {
            out.println("Please enter 'b' value!");
            return;
        }
        
        int a = 0;
        int b = 0;
        
        try {
            a = Integer.parseInt(txtA);
            if(a<=0) {
                throw new Exception();
            }
        } catch (Exception ex) {
            out.println("'a' must be an integer number!");
            return;
        }
        
        try {
            b = Integer.parseInt(txtB);
            if(b<=0) {
                throw new Exception();
            }
        } catch (Exception ex) {
            out.println("'b' must be an integer number!");
            return;
        }
        
        int result = GCD(a, b);
        out.println("GCD(" + a + ", " + b +") = " + result);
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private int GCD(int a, int b) {        
        int min = a>b?b:a;
        
        for (int i = min; i>=1; i--) {
            if(a%i==0 && b%i==0) {
                return i;
            }
        }
        
        return 1;
    }
    
}
