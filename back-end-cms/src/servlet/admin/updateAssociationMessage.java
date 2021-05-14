package servlet.admin;

import bean.*;
import com.google.gson.GsonBuilder;
import dao.*;
import dao.impl.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet("/admin/updateAssociationMessage")
public class updateAssociationMessage extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateAssociationMessage() {
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

        //获取传入信息
        HashMap<String, String> data=(HashMap<String, String>) request.getAttribute("data");
        int stu_id = Integer.valueOf(String.valueOf(data.get("uid")));
        int as_id = Integer.valueOf(request.getParameter("as_id"));
        String state = request.getParameter("state");

        AdminDao adminDao = new AdminImpl();
        int res = adminDao.updateApplyAssociation(as_id,stu_id,state);
        //输出流
        PrintWriter out = response.getWriter();
        //返回体
        ResponseObject result = null;
        //登陆操作
        if(res!= 0) {
            result = new  ResponseObject(200,"审批活动成功!");

        }else{
            result = new ResponseObject(500,"审批活动失败!");
        }
        out.println(new GsonBuilder().create().toJson(result));
        out.flush();
        out.close();
    }
}
