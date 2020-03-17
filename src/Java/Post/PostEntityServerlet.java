/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Post;

import User.User;
import User.UserEntityServerlet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "PostEntityServerlet", urlPatterns = {"/createPost", "/getPosts"})
public class PostEntityServerlet extends HttpServlet {

    private Logger logger;
    @PersistenceContext
    private EntityManager entityManager;
    @Resource
    private UserTransaction userTransaction;

    public PostEntityServerlet() {
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
            if (servletPath.equals("/createPost")) {
                String title = request.getParameter("title");
                String description = request.getParameter("description");
                String forum_id = request.getParameter("id");
                
                logger.info(forum_id);

                // perform some basic validation on parameters
                Object[] data = {title, description, forum_id};
                boolean validated = Utils.Utils.isValid(data);

                if (validated) {
                    logger.info("Valdiated");
                    if (entityManager != null) {
                        Post post = new Post();

                        post.setForumId(Integer.parseInt(forum_id)); // Temporarily put -1
                        post.setTitle(title);
                        post.setDescription(description);
                        post.setUsername(((User) session.getAttribute("user")).getUsername());

                        try {
                            userTransaction.begin();
                            entityManager.persist(post);
                            userTransaction.commit();
                            request.getSession().setAttribute("message", "Post Created Successfully!");
                        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
                            request.getSession().setAttribute("error", "Post Could not be created!");
                            Logger.getLogger(UserEntityServerlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/createPost.jsp");
                        dispatcher.forward(request, response);
                    }
                } else {
                    logger.info("Not Valdiated");
                    request.getSession().setAttribute("error", "Validation Failed!");
                    RequestDispatcher dispatcher = getServletContext().
                            getRequestDispatcher("/createPost.jsp");
                    dispatcher.forward(request, response);
                }
            } else if (servletPath.equals("/getPosts")) {
                String forum_id = request.getParameter("id");
                Object[] data = {forum_id};
                if (!Utils.Utils.isValid(data)) {
                    request.getSession().setAttribute("error", "Validation Failed!");
                    RequestDispatcher dispatcher = getServletContext().
                            getRequestDispatcher("/forums.jsp");
                    dispatcher.forward(request, response);
                } else {
                    Integer forumId = Integer.parseInt(forum_id);
                    String jpqlCommand = "SELECT p FROM Post p WHERE p.forumId = :forumId";
                    Query query = entityManager.createQuery(jpqlCommand);
                    query.setParameter("forumId", forumId);

                    List<Post> posts = new ArrayList<>();

                    if (query.getResultList().size() > 1) {
                        logger.info("Posts found:");
                        Post newPost;

                        for (int i = 0; i < query.getResultList().size(); i++) {
                            newPost = (Post) query.getResultList().get(i);
                            posts.add(newPost);
                        }

                    } else {
                        logger.info("No posts");
                    }

                    request.setAttribute("posts", posts);
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/createPost.jsp");
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
