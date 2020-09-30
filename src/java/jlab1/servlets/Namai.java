/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jlab1.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

/**
 *
 * @author edgaras
 */
@WebServlet(name = "Namai", urlPatterns = {"/"})
public class Namai extends HttpServlet {
    
    private jlab1.beans.Message msg = new jlab1.beans.Message();
    
    @PersistenceUnit 
    private EntityManagerFactory emf;
    
    @Resource 
    private UserTransaction utx;

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
        EntityManager em = null;
        try (PrintWriter out = response.getWriter()) {
            em = emf.createEntityManager();
            List messages = em.createQuery("select m from Message m").getResultList();
            request.setAttribute("msg", this.msg);
            request.setAttribute("msg_list", messages);
            request.getRequestDispatcher("namai.jsp").forward(request, response);
        } catch (Exception ex){
            throw new ServletException(ex);
        } finally{
            if(em != null)
                em.close();
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
        String l_name = "";
        l_name = request.getParameter("name");
        String l_msg = "";
        l_msg = request.getParameter("message");
        if(l_msg != null && l_name != null){
            this.msg.setName(l_name);
            this.msg.setMsg(l_msg);
            this.msg.setTime(new Date());
            
            EntityManager em = null;
            try{
                jlab1.entities.Message e_msg = new jlab1.entities.Message();
                e_msg.setMessage(l_msg);
                e_msg.setName(l_name);
                e_msg.setTime(new Date());
                utx.begin();
                em = emf.createEntityManager();
                em.persist(e_msg);
                utx.commit();
            }catch (Exception ex){
                throw new ServletException(ex);
            }finally {
                if(em != null)
                    em.close();
            }
        }
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
