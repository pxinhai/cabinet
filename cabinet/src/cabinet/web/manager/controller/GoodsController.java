package cabinet.web.manager.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cabinet.application.*;
import cabinet.domain.model.*;
import cabinet.domain.model.ValueObject.InputValueType;
import cabinet.infrastructure.*;
import cabinet.web.util.UnifiedOut;

@Controller
@RequestMapping("/manager/goods")
public class GoodsController extends BaseController {

	@Autowired
	private PropertyService _propertyService;
	@Autowired
	private goodsService _goodsService;

	@RequestMapping()
	public String home(Map<String, Object> map) 
	{
		int cId = CharacterUtility.ConverterToInt(this.request.getParameter("cId"));
		int pIdx=CharacterUtility.ConverterToInt(this.request.getParameter("pIdx"));
		Category category = new Category();
		category.setCategoryId(cId);
		
		map.put("pIdx",pIdx);
		map.put("viewModel",this._goodsService.getQuery(cId));
		map.put("categoryModel", category);
		return "manager/goods/home";
	}

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit(Map<String, Object> map) {
		int cId = CharacterUtility.ConverterToInt(this.request.getParameter("cId"));
		int id = CharacterUtility.ConverterToInt(this.request.getParameter("id"));
		List<CategoryProperty> categoryProperties = null;
		Goods model = null;
		if (id > 0) {
			model = this._goodsService.getModel(id);
			categoryProperties = model.getProperties();
		} else {
			model = new Goods();
			categoryProperties = this._propertyService.getQuery(cId);
		}
		map.put("goods", model);
		map.put("categoryProperties", categoryProperties);
		return "manager/goods/edit";
	}

	@ResponseBody
	@RequestMapping(value = "edit", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public String save() {

		int id = CharacterUtility.ConverterToInt(this.request.getParameter("id"));
		int cId = CharacterUtility.ConverterToInt(this.request.getParameter("cId"));
		Goods model = null;
		if (id > 0) {
			model = this._goodsService.getModel(id);
		} else {
			model = new Goods();
			model.setProperties(this._propertyService.getQuery(cId));
		}
		model.setProName(this.request.getParameter("inputProName"));
		model.setSortOrder(CharacterUtility.ConverterToInt(this.request.getParameter("inputSortOrder")));
		model.setDescription(this.request.getParameter("inputDiscription"));
		for (CategoryProperty c : model.getProperties()) {
			if (c.getValueTypeEnum() == InputValueType.MulitsTag) {
				String[] parValues = this.request.getParameterValues("input_" + c.getCagetroyPropertyId());
				if (parValues != null && parValues.length > 0) {
					c.setPropertyValue(new Goodspropertyvalue());
					c.getPropertyValue().setShortStrValue(",");
					for (String s : parValues) {
						c.setPropertyValue(c.getPropertyValue().getShortStrValue()+s+",");
					}
				}
			} else {
				c.setPropertyValue(this.request.getParameter("input_" + c.getCagetroyPropertyId()));
			}
		}

		UnifiedOut<String> rVal = new UnifiedOut<String>();
		try {
			this._goodsService.save(model);
		} catch (Exception e) {
			e.printStackTrace();
			rVal.setErrorCode(500);
		}
		return rVal.toString();
	}

	@RequestMapping("property")
	public String property(Map<String, Object> map) {
		return "manager/goods/property";
	}
	
	@ResponseBody
	@RequestMapping(value = "del", produces = "text/plain;charset=utf-8")
	public String delelte() {
		UnifiedOut<String> o = new UnifiedOut<String>();
		try
		{
		this._goodsService.delete(CharacterUtility.ConverterToInt(this.request.getParameter("id")));
		}
		catch (Exception e) {
			o.setErrorCode(500);
		}
		return o.toString();
	}
}
