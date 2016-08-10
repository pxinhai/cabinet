package cabinet.web.manager.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cabinet.infrastructure.CharacterUtility;
import cabinet.application.*;
import cabinet.domain.model.CategoryProperty;
@Controller
@RequestMapping("/manager/property")
public class PropertyController extends BaseController {
	
	@Autowired
	private PropertyService propertyServiceHome;
	
	@RequestMapping()
	public String home(Map<String, Object> map) 
	{
		int cid=CharacterUtility.ConverterToInt(this.request.getParameter("cid"));
		int pIdx=CharacterUtility.ConverterToInt(this.request.getParameter("pidx"));		
		if(pIdx==0){
			pIdx=1;
		}
		List<Integer> cidLst=new ArrayList<>();
		if(cid>0)
		{
			cidLst.add(cid);
		}
		List<CategoryProperty> model=this.propertyServiceHome.getQuery(cid);
		map.put("model", model);
		map.put("pIdx",pIdx);
		return "manager/propertyHome";
	}

}
