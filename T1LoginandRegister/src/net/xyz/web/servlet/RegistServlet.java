package net.xyz.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.xyz.user.domain.User;
import net.xyz.user.service.UserException;
import net.xyz.user.service.UserService;

/**
 * Servlet implementation class RegistServlet
 */
@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegistServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置编码方式
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		UserService userService=new UserService();
		//封装表单数据，调用UserService层，如果错误显示异常信息并保存到request域中
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String verifyCode=request.getParameter("verifyCode");
		User user=new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setVerifyCode(verifyCode);
		//校验用户表单是否符合规范，用map来转错误信息
		Map<String,String> errors=new HashMap<String,String>();
		//校验用户名是否符合规范
		if(username==null||username.trim().isEmpty()) {
			errors.put("username", "用户名不能为空");
		}else if(username.length()<3||username.length()>15) {
			errors.put("username","用户名长度必须在3-15之间！");
		}
		//校验密码是否符合规范
		if(password==null||password.trim().isEmpty()) {
			errors.put("password", "密码不能为空");
		}else if(password.length()<3||password.length()>15) {
			errors.put("password","密码长度必须在3-15之间！");
		}
		/*
		 * 判断map是否为空，不为空，说明存在错误
		 * 
		 */
		System.out.println(errors);
		if(errors!=null&&errors.size()>0) {
			/*
			 * 1.保存errors到request域
			 * 2.保存form到request域中，为了回显
			 * 3.转发到regist.jsp
			 */
			request.setAttribute("errors", errors);
			request.setAttribute("user", user);
			request.getRequestDispatcher("/user/regist.jsp").forward(request, response);
			return;
		}
		//校验用户验证码是否正确，如果不正确保存错误信息
		String sessionVerifyCode=(String) request.getSession().getAttribute("session");
		if(!sessionVerifyCode.equals(verifyCode)) {
			request.setAttribute("msg", "验证码填写错误");
			request.getRequestDispatcher("/user/regist.jsp").forward(request, response);;
		}
			try {
				userService.registUser(user);
				response.getWriter().write("<h1>注册成功<h1>"+"<a href='"+request.getContextPath()+"/user/login.jsp'>点击这里登录</a>");
			} catch (UserException e) {
				
				request.setAttribute("msg", e.getMessage());
				request.getRequestDispatcher("/user/regist.jsp").forward(request, response);;
				System.out.println("出错了");
			}
			
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
