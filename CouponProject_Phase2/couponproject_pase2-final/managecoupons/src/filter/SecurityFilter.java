package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

@WebFilter(urlPatterns={"/couponsys/*"})
public class SecurityFilter implements Filter {
	
    public SecurityFilter() {
    }

    public void init(FilterConfig fConfig) throws ServletException {
    	// do nothing
    }

	public void destroy() {
		// do nothing
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		boolean block=false;
		
		if(!block){
		block=filtrate("/managecoupons/couponsys/admin", "/managecoupons/couponsys/admin/login", 
				"adminFacade", "admin",
				req, resp,  chain);
		}else{
			return;
		}
		
		if(!block){
		block=filtrate("/managecoupons/couponsys/company/", "/managecoupons/couponsys/company/login", 
				"companyFacade", "company",
				req, resp,  chain);
		}else{
			return;
		}
		
		if(!block){
		block=filtrate("/managecoupons/couponsys/customer", "/managecoupons/couponsys/customer/login", 
				"customerFacade", "customer",
				req, resp,  chain);
		}else{
			return;
		}

		
		chain.doFilter(req, resp);
	}

	private boolean filtrate(String serviceurl, String loginurl, String facadetype, String usertype,
										ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException{

		// you must CAST the input to the right type first
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		// validate the user is NOT trying to login, otherwise keep filtering
		String url = request.getRequestURI();
		if (url.startsWith(serviceurl) && !url.startsWith(loginurl)) {
			HttpSession session = request.getSession(false);
			if (session == null || session.getAttribute(facadetype) == null) {
				response.setStatus(500);
				// 3rd party jar
				JSONObject jso = new JSONObject();
				try {
					jso.put("message", "-filter block- you must log in as " + usertype + " first!");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				response.getWriter().print(jso.toString());
				response.setContentType(MediaType.APPLICATION_JSON);

				return true;
			}
		}
		return false;
	}
	
}