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
    private String sqlCommandCheck;
    private String sqlCommandCreate;

    @PersistenceContext
    private EntityManager entityManager;

    
    @PersistenceUnit(unitName="PetDatabaseConnectivityPU")
    private EntityManagerFactory entityManagerFactory;
    
    @Resource 
    private UserTransaction userTransaction;
    
    public CreateAccountServlet() {
        logger = Logger.getLogger(getClass().getName());
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String username = request.getParameter("username");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Date dob = new Date();

        if (username.length() < 5 || password.length() < 5) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/CreateNewUser.jsp");
            dispatcher.forward(request, response);

        } else {
            entityManager = entityManagerFactory.createEntityManager();
            
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(null);
            user.setDob(dob);
            user.setAge(10);
            user.setGender("Male");
            user.setUsername(username);
            user.setPassword(password);
            
            try {
                userTransaction.begin();
                entityManager.persist(user);
                userTransaction.commit();
            } catch (NotSupportedException ex) {
                Logger.getLogger(CreateAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SystemException ex) {
                Logger.getLogger(CreateAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RollbackException ex) {
                Logger.getLogger(CreateAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (HeuristicMixedException ex) {
                Logger.getLogger(CreateAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (HeuristicRollbackException ex) {
                Logger.getLogger(CreateAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(CreateAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalStateException ex) {
                Logger.getLogger(CreateAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            // Insert into database

            //sqlCommandCreate = "INSERT INTO users VALUES(:firstName, :lastName, :email, :dob, 0, male, :username, :password)";
            //sqlCommandCreate = "INSERT INTO users (firstName, lastName, email, dob, age, gender, username, password) VALUES(:firstName, :lastName, :email, :dob, 0, male, :username, :password)";
            //sqlCommandCreate = "INSERT INTO users (firstName, lastName, email, dob, age, gender, username, password) VALUES(?, ?, ?, ?, 0, ?, ?, ?)";
            //sqlCommandCreate = "Select * from users";
            /*
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setDob(dob);
            user.setAge(10);
            user.setGender("Male");
            user.setUsername(username);

            //User u = entityManager.find(User.class, user.getEmail());
            
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            User u = entityManager.find(User.class, firstName);
            entityManager.getTransaction().commit();
            entityManager.close();

            /*
            Query query = entityManager.createNativeQuery(sqlCommandCreate);

            query.setParameter("firstName", firstName);
            query.setParameter("lastName", lastName);
            query.setParameter("email", email);
            query.setParameter("dob", dob);
            query.setParameter("username", username);
            query.setParameter("password", password);
            query.setParameter("age", password);
            query.setParameter("password", password);
            */
            //RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
            //dispatcher.forward(request, response);
            
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CreateAccountServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateAccountServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
