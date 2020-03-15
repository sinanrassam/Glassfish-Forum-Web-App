package User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
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


@WebServlet(name = "CreateAccountServlet",
    urlPatterns = {"/CreateAccountServlet"
    }
)
public class CreateAccountServlet extends HttpServlet {

    private Logger logger;

    @PersistenceContext
    private EntityManager entityManager;

    
    @PersistenceUnit(unitName="users")
    private EntityManagerFactory entityManagerFactory;
    
    @Resource 
    private UserTransaction userTransaction;
    
    public CreateAccountServlet() {
        logger = Logger.getLogger(getClass().getName());
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        //Obtain data given from the server
        String username = request.getParameter("username");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        
        int day = Integer.parseInt(request.getParameter("day"));
        int month = Integer.parseInt(request.getParameter("month"));
        int year = Integer.parseInt(request.getParameter("year"));
        Date dob = new Date();
        
        dob.setYear(year);
        dob.setDate(day);
        dob.setMonth(month);
        
        
        if (username.length() < 5 || password.length() < 5) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/CreateNewUser.jsp");
            dispatcher.forward(request, response);

        } else {
            String validateUsername = username;
            String validateEmail = email;
            
            UserPK pk = new UserPK(validateEmail, validateUsername);
            
            User validateUser = entityManager.find(User.class, pk);

            if(validateUser == null) {
                logger.info("User not found");
                logger.info("Creating new user: " + username);
                
                User newUser = new User();
            
                newUser.setFirstName(firstName);
                newUser.setLastName(lastName);
                newUser.setEmail(email);
                newUser.setDob(dob);
                newUser.setAge(20);
                newUser.setGender(gender);
                newUser.setUsername(username);
                newUser.setPassword(password);

                try {
                    userTransaction.begin(); 
                    entityManager.persist(newUser);
                    userTransaction.commit();
                } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
                    Logger.getLogger(CreateAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
                dispatcher.forward(request, response);
                
            } else {
                logger.info("User found");
                
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/CreateNewUser.jsp");
                dispatcher.forward(request, response);
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
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
