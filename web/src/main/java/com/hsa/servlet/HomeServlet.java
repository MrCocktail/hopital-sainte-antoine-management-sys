package com.hsa.servlet;

import com.hsa.service.HospitalService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"", "/home"})
public class HomeServlet extends HttpServlet {

    @Inject
    private HospitalService hospitalService; // Injection de votre EJB Local

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head><title>HSA Home</title></head>");
            out.println("<body>");
            out.println("<h1>Statut du Système de Gestion HSA</h1>");
            
            // Appel de la méthode de l'EJB
            if (hospitalService != null) {
                out.println("<p style='color: green; font-weight: bold;'>EJB Injecté avec succès !</p>");
                out.println("<p>Message de l'EJB : <i>" + hospitalService.getWelcomeMessage() + "</i></p>");
            } else {
                out.println("<p style='color: red;'>Erreur : L'EJB n'a pas pu être injecté.</p>");
            }
            
            out.println("</body>");
            out.println("</html>");
        }
    }
}