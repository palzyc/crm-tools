package common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) arg0;
		HttpServletResponse resp = (HttpServletResponse) arg1;
		String uri = req.getRequestURI();
		HttpSession session = req.getSession();
		if (uri.endsWith("css") || uri.endsWith("js") || /* uri.endsWith("html") || */uri.endsWith("index.jsp") || uri.endsWith("login")) {
			chain.doFilter(req, resp);
		} else {
			if (session.getAttribute(Const.SESSIONKEY_LOGIN_USER) == null) {
				resp.sendRedirect("index.jsp");
			} else {
				chain.doFilter(req, resp);
			}
		}
	}

	public void init(FilterConfig config) throws ServletException {
	}

}
