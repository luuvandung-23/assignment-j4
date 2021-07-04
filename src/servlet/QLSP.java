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

import dao.DanhMucDao;
import dao.SanPhamDao;
import dao.UserDao;
import model.Danhmuc;
import model.Sanpham;
import model.User_;

/**
 * Servlet implementation class QLSP
 */
@WebServlet({"/QLSP/home","/QLSP/create","/QLSP/update","/QLSP/edit","/QLSP/disable","/QLSP/reset"})
public class QLSP extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url= request.getRequestURL().toString();
		ServletContext servletcontext = getServletContext();
		servletcontext.setAttribute("message_qlsp", null);
		servletcontext.setAttribute("error_qlsp", null);
		if(url.contains("home")) {
		servletcontext.setAttribute("trang","/admin/quanlisp.jsp");
		DanhMucDao dao = new DanhMucDao();
		servletcontext.setAttribute("category",dao.findAll());
		}
		else if(url.contains("disable")) {
			disable(request, response);
		}else if(url.contains("edit")) {
			edit(request, response);
		}
		findAll(request, response);
		response.sendRedirect(request.getContextPath() + "/Call/home");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext servletcontext = getServletContext();
		servletcontext.setAttribute("message_qlsp", null);
		servletcontext.setAttribute("error_qlsp", null);
		String url= request.getRequestURL().toString();
		if(url.contains("create")) {
			insert(request, response);
		}else if(url.contains("update")) {
			update(request, response);
		}else if(url.contains("reset")) {
			servletcontext.setAttribute("sp",new Sanpham());
		}
		findAll(request, response);
		response.sendRedirect(request.getContextPath() + "/Call/home");
	}
	protected void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext servletcontext = getServletContext();
		try {
			SanPhamDao dao = new SanPhamDao ();
			List<Sanpham> list = dao.findAll();
			servletcontext.setAttribute("products",list);
			} catch (Exception e) {
			 e.printStackTrace();
			 request.setAttribute("error_qlsp", "Error: "+ e.getMessage());
		}
		
	}
	protected void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext servletcontext = getServletContext();
		try {
			Sanpham sp = new Sanpham();
			Danhmuc dm = new Danhmuc();
			dm.setIdDm(request.getParameter("idDmSelect"));
			BeanUtils.populate(sp,request.getParameterMap());
			SanPhamDao dao = new SanPhamDao();
			sp.setTrangthai(true);
			sp.setDanhmuc(dm);
			servletcontext.setAttribute("dm", sp.getDanhmuc().getIdDm());
			dao.insert(sp);
			servletcontext.setAttribute("message_qlsp", "User inserted !!");
			} catch (Exception e) {
			 e.printStackTrace();
			 servletcontext.setAttribute("error_qlsp", "Error: "+ e.getMessage());
		}
		
	}
	protected void disable(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext servletcontext = getServletContext();
		try {
			Sanpham sp = new Sanpham();
			String id = request.getParameter("idSp");
			SanPhamDao dao = new SanPhamDao();
			sp = dao.findById(id);
			if(sp.getTrangthai()==true) {
				sp.setTrangthai(false);
				servletcontext.setAttribute("message_qlsp", "Product disable !!");
			}else {
				sp.setTrangthai(true);
				servletcontext.setAttribute("message_qlsp", "Product Active  !!");
			}
			dao.update(sp);
			
			} catch (Exception e) {
			 e.printStackTrace();
			 servletcontext.setAttribute("error_qlsp", "Error: "+ e.getMessage());
		}
		
	}
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext servletcontext = getServletContext();
		try {
			Sanpham sp = new Sanpham();
			Danhmuc dm = new Danhmuc();
			dm.setIdDm(request.getParameter("idDmSelect"));
			BeanUtils.populate(sp,request.getParameterMap());
			SanPhamDao dao = new SanPhamDao();
			sp.setTrangthai(true);
			sp.setDanhmuc(dm);
			dao.update(sp);
			servletcontext.setAttribute("sp",sp);
			servletcontext.setAttribute("message_qlsp", "User updated !!");
			servletcontext.setAttribute("dm", sp.getDanhmuc().getIdDm());
			} catch (Exception e) {
			 e.printStackTrace();
			 servletcontext.setAttribute("error_qlsp", "Error: "+ e.getMessage());
		}
		
	}
	protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext servletcontext = getServletContext();
		try {
			String id = request.getParameter("idSp");
			SanPhamDao dao = new SanPhamDao();
			Sanpham sp = dao.findById(id);
			servletcontext.setAttribute("dm", sp.getDanhmuc().getIdDm());
			servletcontext.setAttribute("sp",sp);
			} catch (Exception e) {
			 e.printStackTrace();
			 servletcontext.setAttribute("error_qlsp", "Error: "+ e.getMessage());
		}
		
	}
}

