package cabinet.domain.repository;

import java.util.Date;
import java.util.List;

import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cabinet.domain.model.Tag;

public class TagRepository extends BaseRepository<Tag> {
	
	public void save(Tag model)
	{
		if (model.getTagId() == 0) //添加时的默认值
		{
			model.setDataChangeCreateTime(new Date());
		}
		model.setDataChangeLastTime(new Date());
		this.sessionFac.getCurrentSession().saveOrUpdate(model);
	}
	
	public List<Tag> getQuery(int pid)
	{
		List<Tag> rVal;
		Criteria criteria=this.sessionFac.getCurrentSession().createCriteria(Tag.class);
		criteria.add(Restrictions.eq("isDel",false));
	    criteria.add(Restrictions.eq("parentId",pid));
		criteria.addOrder(Order.asc("sortOrder"));
		rVal=criteria.list();
		return rVal;
	}
	
	public Tag getModel(int pkID)
	{
		Tag rVal=(Tag)(this.sessionFac.getCurrentSession().get(Tag.class ,pkID));
		rVal.setChilden(this.getQuery(rVal.getTagId()));
		return rVal;
	}
}
