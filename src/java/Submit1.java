
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *  Handle and insert NAME and EMAIL into database. Then redirect to calendar1.jsp
 * @author Jing Lin
 */
public class Submit1 extends HttpServlet {

    private Connection conn;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String connectionURL = "jdbc:derby://localhost:1527/JingLin";
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        HttpSession session = request.getSession(true);
        session.setAttribute("name", name);
        session.setAttribute("email", email);
        System.out.println(name);
        {
            Connection conn = DriverManager.getConnection(connectionURL, "IS2560", "IS2560");
            try {
                //insert NAME and EMAIL into database
                String sql = "INSERT INTO ACCOUNT (NAME,EMAIL) VALUES('" + name + "', '" + email + "')";
                Statement st = conn.createStatement();
                st.executeUpdate(sql);

            } catch (SQLException ex) {
                String errormessage = ex.toString();
                //if email is duplicated, update new name
                if (errormessage.contains("a duplicate key value in a unique")) {
                    PreparedStatement ps = conn.prepareStatement("UPDATE ACCOUNT SET NAME=? WHERE EMAIL=?");
                    ps.setString(1, name);
                    ps.setString(2, email);
                    ps.executeUpdate();
                } else {
                    System.out.println("Connect failed ! ");
                }
            }
            conn.close();

        }

        //pass all the attribute to next page
        Connection conn = DriverManager.getConnection(connectionURL, "IS2560", "IS2560");
        PreparedStatement ps = conn.prepareStatement(
                "select TITLE,TIMESTAMP FROM EVENT WHERE USEREMAIL=?");
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {//assign number to TIME_STAMP (eg. 1 representing 9am etc.)
            String title_string;
            int time_stamp;
            title_string = rs.getString(1);
            time_stamp = rs.getInt(2);
            request.setAttribute("title" + String.valueOf(time_stamp), title_string);
        }
        conn.close();
        request.setAttribute("email", email);
        //redirect to calendar1
        request.getRequestDispatcher("/calendar1.jsp").forward(request, response);

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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Submit1.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Submit1.class.getName()).log(Level.SEVERE, null, ex);
        }
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
