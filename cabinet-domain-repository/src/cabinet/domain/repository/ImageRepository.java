package cabinet.domain.repository;

import cabinet.domain.model.Image;
import cabinet.domain.model.ImageRelation;
import cabinet.domain.model.ValueObject.ImageRelationType;

import java.util.*;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class ImageRepository extends BaseRepository<Image> {
	public void save(Image model) {
		Date date = new Date();
		model.setDataChangeCreateTime(date);
		model.setDataChangeLastTime(date);
		this.sessionFac.getCurrentSession().save(model);
	}

	public List<Image> getQuery(int cId) {
		Criteria criteria = this.sessionFac.getCurrentSession().createCriteria(Image.class);
		criteria.add(Restrictions.eq("categoryId", cId));
		criteria.addOrder(Order.desc("dataChangeLastTime"));
		return criteria.list();
	}
	
	public List<ImageRelation> getImages(ImageRelationType type,int relationID)
	{
		Criteria criteria=this.sessionFac.getCurrentSession().createCriteria(ImageRelation.class);
		criteria.add(Restrictions.eq("relationType",(byte)(type.getValue())));
		criteria.add(Restrictions.eq("relationId",relationID));
		return criteria.list();
	}
	
	public void save(ImageRelation r)
	{
		if(r.getImageRelationId()>0)
		{
			ImageRelation org=(ImageRelation)(this.sessionFac.getCurrentSession().get(ImageRelation.class,r.getImageRelationId()));
			org.setSortOrder(r.getSortOrder());
			this.sessionFac.getCurrentSession().update(org);
			
		}else
		{
			this.sessionFac.getCurrentSession().save(r);
		}
		
	}
	
	public void deleteImageRelation(int pkID)
	{
		this.sessionFac.getCurrentSession().delete(String.valueOf(pkID),ImageRelation.class);
	}
}
