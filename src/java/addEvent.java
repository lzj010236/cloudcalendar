
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * record and handle user-inputed TIME and TITLE into database. 
 * @author Jing Lin
 */
public class addEvent extends HttpServlet {
    
    //Transform int into String for time display
    protected String display_time(int time) {
        switch (time) {
            case 1:
                return "9AM";
            case 2:
                return "10AM";
            case 3:
                return "11AM";
            case 4:
                return "12PM";
            case 5:
                return "1PM";
            case 6:
                return "2PM";
            case 7:
                return "3PM";
            case 8:
                return "4PM";
            case 9:
                return "5PM";
            case 10:
                return "6PM";
            default:
                return "";
        }
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
        String connectionURL = "jdbc:derby://localhost:1527/JingLin";
        Connection conn;
        HttpSession session = request.getSession(true);
        String title = "error";
        try {
            conn = DriverManager.getConnection(connectionURL, "IS2560", "IS2560");
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM EVENT WHERE TIMESTAMP=? AND USEREMAIL=?");
            //for display EMAIL and TIME
            ps.setInt(1, Integer.valueOf(request.getParameter("calendar_time")));
            ps.setString(2, (String)session.getAttribute("email"));
            ResultSet exist_rs = ps.executeQuery();
            
            if (exist_rs.next() == false) {//if TITLE is empty
                title = "Please input title";
            } else {//if TITLE is not empty, display current value
                title = exist_rs.getString("TITLE");
            }
            
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(addEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //ask user to input TITLE. And display EMAIL and TIME. Then pass it to updateDB.java
        try (PrintWriter out = response.getWriter()) {
            /* output page */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet addEvent</title>");
            out.println("<link rel=\"stylesheet\" href=\"newcss.css\" type=\"text/css\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Add Event for "
                    + (String)session.getAttribute("email")
                    + "\n" + " at "
                    + display_time(Integer.valueOf(request.getParameter("calendar_time")))
                    + "</h1>");
            out.println("<form method =\"post\" action=\"updateDB\">\n"
                    + "<table border=\"1\">\n"
                    + "<tbody>\n"
                    + "<tr>\n"
                    + "<td>E-mail:</td>\n"
                    + "<td><input type=\"hidden\" name=\"email\" value=\""
                    + (String)session.getAttribute("email")
                    + "\" size=\"50\" />"
                    + (String)session.getAttribute("email")
                    + "</td>\n"
                    + "</tr>\n"
                    + "<tr>\n"
                    + "<td>time:</td>\n"
                    + "<td><input type=\"hidden\" name=\"calendar_time\" value=\""
                    + request.getParameter("calendar_time")
                    + "\" size=\"50\" />"
                    + display_time(Integer.valueOf(request.getParameter("calendar_time")))
                    + "</td>\n"
                    + "</tr>\n"
                    + "\n"
                    + "<tr>\n"
                    + "<td>title:</td>\n"
                    + "<td><input type=\"text\" name=\"title\" value=\""
                    + title 
                    + "\" size=\"50\" /></td>\n"
                    + "</tr>\n"
                    + "\n"
                    + "<tr>\n"
                    + "<td></td>\n"
                    + "<td><input type=\"submit\" value=\"Add/Modify\" name=\"updateDB\" /></td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "</form>\n"
                    + "<h4><a href=\"index.jsp\">Exit</a></h4>    ");
            out.println("</body>");
            out.println("</html>");
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
