package cabinet.web.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
public class httpUtil {

	private static String _contextPath=null;
	
	public static String CombinedParam(String queryStrinig, String key, String val)
	{
		Map<String, Object> map;
		if(queryStrinig!=null){
		map=getUrlParams(queryStrinig.toLowerCase());
		}else{
			map=new HashMap<String,Object>();
		}
		map.put(key.toLowerCase(),val);
		return getUrlParamsByMap(map);
	}

	public static Map<String, Object> getUrlParams(String param)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		if ("".equals(param) || null == param) {
			return map;
		}
		String[] params = param.split("&");
		for (int i = 0; i < params.length; i++) {
			String[] p = params[i].split("=");
			if (p.length == 2) {
				map.put(p[0], p[1]);
			}
		}
		return map;
	}
	
	public static String getUrlParamsByMap(Map<String, Object> map)
	{
	      if (map == null) {
	          return "";
	      }
	      StringBuffer sb = new StringBuffer();
	      List<String> keys = new ArrayList<String>(map.keySet());
	     
	      for (int i = 0; i < keys.size(); i++) {
	          String key = keys.get(i);
	          String value = map.get(key).toString();
	          sb.append(key + "=" + value);
	          sb.append("&");
	      }
	      String s = sb.toString();
	      if (s.endsWith("&")) {
	          s = s.substring(0, s.lastIndexOf("&"));
	      }
	      return s;
	  }
	
	public static String getContextPath(HttpServletRequest request)
	{
		if(_contextPath==null)
		{
			_contextPath=request.getContextPath();
		}
		return _contextPath;
	}
}
