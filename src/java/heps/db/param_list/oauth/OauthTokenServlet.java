/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.param_list.oauth;

import cn.vlabs.umt.oauth.AccessToken;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.vlabs.umt.oauth.Oauth;
import cn.vlabs.umt.oauth.UMTOauthConnectException;
import cn.vlabs.umt.oauth.common.exception.OAuthProblemException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lvhuihui
 */
@WebServlet(name = "token", urlPatterns = {"/token"})
public class OauthTokenServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /* protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet OauthTokenServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OauthTokenServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }*/
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //processRequest(request, response);
        InputStream in = OauthTokenServlet.class.getResourceAsStream("umtoauthconfig.properties");
        Properties p = new Properties();
        p.load(in);
        Oauth oauth = new Oauth(p);
        System.out.println("+++++++" + req.getParameter("code"));
        p.setProperty("code", "6bc53a084e7a5fcccba17bfc15e0604e");
       // p.setProperty("code", req.getParameter("code"));
       
        try {
            AccessToken token;
            token = oauth.getAccessTokenByRequest(req);
            req.setAttribute("token", token.getAccessToken());
            req.setAttribute("refreshToken", token.getRefreshToken());
            //登录完成 获登录的用户信息
            req.setAttribute("userInfo", token.getUserInfo());
            System.out.println("++++" + token.getUserInfo());
            req.getRequestDispatcher("http://localhost/heps-db-param_list").forward(req, resp);
        } catch (UMTOauthConnectException | OAuthProblemException ex) {
            Logger.getLogger(OauthTokenServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        doGet(request, response);
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
