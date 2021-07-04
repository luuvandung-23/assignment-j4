package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import dao.UserDao;
import model.User_;
@WebServlet({"/UserServlet/home","/UserServlet/create","/UserServlet/update","/UserServlet/edit","/UserServlet/disable","/UserServlet/reset"})
public class QLUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url= request.getRequestURL().toString();
		ServletContext servletcontext = getServletContext();
		servletcontext.setAttribute("message_qltk", null);
		servletcontext.setAttribute("error_qltk", null);
		if(url.contains("home")) {
		servletcontext.setAttribute("trang","/admin/quanlitk.jsp");
		}
		else if(url.contains("disable")) {
			disable(request, response);
		}else if(url.contains("edit")) {
			edit(request, response);
		}
		findAll(request, response);
		response.sendRedirect(request.getContextPath() + "/Call/home");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext servletcontext = getServletContext();
		servletcontext.setAttribute("message_qltk", null);
		servletcontext.setAttribute("error_qltk", null);
		String url= request.getRequestURL().toString();
		if(url.contains("create")) {
			insert(request, response);
		}else if(url.contains("update")) {
			update(request, response);
		}else if(url.contains("reset")) {
			servletcontext.setAttribute("user_qltk",new User_());
		}
		findAll(request, response);
		response.sendRedirect(request.getContextPath() + "/Call/home");
	}
	
	protected void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext servletcontext = getServletContext();
		try {
			UserDao dao = new UserDao();
			List<User_> list = dao.findAll();
			servletcontext.setAttribute("users",list);
			} catch (Exception e) {
			 e.printStackTrace();
			 request.setAttribute("error_qltk", "Error: "+ e.getMessage());
		}
		
	}
	protected void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext servletcontext = getServletContext();
		try {
			User_ user = new User_();
			BeanUtils.populate(user,request.getParameterMap());
			user.setTrangthai(true);
			UserDao dao = new UserDao();
			dao.insert(user);
			servletcontext.setAttribute("message_qltk", "User inserted !!");
			} catch (Exception e) {
			 e.printStackTrace();
			 servletcontext.setAttribute("error_qltk", "Error: "+ e.getMessage());
		}
		
	}
	protected void disable(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext servletcontext = getServletContext();
		try {
			User_ user = new User_();
			String userId = request.getParameter("idUser");
			UserDao dao = new UserDao();
			user = dao.findById(userId);
			if(user.getTrangthai()==true) {
				user.setTrangthai(false);
				servletcontext.setAttribute("message_qltk", "User disable !!");
			}else {
				user.setTrangthai(true);
				servletcontext.setAttribute("message_qltk", "User Active !!");
			}
			dao.update(user);
			
			} catch (Exception e) {
			 e.printStackTrace();
			 servletcontext.setAttribute("error_qltk", "Error: "+ e.getMessage());
		}
		
	}
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext servletcontext = getServletContext();
		try {
			User_ user = new User_();
			BeanUtils.populate(user,request.getParameterMap());
			UserDao dao = new UserDao();
			dao.update(user);
			servletcontext.setAttribute("user_qltk",user);
			servletcontext.setAttribute("message_qltk", "User updated !!");
			} catch (Exception e) {
			 e.printStackTrace();
			 servletcontext.setAttribute("error_qltk", "Error: "+ e.getMessage());
		}
		
	}
	protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext servletcontext = getServletContext();
		try {
			String userId = request.getParameter("idUser");
			UserDao dao = new UserDao();
			User_ user = dao.findById(userId);
			servletcontext.setAttribute("user_qltk",user);
			} catch (Exception e) {
			 e.printStackTrace();
			 servletcontext.setAttribute("error_qltk", "Error: "+ e.getMessage());
		}
		
	}
}
