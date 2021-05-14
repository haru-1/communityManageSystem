package servlet.admin;

import bean.Page;
import bean.ResponseObject;
import bean.Student;
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
import java.util.List;

@WebServlet("/admin/getMember")
public class getMember extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public getMember() {
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

        Page page = new Page();
        AdminDao adminDao = new AdminImpl();
        List<Student> students  = adminDao.getAllStudentList(page);
        //输出流
        PrintWriter out = response.getWriter();
        //返回体
        ResponseObject result = null;
        //登陆操作
        if(students.size() != 0) {
            result = new  ResponseObject(200,"查询成功!",students);

        }else{
            result = new ResponseObject(500,"查询失败!");
        }
        out.println(new GsonBuilder().create().toJson(result));
        out.flush();
        out.close();
    }
}
