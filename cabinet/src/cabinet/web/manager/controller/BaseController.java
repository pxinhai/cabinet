package cabinet.web.manager.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

public class BaseController
{
	@Autowired
	protected HttpServletRequest request;
	
	@Autowired
	protected HttpServletResponse response;

}
