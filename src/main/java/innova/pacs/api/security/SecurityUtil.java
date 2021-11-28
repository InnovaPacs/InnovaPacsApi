package innova.pacs.api.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtil {
	/**
	 * Get user name in session
	 * @return
	 */
	public static String getUsername() {
		String username = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
		  
			username = ((UserDetails)principal).getUsername();
		} else {
		  
			username = principal.toString();
		}
		
		return username;
	}
}
