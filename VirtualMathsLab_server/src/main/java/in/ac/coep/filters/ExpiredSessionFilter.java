package  in.ac.coep.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.filter.GenericFilterBean;


@Service("checkExpiredSessions")
public class ExpiredSessionFilter extends GenericFilterBean {

	static final String FILTER_APPLIED = "__spring_security_expired_session_filter_applied";

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		String requestMapping = request.getRequestURI().substring(
				request.getContextPath().length());
		HttpServletResponse response = (HttpServletResponse) res;
		if (!requestMapping.contains("ajax")) {
			if (request.getAttribute(FILTER_APPLIED) != null) {
				chain.doFilter(request, response);
				return;
			}

			request.setAttribute(FILTER_APPLIED, Boolean.TRUE);
			if (request.getRequestedSessionId() != null
					&& !request.isRequestedSessionIdValid()) {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
						"SESSION_TIMED_OUT");
				return;
			}

		}
	

		chain.doFilter(request, response);
	}
}
