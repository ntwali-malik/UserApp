import Utils.LoggerUtil;
import java.io.IOException;
import java.io.PrintWriter;
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
//import com.yourapp.utils.LoggerUtil;

@WebServlet("/update")
public class UpdateSer extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerUtil.getLogger();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/userInfo", "postgres", "123");
            String query = "UPDATE users SET name=?, email=? WHERE id=?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setInt(3, Integer.parseInt(id));
            int result = pstmt.executeUpdate();
            
            if (result > 0) {
                response.sendRedirect("retrieve.jsp");
                logger.info("Successfully updated record with ID: " + id);
            } else {
                out.println("<p>Failed to update record!</p>");
                logger.warning("Failed to update record with ID: " + id);
            }
            
            pstmt.close();
            con.close();
        } catch (Exception e) {
            logger.severe("Error occurred while updating record: " + e.getMessage());
            e.printStackTrace();
        }
        
        out.close();
    }
}
