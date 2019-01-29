package com.cfstarter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cfstarter.domain.Destination;
import com.cfstarter.domain.TokenInfo;
import com.cfstarter.service.CloudService;
import com.cfstarter.service.SecurityService;
import com.sap.cloud.sdk.cloudplatform.CloudPlatform;
import com.sap.cloud.sdk.cloudplatform.security.RolesAllowed;

@Controller
public class HomeController {
	
	@Autowired
	private CloudPlatform platform;
	
	@Autowired
	private CloudService cloudService;
	
	@Autowired 
	private SecurityService securityService;
	

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String getHome(Model model) {
		String appName = platform.getApplicationName();
		model.addAttribute("appName", appName);
		List<Destination> destinations = cloudService.getDestinations();
		model.addAttribute("destinations", destinations);
		return "index";
	}
	
	@RequestMapping(value="/auth", method=RequestMethod.GET)
	public String getAuth(Model model) {
		List<String> scopes = securityService.getScopes();
		model.addAttribute("scopes", scopes);
		TokenInfo token = securityService.getTokenInfo();
		model.addAttribute("token", token);
		return "auth";
	}
	
	@RequestMapping(value="/failed", method=RequestMethod.GET)
	public String getRoleCheckFailed() {
		return "scope";
	}
	
	@RequestMapping(value="/scope", method=RequestMethod.GET)
	public String checkScope() {
		securityService.userHasAuthorization("Display");
		return "scope";
	}
	
	@RequestMapping(value="/scopeFail", method=RequestMethod.GET)
	public String checkScopeFailed() {
		securityService.userHasAuthorization("Download");
		return "scope";
	}
	
	@RolesAllowed("ADMIN")
	@RequestMapping(value="/admin", method=RequestMethod.GET)
	public String checkAdmin() {
		return "scope";
	}
	
	@RolesAllowed("HACKER")
	@RequestMapping(value="/hacker", method=RequestMethod.GET)
	public String checkHacker() {
		return "scope";
	}
}
