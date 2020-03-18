/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forum;

import User.User;
import User.UserEntityServerlet;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import javax.annotation.Resource;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author sinan.rassam
 */
@WebServlet(name = "ForumEntityServerlet", urlPatterns = {"/getForums", "/createForum"})
public class ForumEntityServerlet extends HttpServlet {

    private Logger logger;
    @PersistenceContext
    private EntityManager entityManager;
    @Resource
    private UserTransaction userTransaction;

    public ForumEntityServerlet() {
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

        if (user == null) {
            request.getSession().setAttribute("error", "You need to be logged in to see this page");
            RequestDispatcher dispatcher = getServletContext().
                    getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        } else {
            if (servletPath.equals("/getForums")) {
                logger.info("Test");
                if (entityManager != null) {
                    String jpqlCommand = "SELECT f FROM Forum f";
                    Query query = entityManager.createQuery(jpqlCommand);

                    List<Forum> forums = new ArrayList<>();

                    if (query.getResultList().size() > 0) {
                        logger.info("Forums found:");

                        for (int i = 0; i < query.getResultList().size(); i++) {
                            forums.add((Forum) query.getResultList().get(i));
                        }
                    } else {
                        logger.info("No Forums found!");
                    }

                    request.setAttribute("forums", forums);
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/forums.jsp");
                    dispatcher.forward(request, response);
                }
            } else if (servletPath.equals("/createForum")) {
                if (user.getAdminLevel() == 0) {
                    String jpqlCommand = "SELECT f FROM Forum f";
                    Query query = entityManager.createQuery(jpqlCommand);

                    int forumID = query.getResultList().size() + 1;
                    String forumTitle = request.getParameter("title");
                    String forumDescription = request.getParameter("description");

                    Object[] data = {forumTitle, forumDescription, forumID};
                    boolean validated = Utils.Utils.isValid(data);

                    if (validated) {
                        if (entityManager != null) {
                            Forum forum = new Forum();

                            forum.setId(forumID);
                            forum.setTitle(forumTitle);
                            forum.setDescription(forumDescription);
                            forum.setCreationDate(new Date());

                            try {
                                userTransaction.begin();
                                entityManager.persist(forum);
                                userTransaction.commit();
                                request.getSession().setAttribute("message", "Forum Created Successfully!");
                            } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
                                request.getSession().setAttribute("error", "Forum Could not be created!");
                                Logger.getLogger(UserEntityServerlet.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/getForums");
                            dispatcher.forward(request, response);
                        }
                    } else {
                        request.getSession().setAttribute("error", "Validation Failed!");
                        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/getForums");
                        dispatcher.forward(request, response);
                    }
                } else if (user.getAdminLevel() == 1) {
                    request.getSession().setAttribute("error", "You need admin privileges to create a new forum!");
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/getForums");
                    dispatcher.forward(request, response);
                }
            }
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
