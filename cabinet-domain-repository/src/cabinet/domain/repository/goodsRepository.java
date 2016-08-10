package cabinet.domain.repository;

import java.util.*;

import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cabinet.domain.model.*;

@Repository
public class goodsRepository extends BaseRepository<Goods> {

	@Autowired
	private PropertyRepository propertyRepository;

	public List<Goods> getQuery(int cId) 
	{
		Criteria c = this.sessionFac.getCurrentSession().createCriteria(Goods.class);
		c.add(Restrictions.eq("isDel",false));
		if(cId>0)
		{
			c.add(Restrictions.eq("categoryId",cId));
		}	
		c.addOrder(Order.asc("sortOrder"));
		return c.list();
	}

	public Goods getModel(int id) {
		Goods rVal = (Goods) (this.sessionFac.getCurrentSession().get(Goods.class, id));
		rVal.setProperties(this.propertyRepository.getQuery(rVal.getCategoryId()));
		for (CategoryProperty c : rVal.getProperties()) {
			c.setPropertyValue(getPropertyValueModel(id,c.getCagetroyPropertyId()));
		}
		return rVal;
	}

	public void save(Goods model) {
		Date currentDate = new Date();

		// 保存产品主详细
		model.setDataChangeLastTime(currentDate);
		boolean isAdd = false;
		if (model.getGoodsId() == 0) {
			isAdd = true;
			model.setDataChangeCreateTime(new Date());
		}
		Session session = this.sessionFac.getCurrentSession();
		session.saveOrUpdate(model);
		// 保存属性值
		for (CategoryProperty v : model.getProperties()) {
			if (isAdd) {
				v.getPropertyValue().setDataChangeCreateTime(currentDate);
				v.getPropertyValue().setGoodsId(model.getGoodsId());
				v.getPropertyValue().setCagetroyPropertyId(v.getCagetroyPropertyId());
			} else {
				Goodspropertyvalue goodspropertyvalue = getPropertyValueModel(model.getGoodsId(),
						v.getCagetroyPropertyId());
				// 根据产品ID与属性ID，获取属性值主键
				if (goodspropertyvalue != null) {
					goodspropertyvalue.setDecimalValue(v.getPropertyValue().getDecimalValue());
					goodspropertyvalue.setIsDel(false);
					goodspropertyvalue.setLongStrValue(v.getPropertyValue().getLongStrValue());
					goodspropertyvalue.setShortStrValue(v.getPropertyValue().getShortStrValue());
					v.setPropertyValue(goodspropertyvalue);
				}
			}
			v.getPropertyValue().setDataChangeLastTime(currentDate);
			session.saveOrUpdate(v.getPropertyValue());
		}
	}

	public Goodspropertyvalue getPropertyValueModel(int goodsID, int cagetroyPropertyId) {
		Criteria criteria = this.sessionFac.getCurrentSession().createCriteria(Goodspropertyvalue.class);
		criteria.add(Restrictions.eq("goodsId", goodsID));
		criteria.add(Restrictions.eq("cagetroyPropertyId", cagetroyPropertyId));
		List<Goodspropertyvalue> valueData = criteria.list();
		if (valueData.size() > 0) {
			return valueData.get(0);
		} else {
			return null;
		}
	}
	
	public void delete(int id)
	{
		Goods m = (Goods) (this.sessionFac.getCurrentSession().get(Goods.class, id));
		m.setIsDel(true);
		this.sessionFac.getCurrentSession().update(m);
	}
	
}
