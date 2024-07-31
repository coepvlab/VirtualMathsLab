package in.ac.coep.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import in.ac.coep.entity.User;
import in.ac.coep.serviceImpl.CustomUserService;
import in.ac.coep.utility.Md5Encryption;

/**
 * @author Vaibhav
 *
 */

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private CustomUserService userService;

	@SuppressWarnings("static-access")
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String uname = authentication.getName();
		String password = (String) authentication.getCredentials();
		User user = null;

		if (uname.contains("@")) {
			user = userService.loadUserByUsername(uname);
			if (user == null) {
				throw new BadCredentialsException("Username not found.");
			}

		} else {
			user = userService.loadUserByUserId(Long.parseLong(uname));
			if (user == null) {
				throw new BadCredentialsException("Username not found.");
			}
		}

		Md5Encryption encryption = new Md5Encryption();



		if(user.isEmailValidated() == false) {
			throw new BadCredentialsException(
					"<B>Please verify your account by following the link sent to your registered email address.</B><br>");
		}
		
		if (user.isAccountLocked() == true) {
			throw new BadCredentialsException(
					"<B>Your account is locked. Please request admin to unlock your account.</B><br>");
		} 
		
		if(user.isCredentialsExpired() == true) {
			throw new BadCredentialsException(
					"<B>Your account is locked. Please request admin to assign role to your account.</B><br>");
		}
		
		if (user.getAuthorities().size() == 0) {
			throw new BadCredentialsException("<B>Please request admin to activate role for your account.</B><br>");
		}
		
		if (!user.getPassword().equals(encryption.md5(password))) {
			throw new BadCredentialsException("Wrong password.");
	}

		Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
		return new UsernamePasswordAuthenticationToken(user, password, authorities);
	}

	public boolean supports(Class<?> arg0) {
		return true;
	}
}