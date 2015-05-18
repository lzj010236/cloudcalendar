/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
 *
 * @author J
 */
public class deleteEvent extends HttpServlet {

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
        HttpSession session = request.getSession(true);
        String email=(String)session.getAttribute("email");
        int calender_time = Integer.valueOf(request.getParameter("calendar_time"));
        String connectionURL = "jdbc:derby://localhost:1527/JingLin";
        
        try {
            //delete event according to TIME and EMAIL
            Connection conn;
            conn = DriverManager.getConnection(connectionURL, "IS2560", "IS2560");
            PreparedStatement ps = conn.prepareStatement("DELETE FROM EVENT WHERE TIMESTAMP=? AND USEREMAIL=?");
            ps.setInt(1, calender_time);
            ps.setString(2, email);
            ps.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(deleteEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
        try (
            //pass attribute to next page
            Connection conn= DriverManager.getConnection(connectionURL, "IS2560", "IS2560")) {
            PreparedStatement ps = conn.prepareStatement(
                    "select TITLE,TIMESTAMP FROM EVENT WHERE USEREMAIL=?");
            ps.setString(1, (String)session.getAttribute("email"));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String title_string;
                int time_stamp;
                title_string = rs.getString(1);
                time_stamp = rs.getInt(2);
                request.setAttribute("title" + String.valueOf(time_stamp), title_string);
            }
        }
        
        //call calendar1
        request.setAttribute("email", (String)session.getAttribute("email"));
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
            Logger.getLogger(deleteEvent.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(deleteEvent.class.getName()).log(Level.SEVERE, null, ex);
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
