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
 * Servlet implementation class CreateServlet
 */
@WebServlet("/create")
public class CreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = (String)request.getParameter("_token");
        if(token != null && token.equals(request.getSession().getId())){
            EntityManager em = DBUtil.createEntityManager();

            Task task = new Task();

            String content = request.getParameter("content");
            task.setContent(content);

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            task.setCreated_at(timestamp);
            task.setUpdated_at(timestamp);


            try{
                Timestamp deadline = Timestamp.valueOf(LocalDateTime.parse(request.getParameter("deadline")));
                task.setDeadline(deadline);
            }catch(DateTimeParseException e){
                task.setDeadline(null);
            }


            List<String> errors = TaskValidator.validate(task);

            if(errors.size() > 0){
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("task", task );
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/new.jsp");
                rd.forward(request, response);

            }else{
                em.getTransaction().begin();
                em.persist(task );
                em.getTransaction().commit();
                request.getSession().setAttribute("flush", "登録が完了しました");
                em.close();

                response.sendRedirect(request.getContextPath() + "/index");
            }

        }

    }

}
