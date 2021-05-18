package innova.pacs.api.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import innova.pacs.api.model.User;
import innova.pacs.api.model.service.UserService;

@Component
public class InfoAdicionalToken implements TokenEnhancer{
	
	@Autowired
	private UserService userService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		User user = userService.findByUsername(authentication.getName());
		Map<String, Object> info = new HashMap<>();
		info.put("info_adicional", "Hola que tal!: ".concat(authentication.getName()));
		info.put("nombre", user.getUsername());
		info.put("email", user.getEmail());
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		
		return accessToken;
	}

}
