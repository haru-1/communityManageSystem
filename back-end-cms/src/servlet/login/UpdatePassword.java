package servlet.login;

import bean.ResponseObject;
import bean.Student;
import com.google.gson.GsonBuilder;
import dao.LoginDao;
import dao.impl.LoginImpl;
import net.minidev.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet("/login/UpdatePassword")
public class UpdatePassword extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePassword() {
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

        JSONObject resultJSON=new JSONObject();

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        HashMap<String, String> data=(HashMap<String, String>) request.getAttribute("data");
        int id = Integer.valueOf(String.valueOf(data.get("uid")));

        String password = request.getParameter("password");
        String newpassword = request.getParameter("newpassword");
        System.out.println("uid:"+id+"password:"+password + "newpassword"+newpassword);
        //登陆注册对象
        LoginDao loginDao = new LoginImpl();
        //输出流
        PrintWriter out = response.getWriter();
        //返回体
        int res = loginDao.updatePassword(id,password,newpassword);
        if(res != 0) {
            resultJSON.put("state", 200);
            resultJSON.put("msg", "修改密码成功");
        }else{
            resultJSON.put("state", 500);
            resultJSON.put("msg", "用户名密码不对");
        }
        out.println(resultJSON.toJSONString());
        out.flush();
        out.close();
    }


}


