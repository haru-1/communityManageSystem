package servlet.president;

import bean.*;
import com.google.gson.GsonBuilder;
import dao.AttendDao;
import dao.impl.AttendImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/president/addApplyActivity")
public class addApplyActivity extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public addApplyActivity() {
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
        Activity activity = new Activity();
        activity.setAs_name(request.getParameter("as_name"));
        activity.setAct_name(request.getParameter("act_name"));
        activity.setAct_content(request.getParameter("act_content"));
        activity.setAct_form(request.getParameter("act_form"));
        activity.setAct_address(request.getParameter("act_address"));
        activity.setAct_regStart(request.getParameter("act_regStart"));
        activity.setAct_regEnd(request.getParameter("act_regEnd"));
        activity.setAct_start(request.getParameter("act_start"));
        activity.setAct_end(request.getParameter("act_end"));
        activity.setAct_number(request.getParameter("act_number"));
        activity.setAct_level(request.getParameter("act_level"));

        AttendDao attendDao = new AttendImpl();
        int res = attendDao.addApplyActivity(activity);
        //输出流
        PrintWriter out = response.getWriter();
        //返回体
        ResponseObject result = null;
        //登陆操作
        if(res!= 0) {
            result = new  ResponseObject(200,"提交活动申请成功!");

        }else{
            result = new ResponseObject(500,"提交活动申请失败!");
        }
        out.println(new GsonBuilder().create().toJson(result));
        out.flush();
        out.close();
    }
}
