/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Database.DatabaseConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin piyush
 */
@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {

    private String user;
    private String pass;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        try {
            String userp = request.getParameter("your_name");
            String passp = request.getParameter("your_pass");
            Connection con = DatabaseConnection.initializeDatabase();

            PreparedStatement ptm = con.prepareStatement("SELECT * FROM adminreg WHERE username=? AND password=?;");
            ptm.setString(1, userp);
            ptm.setString(2, passp);
            ResultSet rs = ptm.executeQuery();
            if (rs.next()) {
                user = rs.getString(1);
                pass = rs.getString(2);
            }
            if (userp.equals(user) && passp.equals(pass)) {
                pw.println("<script type=\"text/javascript\">");
                pw.println("alert('Login Successfully..!');");
                pw.println("window.location.href = \"AdminHome.jsp\";");
                pw.println("</script>");
                //RequestDispatcher rd = request.getRequestDispatcher("AdminHome.jsp");
                //rd.forward(request, response);
            } else {
                pw.println("<script type=\"text/javascript\">");
                pw.println("alert('Username or Password is Incorrect..!');");
                pw.println("window.location.href = \"index.jsp\";");
                pw.println("</script>");
                //RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                //rd.include(request, response);
            }
        } catch (Exception e) {

        }

    }

}
