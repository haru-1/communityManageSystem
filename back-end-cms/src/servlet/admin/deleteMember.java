package servlet.admin;

import bean.*;
import com.google.gson.GsonBuilder;
import dao.AdminDao;
import dao.impl.AdminImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/admin/deleteMember")
public class deleteMember extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteMember() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        this.doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        int stu_id = Integer.valueOf(request.getParameter("stu_id"));

        AdminDao adminDao = new AdminImpl();
        int res = adminDao.deleteStudent(stu_id);

        //输出流
        PrintWriter out = response.getWriter();
        //返回体
        ResponseObject result = null;
        //登陆操作
        if (res != 0) {
            result = new ResponseObject(200, "删除成功!");

        } else {
            result = new ResponseObject(500, "删除失败");
        }
        out.println(new GsonBuilder().create().toJson(result));
        out.flush();
        out.close();
    }
}
