package cabinet.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cabinet.domain.model.*;
import cabinet.domain.service.TagDomainService;

@Component
public class TagService {
	
	@Autowired
	private TagDomainService tagDomain;
	
	public  void save(Tag model)
	{
	 this.tagDomain.save(model);
	}
	
	public  void delete(int id)
	{
		 this.tagDomain.delete(id);
	}
	
	public  List<Tag> getQuery(int pid)
	{
		return this.tagDomain.getQuery(pid);
	}
	
	public Tag getModel(int id)
	{
		 return this.tagDomain.getModel(id);
	}
}
