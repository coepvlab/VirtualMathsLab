/**
 * 
 */
package in.ac.coep.utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

/**
 * @author VLab
 *
 */
@Service(value = "customSuccessHandler")
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	 private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	 
	 
	 @Override
	    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
	            throws IOException {
	        String targetUrl = determineTargetUrl(authentication);
	 
	        if (response.isCommitted()) {
	            System.out.println("Can't redirect");
	            return;
	        }
	 
	        redirectStrategy.sendRedirect(request, response, targetUrl);
	    }
	 
	 
	 protected String determineTargetUrl(Authentication authentication) {
	        String url = "";
	 
	        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
	 
	        List<String> roles = new ArrayList<String>();
	 
	        for (GrantedAuthority a : authorities) {
	            roles.add(a.getAuthority());
	        }
	 
	        if (isUser(roles)) {
	            url = "/home";
	        } 
	        return url;
	    }
	 
	    private boolean isUser(List<String> roles) {
	        if (roles.contains("STUDENT")) {
	            return true;
	        }
	        return false;
	    }
	 
	  	 
	    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
	        this.redirectStrategy = redirectStrategy;
	    }
	 
	    protected RedirectStrategy getRedirectStrategy() {
	        return redirectStrategy;
	    }
	 
	}
	 


