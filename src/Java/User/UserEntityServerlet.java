/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import java.io.IOException;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sinan.rassam
 */
@WebServlet(name = "UserEntityServerlet", urlPatterns = {"/login", "/logout"})
public class UserEntityServerlet extends HttpServlet {

    private Logger logger;
    @PersistenceContext
    private EntityManager entityManager;

    public UserEntityServerlet() {
        logger = Logger.getLogger(getClass().getName());
    }

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
        String servletPath = request.getServletPath();
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("user");
        if (servletPath.equals("/login")) {
            logger.info("Login");
            if (user == null) {
                logger.info("Processing Request");

                String username = request.getParameter("username");
                String password = request.getParameter("password");

                // perform some basic validation on parameters
                boolean validated = (isValid(username)) && (isValid(password));

                if (validated) {
                    logger.info("Valdiated");
                    if (entityManager != null) {
                        String jpqlCommand
                                = "SELECT u FROM User u WHERE u.username = :username AND u.password = :password";
                        Query query = entityManager.createQuery(jpqlCommand);
                        query.setParameter("username", username);
                        query.setParameter("password", password);
                        if (query.getResultList().size() == 1) {
                            logger.info("OK");
                            user = (User) query.getResultList().get(0);
                            session.setAttribute("user", user);
                            RequestDispatcher dispatcher = getServletContext().
                                    getRequestDispatcher("/confirmation.jsp");
                            dispatcher.forward(request, response);
                        } else {
                            request.getSession().setAttribute("error", "Username or Password are incorrect!");
                            RequestDispatcher dispatcher = getServletContext().
                                    getRequestDispatcher("/login.jsp");
                            dispatcher.forward(request, response);
                        }
                    }
                } else {
                    request.getSession().setAttribute("error", "Validation Failed!");
                    RequestDispatcher dispatcher = getServletContext().
                            getRequestDispatcher("/login.jsp");
                    dispatcher.forward(request, response);
                }
            }
        } else if (servletPath.equals("/logout")) {
            logger.info("Logout");
            session.invalidate();
            response.sendRedirect("login.jsp");
        }
    }
    
    private boolean isValid(String str) {
        return (str != null && !str.isEmpty());
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