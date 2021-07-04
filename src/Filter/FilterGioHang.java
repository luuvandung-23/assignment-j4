package Filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.HoaDonDao;
import model.Hoadon;
import model.Hoadonct;
import model.User_;

/**
 * Servlet Filter implementation class FilterGioHang
 */
@WebFilter({"/Call/giohang","/Call/delete"})
public class FilterGioHang implements Filter {

    
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			HttpServletRequest req = (HttpServletRequest)request ;
			User_ user =(User_) req.getSession().getAttribute("user");
			HoaDonDao dao = new HoaDonDao();
			Hoadon hd = dao.findByTrangthai(user.getIdUser(), "giohang");
			List<Hoadonct> hdct = dao.findHoadonct(hd.getIdHd());
			request.setAttribute("thanhtien",hd.getTongtien()+"$");
			request.setAttribute("dshoadonct",hdct);
			}catch (Exception e) {
				request.setAttribute("message","Chưa có sản phẩm trong");
			}
				
		chain.doFilter(request, response);
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
