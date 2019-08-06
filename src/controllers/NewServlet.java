package controllers;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import utils.DBUtil;

/**
 * Servlet implementation class NewServlet
 */
@WebServlet("/new")
public class NewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();
        em.getTransaction().begin();

        Task task = new Task();

        task.setContent("掃除をする");
        task.setUpdated_at(new Timestamp(System.currentTimeMillis()));
        task.setCreated_at(new Timestamp(System.currentTimeMillis()));

        try {
            task.setDeadline(new Timestamp(new SimpleDateFormat("yyyy/mm/dd hh:mm").parse("2019/08/10 12:00").getTime()));
        } catch (ParseException e) {}


        em.persist(task);
        em.getTransaction().commit();

        response.getWriter().append(Integer.valueOf(task.getId()).toString());

        em.close();
    }

}
