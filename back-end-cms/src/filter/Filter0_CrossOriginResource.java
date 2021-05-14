package filter;

import com.thetransactioncompany.cors.CORSConfiguration;
import com.thetransactioncompany.cors.CORSFilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 服务端跨域处理过滤器,该过滤器需要依赖cors-filter-2.2.1.jar和java-property-utils-1.9.1.jar
 * 
 *
 */
@WebFilter(urlPatterns={"/*"},asyncSupported=true)
//initParams={
//	@WebInitParam(name="cors.allowOrigin",value="*"),
//	@WebInitParam(name="cors.supportedMethods",value="CONNECT, DELETE, GET, HEAD, OPTIONS, POST, PUT, TRACE"),
//	@WebInitParam(name="cors.supportedHeaders",value="*"),//注意，如果token字段放在请求头传到后端，这里需要配置
//	@WebInitParam(name="cors.exposedHeaders",value="Set-Cookie"),
//	@WebInitParam(name="cors.supportsCredentials",value="true")
//})
public class Filter0_CrossOriginResource extends CORSFilter implements javax.servlet.Filter{


	public void init(FilterConfig config) throws ServletException {
//		System.out.println("跨域资源处理过滤器初始化了");
//		super.init(config);
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("跨域过滤器");
//		super.doFilter(request, response, chain);
		HttpServletResponse resp = (HttpServletResponse) response;
		// 添加参数，允许任意domain访问
		resp.setHeader("Access-Control-Allow-Origin", "*");
		// 这个allow-headers要配为*，这样才能允许所有的请求头 --- update by zxy  in 2018-10-19
		resp.setHeader("Access-Control-Allow-Headers", "*");
		resp.setHeader("Access-Control-Allow-Methods","PUT,POST,GET,DELETE,OPTIONS");
		resp.setHeader("Access-Control-Allow-Credentials", "true");
//		resp.setHeader("Access-Control-Max-Age", time+"");
		chain.doFilter(request, resp);
	}


//	public void setConfiguration(CORSConfiguration config) {
//		super.setConfiguration(config);
//	}
	
}
