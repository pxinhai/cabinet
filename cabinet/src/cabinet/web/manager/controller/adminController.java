package cabinet.web.manager.controller;

import javax.servlet.http.Cookie;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cabinet.infrastructure.SecurityUtility;
import cabinet.web.util.Keys;
import cabinet.web.util.UnifiedOut;

@Controller
@RequestMapping("/manager")
public class adminController extends BaseController {
	
	@RequestMapping(value="login",method = RequestMethod.GET)
	public String index() {
		return "manager/login";
	}
	
	@ResponseBody
	@RequestMapping(value = "login", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public String post() 
	{
    String userName=this.request.getParameter("inputUsername");
    String pwd=this.request.getParameter("inputPassword");
    String rememberMe=this.request.getParameter("ckbRememberMe");
    UnifiedOut<String> rVal = new UnifiedOut<String>();
    if("pxinhai".equals(userName)&&"000000".equals(pwd))
    {
    	String safetyKey=SecurityUtility.getMd5(userName+Keys.Cookie.loginSecretKey);
    	Cookie userNameCookie=new  Cookie(Keys.Cookie.userName, userName);
    	Cookie safetyKeyCookie=new  Cookie(Keys.Cookie.safetyKey, safetyKey);
    	if("1".equals(rememberMe))
    	{	
    	int expire=60*60*24*30;
    	userNameCookie.setMaxAge(expire);
    	safetyKeyCookie.setMaxAge(expire);
    	}
    	this.response.addCookie(userNameCookie);
    	this.response.addCookie(safetyKeyCookie);
    }else{
    	rVal.setErrorCode(1);
    	rVal.setMessage("用户名或密码错误");
    }
	return rVal.toString();
	}
	
	@RequestMapping("header")
	public String header()
	{
		return "manager/header";
	}
	
	@RequestMapping("footer")
	public String bottom()
	{
		return "manager/footer";
	}
	
	@RequestMapping("navigation")
	public String navigation()
	{
		return "manager/navigation";
	}
	
	@RequestMapping(value="pagination")
	public String pagination()
	{
		return "manager/pagination";	
	}
	
}
