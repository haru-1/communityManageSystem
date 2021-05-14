package servlet.apply;

import bean.*;
import com.google.gson.GsonBuilder;
import dao.ApplyDao;
import dao.AssociationDao;
import dao.PersonalDao;
import dao.impl.ApplyImpl;
import dao.impl.AssociationImpl;
import dao.impl.PersonalImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

@WebServlet("/personal/getMyApplyMessage")
public class getMyApplyMessage extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public getMyApplyMessage() {
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


        HashMap<String, String> data=(HashMap<String, String>) request.getAttribute("data");
        int stu_id = Integer.valueOf(String.valueOf(data.get("uid")));

        ApplyDao applyDao = new ApplyImpl();
        List<ApplyMessage> applyMessages = applyDao.getMyApplyMessage(stu_id);
        //输出流
        PrintWriter out = response.getWriter();
        //返回体
        ResponseObject result = null;
        //登陆操作
        if( applyMessages.size() != 0) {
            result = new  ResponseObject(200,"查询成功!",applyMessages);

        }else{
            result = new ResponseObject(500,"您没有任何的审批信息");
        }
        out.println(new GsonBuilder().create().toJson(result));
        out.flush();
        out.close();
    }
}
