package cabinet.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cabinet.domain.model.*;
import cabinet.domain.repository.*;

@Service
public class TagDomainService {

	@Autowired
	private TagRepository tagHome;
	
	public void save(Tag model)
	{
	 this.tagHome.save(model);
	}
	
	public void delete(int id)
	{
		Tag model=this.tagHome.getModel(id);
		model.setIsDel(true);
		this.tagHome.save(model);
	}
	
	public List<Tag> getQuery(int pid)
	{
		return this.tagHome.getQuery(pid);
	}
	
	public Tag getModel(int id)
	{
		return this.tagHome.getModel(id);
	}
}
