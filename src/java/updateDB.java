/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
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

/**
 *
 * @author J
 */
public class updateDB extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");

        String connectionURL = "jdbc:derby://localhost:1527/JingLin";
        String email = request.getParameter("email");
        String calendar_time = request.getParameter("calendar_time");

        try {
            Connection conn = DriverManager.getConnection(connectionURL, "IS2560", "IS2560");
            //get EMAIL and TIME
            PreparedStatement exist_ps = conn.prepareStatement("SELECT * FROM EVENT WHERE TIMESTAMP=? AND USEREMAIL=?");
            exist_ps.setInt(1, Integer.valueOf(request.getParameter("calendar_time")));
            exist_ps.setString(2, request.getParameter("email"));
            ResultSet exist_rs = exist_ps.executeQuery();
            if (exist_rs.next() == false) {//if TITLE is empty, insert user-input value
                PreparedStatement ps = conn.prepareStatement(
                        "INSERT INTO EVENT (USEREMAIL,TIMESTAMP,TITLE) VALUES(?,?,?)");
                ps.setString(1, request.getParameter("email"));
                ps.setInt(2, Integer.valueOf(request.getParameter("calendar_time")));
                ps.setString(3, request.getParameter("title"));
                ps.executeUpdate();
            } else {//if TITLE already has value, update new value
                PreparedStatement ps = conn.prepareStatement(
                        "UPDATE EVENT SET TITLE=? WHERE USEREMAIL=? AND TIMESTAMP=?");
                ps.setString(2, request.getParameter("email"));
                ps.setInt(3, Integer.valueOf(request.getParameter("calendar_time")));
                ps.setString(1, request.getParameter("title"));
                ps.executeUpdate();
            }
            conn.close();

        } catch (SQLException ex) {
            System.out.println("Connect failed ! ");
        }

        //pass all attribute to next page
        Connection conn = DriverManager.getConnection(connectionURL, "IS2560", "IS2560");
        PreparedStatement ps = conn.prepareStatement(
                "select TITLE,TIMESTAMP FROM EVENT WHERE USEREMAIL=?");
        ps.setString(1, request.getParameter("email"));
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String title_string;
            int time_stamp;
            title_string = rs.getString(1);
            time_stamp = rs.getInt(2);
            request.setAttribute("title" + String.valueOf(time_stamp), title_string);
        }
        conn.close();
        request.setAttribute("email", request.getParameter("email"));
        //call calendar1
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
            Logger.getLogger(updateDB.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(updateDB.class.getName()).log(Level.SEVERE, null, ex);
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
