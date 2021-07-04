package Filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DanhMucDao;
import dao.SanPhamDao;
import model.Sanpham;

/**
 * Servlet Filter implementation class FilterSanPham
 */
@WebFilter(urlPatterns ={"/Call/H"})
public class FilterSanPham implements Filter {

    /**
     * Default constructor. 
     */
    public FilterSanPham() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
				SanPhamDao dao = new SanPhamDao();
				DanhMucDao daodm = new DanhMucDao();
				request.setAttribute("listdm",daodm.findAll());
				String danhmuc = request.getParameter("idDm");
				if(danhmuc==null) {
					danhmuc= "";
				}
				int tongsosp = dao.count("%"+danhmuc);
				int sotrang=0;
				if((tongsosp % 8)>0 && tongsosp>8) {
					sotrang = (tongsosp-(tongsosp % 8))/8+1;
				}
				if(tongsosp<=8) {
					sotrang = 1;
				}
				if((tongsosp % 8)==0 && tongsosp>8) {
					sotrang = tongsosp/8;
				}
				int a[]=new int[sotrang];
				for(int i =0 ; i <sotrang;i++) {
					 a[i]=i+1;
				}
				int index  = 0; ;
				if(request.getParameter("st")!=null) {
						index = Integer.parseInt(request.getParameter("st"));
				}
				request.setAttribute("sotrang",a);
				List<Sanpham> dssp = dao.fillAll(index, 8,"%"+danhmuc);
				request.setAttribute("listsp", dssp);
				request.setAttribute("trang", "sanphamtt.jsp");
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
