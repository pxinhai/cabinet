package cabinet.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cabinet.domain.model.CategoryProperty;
import cabinet.domain.model.ValueObject.InputValueType;
import cabinet.domain.repository.PropertyRepository;

@Service
public class PropertyDomainService {

	@Autowired
	private PropertyRepository categoryPropertyHome;
	@Autowired TagDomainService tagHome;
	
	public List<CategoryProperty> getQuery(int cId)
	{
		return this.categoryPropertyHome.getQuery(cId);
	}
}
