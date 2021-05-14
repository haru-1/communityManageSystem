package servlet.president;

import bean.*;
import com.google.gson.GsonBuilder;
import dao.MemberDao;
import dao.PersonalDao;
import dao.impl.MemberImpl;
import dao.impl.PersonalImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet("/president/updateAssociation")
public class updateAssociation extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateAssociation() {
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
        String oldName = request.getParameter("oldName");
        Association association = new Association();
        association.setAs_name(request.getParameter("as_name"));
        association.setAs_level(request.getParameter("as_level"));
        association.setAs_introduce(request.getParameter("as_introduce"));
        association.setAs_number(request.getParameter("as_number"));

        MemberDao memberDao = new MemberImpl();
        int res = memberDao.updateAssociationMessage(association,oldName);
        //输出流
        PrintWriter out = response.getWriter();
        //返回体
        ResponseObject result = null;
        //登陆操作
        if(res!= 0) {
            result = new  ResponseObject(200,"递交申请社团信息成功!");

        }else{
            result = new ResponseObject(500,"递交申请社团信息失败!");
        }
        out.println(new GsonBuilder().create().toJson(result));
        out.flush();
        out.close();
    }
}
