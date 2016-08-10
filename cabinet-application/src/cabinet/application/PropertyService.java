package cabinet.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cabinet.domain.model.CategoryProperty;
import cabinet.domain.service.PropertyDomainService;;

@Component
public class PropertyService {

	@Autowired
	private PropertyDomainService propertyDomainServiceHome;
	
	public List<CategoryProperty> getQuery(int cId)
	{
		return this.propertyDomainServiceHome.getQuery(cId);
	}
}
