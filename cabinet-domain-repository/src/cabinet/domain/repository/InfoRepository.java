package cabinet.domain.repository;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import cabinet.domain.model.Info;

public class InfoRepository extends BaseRepository<Info> 
{
	public void save(Info model)
	{
		if (model.getArticleId() == 0) //添加时的默认值
		{
			model.setDataChangeCreateTime(new Date());
		}
		model.setDataChangeLastTime(new Date());
		this.sessionFac.getCurrentSession().saveOrUpdate(model);
	}
	
	public List<Info> getQuery(int categoryid)
	{
		Query query=this.sessionFac.getCurrentSession().createQuery
		("select new Info(articleId, title,dataChangeCreateTime, dataChangeLastTime, categoryId,SortOrder)"
				+ " from Info"
				+ " where categoryId=:categoryId"
				+ " order by SortOrder");
		query.setParameter("categoryId", categoryid);		
		List<Info> rVal=query.list();
		return rVal;
	}
	
	public Info getModel(int id)
	{
		return (Info)(this.sessionFac.getCurrentSession().get(Info.class ,id));
	}
	
	public void Delete(int id)
	{
		Info m=this.getModel(id);
		this.sessionFac.getCurrentSession().delete(m);
	}
}
