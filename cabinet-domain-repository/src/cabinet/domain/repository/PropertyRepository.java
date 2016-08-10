package cabinet.domain.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import cabinet.domain.model.*;
import cabinet.domain.model.ValueObject.InputValueType;

public class PropertyRepository extends BaseRepository<CategoryProperty>
{
	
	@Autowired 
	private TagRepository tagRepository;
	
	public List<CategoryProperty> getQuery(int cId)
	{
		List<CategoryProperty> rVal;
		Criteria criteria=this.sessionFac.getCurrentSession().createCriteria(CategoryProperty.class);
		criteria.add(Restrictions.eq("isDel",false));
		if(cId>0)
		{
		criteria.add(Restrictions.eq("categoryId", cId));
		}
		criteria.addOrder(Order.asc("sortOrder"));
		rVal=criteria.list();
		
		for(CategoryProperty c:rVal)
		{
			if(c.getValueTypeEnum()==InputValueType.MulitsTag
			 ||c.getValueTypeEnum()==InputValueType.SingleTag){
				c.setTag(this.tagRepository.getModel(c.getTagId()));
				c.setPropertyName(c.getTag().getCname());
			}
		}
		return rVal;
	}
}
