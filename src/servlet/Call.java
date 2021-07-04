package servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.User;
import org.apache.commons.beanutils.BeanUtils;

import dao.DanhMucDao;
import dao.HoaDonDao;
import dao.SanPhamDao;
import dao.UserDao;
import model.Hoadon;
import model.Hoadonct;
import model.HoadonctPK;
import model.Sanpham;
import model.User_;
import utils.CookieUtils;
import utils.JpaUtils;

/**
 * Servlet implementation class Call
 */
@WebServlet({"/Call/H","/Call/home","/Call/login","/Call/register","/Call/logout","/Call/changeUser","/Call/giohang","/Call/ttsp","/Call/delete","/Call/dathang"})
public class Call extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri= request.getRequestURI();
		ServletContext servletcontext = getServletContext();
		if(uri.contains("/Call/home")) {
			request.setAttribute("trang",servletcontext.getAttribute("trang"));
		}
		if(uri.contains("login")) {
			request.setAttribute("trang", "login.jsp");
		}
		if(uri.contains("register")) {
			request.setAttribute("trang", "dangki.jsp");
			
		}
		if(uri.contains("logout")) {
			request.getSession().setAttribute("user", null);
			request.setAttribute("trang", "login.jsp");
		}
		if(uri.contains("changeUser")) {
			request.setAttribute("trang", "changeUser.jsp");
		}
		if(uri.contains("giohang")) {
			request.setAttribute("trang", "giohang.jsp");
		}
		if(uri.contains("ttsp")) {
			if(request.getSession().getAttribute("user")==null) {
				request.setAttribute("trang", "login.jsp");
			}
			else {
				SanPhamDao dao = new SanPhamDao();
				Sanpham sanpham =  dao.findById(request.getParameter("msp"));
				request.getSession().setAttribute("sp",sanpham);
				request.setAttribute("trang", "thongtin1sp.jsp");
			}
		}
		if(uri.contains("delete")) {
			deletesp(request, response);
			request.setAttribute("trang", "giohang.jsp");
		}
		request.getRequestDispatcher("/views/trangchu.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri= request.getRequestURI();
		DanhMucDao daodm = new DanhMucDao();
		request.setAttribute("listdm",daodm.findAll());
		if(uri.contains("login")) {
			CheckPass(request, response);
		}
		if(uri.contains("register")) {
			Register(request, response);
		}
		if(uri.contains("changeUser")) {
			request.setAttribute("trang", "changeUser.jsp");
			UpdateAccount(request, response);
		}
		if(uri.contains("dathang")) {
			dathang(request, response);
			request.setAttribute("trang", "giohang.jsp");
		}
		if(uri.contains("giohang")) {
			themspVaoGio(request, response);
			request.setAttribute("trang", "giohang.jsp");
		}
		
		request.getRequestDispatcher("/views/trangchu.jsp").forward(request, response);
	}
	public void CheckPass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("username");
		String pw = request.getParameter("password");
		try {
		UserDao dao = new UserDao();
		User_ user = dao.findById(id);
		if(!user.getPassworD().equals(pw)) {
		request.setAttribute("trang", "login.jsp");
		request.setAttribute("error", "Sai mật khẩu!");
		}
		else {
		if(user.getTrangthai()==false) {
			request.setAttribute("trang", "login.jsp");
			request.setAttribute("error", "Tài khoản đã buộc tạm ngừng !");
			return;
		}
		int hours = (request.getParameter("remember-me") == null) ? 0 : 30*24;
		CookieUtils.add("taikhoan",id, hours, response);
		CookieUtils.add("matkhau",pw, hours, response);
		request.setAttribute("trang", "sanphamtt.jsp");
		SanPhamDao d = new SanPhamDao();
		List<Sanpham> dssp = d.fillAll(0, 8,"%");
		request.setAttribute("listsp", dssp);
		request.getSession().setAttribute("user", user);
		}
		} catch (Exception e) {
		request.setAttribute("trang", "login.jsp");
		request.setAttribute("error", "Sai tên đăng nhập!");
		}
	}
	public void Register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		UserDao dao = new UserDao();
		User_ user1 = dao.findById(request.getParameter("idUser"));
		if(user1!=null) {
			request.setAttribute("error", "Tài khoản đã tồn tại!");
			request.setAttribute("trang", "dangki.jsp");
			return;
		}
		User_ user =  new User_();
		user.setAdmiN(false);
		user.setTrangthai(true);
		BeanUtils.populate(user,request.getParameterMap());
		if(user.getFullname()=="" || user.getDiachi()=="" || user.getEmail()==""|| user.getIdUser()=="" || user.getPassworD()=="" ||request.getParameter("nlpassword")== "" ) {
			request.setAttribute("trang", "dangki.jsp");
			request.setAttribute("error", "Không được để trống các trường!");
			return;
		}
		if(user.getPassworD().equalsIgnoreCase(request.getParameter("nlpassword"))==false) {
			request.setAttribute("trang", "dangki.jsp");
			request.setAttribute("error", "Mật khẩu không khớp!");
			return;
		}
		String mauEmail = "\\w+@\\w+(\\.\\w+){1,2}";
		if(!user.getEmail().matches(mauEmail)) {
			request.setAttribute("trang", "dangki.jsp");
			request.setAttribute("error", "Nhập đúng đinh dạng email!");
			return;
		}
		dao.insert(user);
		request.setAttribute("trang", "sanphamtt.jsp");
		SanPhamDao d = new SanPhamDao();
		List<Sanpham> dssp = d.fillAll(0, 8,"%");
		request.setAttribute("listsp", dssp);
		request.getSession().setAttribute("user", user);
		} catch (Exception e) {
		request.setAttribute("trang", "dangki.jsp");
		request.setAttribute("error", "Lỗi!");
		}
	}
	public void UpdateAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		UserDao dao = new UserDao();
		User_ user =  new User_();
		BeanUtils.populate(user,request.getParameterMap());
		if(user.getFullname()=="" || user.getDiachi()=="" || user.getEmail()==""|| user.getPassworD()=="" ||request.getParameter("nlpassword")== "" ) {
			request.setAttribute("error", "Không được để trống các trường!");
			return;
		}
		if(user.getPassworD().equalsIgnoreCase(request.getParameter("nlpassword"))==false) {
			request.setAttribute("error", "Mật khẩu không khớp!");
			return;
		}
		String mauEmail = "\\w+@\\w+(\\.\\w+){1,2}";
		if(!user.getEmail().matches(mauEmail)) {
			request.setAttribute("error", "Nhập đúng đinh dạng email!");
			return;
		}
		dao.update(user);
		request.getSession().setAttribute("user", user);
		request.setAttribute("message", "Update thành công!");
		} catch (Exception e) {
		request.setAttribute("error", "Lỗi!");
		}
	}
	public void themspVaoGio(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Sanpham sp = (Sanpham) request.getSession().getAttribute("sp");
		User_ user = (User_) request.getSession().getAttribute("user");
		HoaDonDao dao = new HoaDonDao();
		Hoadon hd = new Hoadon();
		Hoadonct hdct = new Hoadonct();
		HoadonctPK hoadonctPK = new HoadonctPK();
		hdct.setSanpham(sp);
		Hoadon hdcosan =null;
		hoadonctPK.setIdSp(sp.getIdSp());
		hdct.setSoluong(Integer.parseInt(request.getParameter("so")));
		hdct.setGia(sp.getGia());
		hdct.setThanhtien(sp.getGia()*Integer.parseInt(request.getParameter("so")));
		//hoadon
		hd.setTenkh(user.getFullname());
		hd.setUser(user);
		hd.setTrangthai("giohang");
		hd.setNgaytao(String.valueOf(java.time.LocalDate.now()));
		hd.setTongtien(0);
		try {
				int tong = 0;
				hdcosan = dao.findByTrangthai(user.getIdUser(), "giohang");
				hoadonctPK.setIdHd(hdcosan.getIdHd());
				hdct.setId(hoadonctPK);
				if(dao.findHoadonct(hdcosan.getIdHd(), sp.getIdSp())==null) {
				dao.inserthdct(hdct);
				}
				else {
				Hoadonct hdct1 = dao.findHoadonct(hdcosan.getIdHd(), sp.getIdSp());
				hdct.setSoluong(Integer.parseInt(request.getParameter("so"))+hdct1.getSoluong());
				hdct.setThanhtien(sp.getGia()*hdct.getSoluong());
				dao.updatehdct(hdct);
				}
				List<Hoadonct> hdctcosan = dao.findHoadonct(hdcosan.getIdHd()); 
				for(Hoadonct x : hdctcosan) {
					tong = (int) (tong + x.getThanhtien());
				}
				hd.setTongtien(tong);
				hd.setIdHd(hdcosan.getIdHd());
				dao.updatehd(hd);
		}catch (Exception e) {
				dao.inserthd(hd);
				hdcosan = dao.findByTrangthai(user.getIdUser(), "giohang");
				hoadonctPK.setIdHd(hdcosan.getIdHd());
				hdct.setId(hoadonctPK);
				dao.inserthdct(hdct);
				hd.setIdHd(hdcosan.getIdHd());
				hd.setTongtien(hdct.getThanhtien());
				dao.updatehd(hd);
		}
		
		
		
	}
	public void deletesp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Sanpham sp = (Sanpham) request.getSession().getAttribute("sp");
		User_ user = (User_) request.getSession().getAttribute("user");
		HoaDonDao dao = new HoaDonDao();
		Hoadon hd = new Hoadon();
		int id = Integer.parseInt(request.getParameter("idhd"));
		String idsp= request.getParameter("idsp");
		try {
			dao.delete(id,idsp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		Hoadon hdcosan = dao.findByTrangthai(user.getIdUser(), "giohang");
		hd.setTenkh(user.getFullname());
		hd.setUser(user);
		hd.setTrangthai("giohang");
		hd.setNgaytao(String.valueOf(java.time.LocalDate.now()));
		List<Hoadonct> hdctcosan = dao.findHoadonct(hdcosan.getIdHd()); 
		int tong = 0;
		for(Hoadonct x : hdctcosan) {
			tong = (int) (tong + x.getThanhtien());
		}
		hd.setTongtien(tong);
		hd.setIdHd(hdcosan.getIdHd());
		dao.updatehd(hd);
	}
	public void dathang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Sanpham sp = (Sanpham) request.getSession().getAttribute("sp");
		User_ user = (User_) request.getSession().getAttribute("user");
		HoaDonDao dao = new HoaDonDao();
		Hoadon hd = new Hoadon();
		Hoadon hdcosan = dao.findByTrangthai(user.getIdUser(), "giohang");
		hd.setTenkh(user.getFullname());
		hd.setUser(user);
		hd.setTrangthai("dathang");
		hd.setNgaytao(String.valueOf(java.time.LocalDate.now()));
		List<Hoadonct> hdctcosan = dao.findHoadonct(hdcosan.getIdHd()); 
		int tong = 0;
		for(Hoadonct x : hdctcosan) {
			tong = (int) (tong + x.getThanhtien());
		}
		hd.setTongtien(tong);
		hd.setIdHd(hdcosan.getIdHd());
		dao.updatehd(hd);
	}
}
