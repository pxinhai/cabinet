package cabinet.web.manager.controller;

import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.css.ElementCSSInlineStyle;

import cabinet.application.InfoService;
import cabinet.domain.model.Category;
import cabinet.domain.model.Info;
import cabinet.infrastructure.CharacterUtility;
import cabinet.web.util.UnifiedOut;

@Controller
@RequestMapping("/manager/info")
public class infoController extends BaseController {

	@Autowired
	private InfoService infoService;

	@RequestMapping()
	public String home(Map<String, Object> map) {
		int pIdx = CharacterUtility.ConverterToInt(this.request.getParameter("pidx"));
		if (pIdx == 0) {
			pIdx = 1;
		}
		int cid = CharacterUtility.ConverterToInt(this.request.getParameter("cID"));
		Category category = new Category();
		category.setCategoryId(cid);
		List<Info> list = this.infoService.getQuery(cid);
		map.put("viewModel", list);
		map.put("categoryModel", category);
		map.put("pIdx", pIdx);
		return "manager/infoHome";
	}

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit(Map<String, Object> map) {
		Info model = new Info();
		int id = CharacterUtility.ConverterToInt(this.request.getParameter("id"));
		if (id > 0) {
			model = this.infoService.getModel(id);
		}
		int cid = CharacterUtility.ConverterToInt(this.request.getParameter("cID"));
		map.put("cid", cid);
		map.put("info", model);
		return "manager/infoEdit";
	}

	@ResponseBody
	@RequestMapping(value = "edit", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public String save() {
		Info model =null;
		int id = CharacterUtility.ConverterToInt(this.request.getParameter("id"));
		if (id > 0) {
			model = this.infoService.getModel(id);
		}else {
			model=new Info();
		}
		model.setContent(this.request.getParameter("inputContent"));
		model.setTitle(this.request.getParameter("inputTitle"));
		model.setSortOrder(CharacterUtility.ConverterToInt(this.request.getParameter("inputSortOrder")));
		model.setCategoryId(CharacterUtility.ConverterToInt(this.request.getParameter("cID")));
		UnifiedOut<String> rVal = new UnifiedOut<String>();
		try {
			this.infoService.save(model);
		} catch (Exception e) {
			rVal.setErrorCode(500);
		}
		return rVal.toString();
	}

	@ResponseBody
	@RequestMapping(value = "del", produces = "text/plain;charset=utf-8")
	public String delelte() {
		UnifiedOut<String> o = new UnifiedOut<String>();
		try
		{
		this.infoService.delete(CharacterUtility.ConverterToInt(this.request.getParameter("id")));
		}
		catch (Exception e) {
			o.setErrorCode(500);
		}
		return o.toString();
	}
}
