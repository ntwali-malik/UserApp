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

@WebServlet("/delete")
public class DeleteSer extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerUtil.getLogger();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String id = request.getParameter("id");

        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/userInfo", "postgres", "123");
            String query = "DELETE FROM users WHERE id=?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, Integer.parseInt(id));
            int result = pstmt.executeUpdate();
            
            if (result > 0) {
                response.sendRedirect("retrieve.jsp");
                logger.info("Successfully deleted record with ID: " + id);
            } else {
                out.println("<p>Failed to delete record!</p>");
                logger.warning("Failed to delete record with ID: " + id);
            }
            
            pstmt.close();
            con.close();
        } catch (Exception e) {
            logger.severe("Error occurred while deleting record: " + e.getMessage());
            e.printStackTrace();
        }
        
        out.close();
    }
}
