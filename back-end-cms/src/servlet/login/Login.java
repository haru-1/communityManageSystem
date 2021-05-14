package servlet.login;

import bean.Association;
import bean.Page;
import bean.ResponseObject;
import bean.Student;
import com.google.gson.GsonBuilder;
import dao.AssociationDao;
import dao.LoginDao;
import dao.impl.AssociationImpl;
import dao.impl.LoginImpl;
import net.minidev.json.JSONObject;
import util.jwt.Jwt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet("/login/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		int id = Integer.valueOf(request.getParameter("id"));
		String password = request.getParameter("password");

		//登陆注册对象
		LoginDao loginDao = new LoginImpl();
		//输出流
		PrintWriter out = response.getWriter();
		//返回体
//		ResponseObject result = null;
		//登陆操作
			Student student = loginDao.login(id, password);
			List<Student> list = new ArrayList<Student>();
			if(student != null) {
				//生成token
				Map<String , Object> payload=new HashMap<String, Object>();
				Date date=new Date();
				payload.put("uid", id);//用户ID
				payload.put("iat", date.getTime());//生成时间
				payload.put("ext",date.getTime()+1000*60*60*72);//过期时间72小时
				String token= Jwt.createToken(payload);
				list.add(student);
//				result = new  ResponseObject(200,"登陆成功!",token);

				resultJSON.put("state", 200);
				resultJSON.put("msg", "登陆成功");
				resultJSON.put("token", token);
				resultJSON.put("name", student.getStu_name());



			}else{
//				result = new ResponseObject(500,"用户信息错误!");
				resultJSON.put("state", 500);
				resultJSON.put("msg", "用户名密码不对");
			}
//		out.println(new GsonBuilder().create().toJson(result));
		out.println(resultJSON.toJSONString());
		out.flush();
		out.close();
	}
}


