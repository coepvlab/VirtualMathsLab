package in.ac.coep.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.stereotype.Controller;

import in.ac.coep.constants.Constants;

@Controller
public class CustomAuthenticationHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		String ctoken = (String) request.getSession().getAttribute(Constants.CSRF_TOKEN);
		DefaultSavedRequest defaultSavedRequest = (DefaultSavedRequest) request.getSession()
				.getAttribute("SPRING_SECURITY_SAVED_REQUEST_KEY");
		if (defaultSavedRequest != null && ctoken != null) {
			String requestUrl = defaultSavedRequest.getRequestURL() + "?" + defaultSavedRequest.getQueryString();
			getRedirectStrategy().sendRedirect(request, response, requestUrl);
		} else {
			super.onAuthenticationSuccess(request, response, authentication);
		}
	}

	@Override
	protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {
		return "/home";
	}
}