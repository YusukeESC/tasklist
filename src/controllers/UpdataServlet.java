package controllers;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import models.validators.TaskValidator;
import utils.DBUtil;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/updata")
public class UpdataServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdataServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = (String)request.getParameter("_token");
        if(token != null || token.equals(request.getSession().getId())){
            EntityManager em = DBUtil.createEntityManager();

            Task task = em.find(Task.class, (Integer)(request.getSession().getAttribute("task_id")));

            task.setContent(request.getParameter("content"));

            try{
                Timestamp deadline = Timestamp.valueOf(LocalDateTime.parse(request.getParameter("deadline")));
                task.setDeadline(deadline);
            }catch(DateTimeParseException e){
//                task.setDeadline(null);
            }


            task.setUpdated_at(new Timestamp(System.currentTimeMillis()));

            List<String> errors = TaskValidator.validate(task);
            if(errors.size() > 0){
                em.close();

                request.setAttribute("message", task);
                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/messages/edit.jsp");
                rd.forward(request, response);

            }


            em.getTransaction().begin();
            em.getTransaction().commit();
            request.getSession().setAttribute("flush", "更新が完了しました");
            em.close();

            request.getSession().removeAttribute("message_id");
            response.sendRedirect(request.getContextPath() + "/index");


        }
    }

}
