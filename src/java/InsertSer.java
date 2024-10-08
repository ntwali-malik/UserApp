import Utils.LoggerUtil;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Import your LoggerUtil class
//import com.yourapp.LoggerUtil;

@WebServlet("/InsertSer")
public class InsertSer extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerUtil.getLogger();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/userInfo", "postgres", "123");
            PreparedStatement ps = con.prepareStatement("INSERT INTO users(name, email) VALUES (?, ?)");
            ps.setString(1, name);
            ps.setString(2, email);
            ps.executeUpdate();
            ps.close();
            con.close();
            
            response.sendRedirect("retrieve.jsp");
            logger.info("Inserted user with name: " + name + " and email: " + email);
        } catch (Exception ex) {
            logger.severe("Error occurred while inserting record: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
