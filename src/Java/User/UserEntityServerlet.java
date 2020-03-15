/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
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
@WebServlet(name = "UserEntityServerlet", urlPatterns = {"/login", "/logout", "/register"})
public class UserEntityServerlet extends HttpServlet {

    private Logger logger;
    @PersistenceContext
    private EntityManager entityManager;
    @Resource
    private UserTransaction userTransaction;

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
        response.setContentType("text/html;charset=UTF-8");
        String servletPath = request.getServletPath();
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("user");

        if (servletPath.equals("/register")) {
            logger.info("Registeration");

            //Obtain data given from the server
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String gender = request.getParameter("gender");

            String dayString = request.getParameter("day");
            String monthString = request.getParameter("month");
            String yearString = request.getParameter("year");

            // perform some basic validation on parameters
            Object[] data = {username, password, firstName, lastName, email, gender, dayString, monthString, yearString};
            boolean validated = isValid(data);

            if (validated) {
                int day = Integer.parseInt(dayString);
                int month = Integer.parseInt(monthString);
                int year = Integer.parseInt(yearString);
                Date dob = new Date(year, month, day);

                UserPK pk = new UserPK(email, username);
                User validateUser = entityManager.find(User.class, pk);

                if (validateUser == null) {
                    logger.info("User not found");
                    logger.info("Creating new user: " + username);

                    user = new User();

                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setEmail(email);
                    user.setDob(dob);
                    user.setAge(20);
                    user.setGender(gender);
                    user.setUsername(username);
                    user.setPassword(password);

                    try {
                        userTransaction.begin();
                        entityManager.persist(user);
                        userTransaction.commit();
                    } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
                        Logger.getLogger(UserEntityServerlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    request.getSession().setAttribute("message", "User Account Creation Was Sucessfull!");
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
                    dispatcher.forward(request, response);
                } else {
                    request.getSession().setAttribute("error", "User with that email/username already exist");
                    RequestDispatcher dispatcher = getServletContext().
                            getRequestDispatcher("/register.jsp");
                    dispatcher.forward(request, response);
                }
            } else {
                request.getSession().setAttribute("error", "Validation Failed!");
                RequestDispatcher dispatcher = getServletContext().
                        getRequestDispatcher("/register.jsp");
                dispatcher.forward(request, response);
            }

        } else if (servletPath.equals("/login")) {
            logger.info("Login");
            if (user == null) {
                logger.info("Processing Request");

                String username = request.getParameter("username");
                String password = request.getParameter("password");

                // perform some basic validation on parameters
                Object[] data = {username, password};
                boolean validated = isValid(data);

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

    private boolean isValid(Object objs[]) {
        boolean isValid = true;
        for (int i = 0; i < objs.length; i++) {
            if (objs[i] != null) {
                if (objs[i] instanceof String) {
                    isValid = !(((String) objs[i]).isEmpty());
                    if (!isValid) {
                        return isValid;
                    }
                }
            } else {
                return false;
            }
        }
        return isValid;
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
