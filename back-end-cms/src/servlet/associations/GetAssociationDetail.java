package servlet.associations;

import bean.Association;
import bean.Page;
import bean.ResponseObject;
import com.google.gson.GsonBuilder;
import dao.AssociationDao;
import dao.impl.AssociationImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet("/associations/GetAssociationDetail")
public class GetAssociationDetail extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAssociationDetail() {
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
        String as_name = request.getParameter("as_name");
        System.out.println("as_name:"+as_name);

        //获取社团详情
        Page page = new Page();
        AssociationDao associationDao = new AssociationImpl();
        Association association = associationDao.getAssociationDetail(as_name);
        //输出流
        PrintWriter out = response.getWriter();
        //返回体
        ResponseObject result = null;
        //登陆操作
        if(association!= null) {
            result = new  ResponseObject(200,"查询成功!",association);

        }else{
            result = new ResponseObject(500,"查询失败!");
        }
        out.println(new GsonBuilder().create().toJson(result));
        out.flush();
        out.close();
    }
}
