package cabinet.web.manager.controller;

import java.util.*;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cabinet.application.TagService;
import cabinet.domain.model.*;
import cabinet.infrastructure.CharacterUtility;
import cabinet.web.util.*;

@Controller
@RequestMapping("/manager/tag")
public class tagController extends BaseController {

	@Autowired
	private TagService tagService;
	@RequestMapping()
	public String home(Map<String, Object> map) 
	{
		int pid=CharacterUtility.ConverterToInt(this.request.getParameter("pid"));
		int pIdx=CharacterUtility.ConverterToInt(this.request.getParameter("pidx"));		
		if(pIdx==0){
			pIdx=1;
		}
		List<Tag> tagLst = this.tagService.getQuery(pid);
		Tag pModel=new Tag();
		if(pid>0)
		{
			pModel=this.tagService.getModel(pid);		
		}
		map.put("pModel", pModel);
		map.put("tagLst", tagLst);
		map.put("pIdx",pIdx);
		return "manager/tagHome";
	}

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit(Map<String, Tag> map) {
		Tag m = new Tag();
		Tag pModel = new Tag();
		int id =CharacterUtility.ConverterToInt(this.request.getParameter("id"));
		int pid=CharacterUtility.ConverterToInt(this.request.getParameter("pid"));
		if(id>0)
		{
			m = this.tagService.getModel(id);
		}
		if(pid>0)
		{
			pModel=this.tagService.getModel(pid);
		}
		map.put("pModel", pModel);
		map.put("Model", m);
		return "manager/tagEdit";
	}

	@ResponseBody
	@RequestMapping(value = "edit", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public String save() 
	{
		int tagID =CharacterUtility.ConverterToInt(this.request.getParameter("id"));
		Tag g = null;
		if (tagID > 0) {
			g = this.tagService.getModel(tagID);
		} else {
			g = new Tag();
			g.setParentId(CharacterUtility.ConverterToInt(this.request.getParameter("pid")));
		}
		g.setCname(this.request.getParameter("inputCName"));
		g.setEname(this.request.getParameter("inputEName"));
		g.setSortOrder(CharacterUtility.ConverterToInt(this.request.getParameter("inputSortOrder")));
		UnifiedOut<String> o = new UnifiedOut<String>();
		try {
			this.tagService.save(g);			
		} catch (Exception e) {
			o.setErrorCode(500);
		}
		return o.toString();
	}

	@ResponseBody
	@RequestMapping(value = "del", produces = "text/plain;charset=utf-8")
	public String delelte() 
	{
		this.tagService.delete(CharacterUtility.ConverterToInt( this.request.getParameter("id")));
		UnifiedOut<String> o = new UnifiedOut<String>();
		return o.toString();
	}
}
