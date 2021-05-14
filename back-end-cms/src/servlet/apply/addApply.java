package servlet.apply;

import bean.ApplyMessage;
import bean.Association;
import bean.Page;
import bean.ResponseObject;
import com.google.gson.GsonBuilder;
import dao.ApplyDao;
import dao.AssociationDao;
import dao.impl.ApplyImpl;
import dao.impl.AssociationImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet("/apply/addApply")
public class addApply extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public addApply() {
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

        //获取传入信息
        ApplyMessage applyMessage = new ApplyMessage();
        applyMessage.setAs_name(request.getParameter("as_name"));
        applyMessage.setStu_id(stu_id);
        applyMessage.setAp_content(request.getParameter("ap_content"));

        ApplyDao applyDao = new ApplyImpl();
        int res = applyDao.addApply(applyMessage);
        //输出流
        PrintWriter out = response.getWriter();
        //返回体
        ResponseObject result = null;
        //登陆操作
        if(res!= 0) {
            result = new  ResponseObject(200,"申请成功!");

        }else{
            result = new ResponseObject(500,"申请失败,您已经申请过该社团了");
        }
        out.println(new GsonBuilder().create().toJson(result));
        out.flush();
        out.close();
    }
}
