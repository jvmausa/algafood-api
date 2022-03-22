package com.jvmausa.algafood.core.security.authorizationServer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

	@GetMapping(path = "/login")
	public String login(){
		return "pages/login";
	}	
	
	@GetMapping(path = "/oauth/confirm_access")
	public String approval() {
		return "pages/approval";
		
	}
	
	
	
}
