package cabinet.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cabinet.domain.model.Info;
import cabinet.domain.service.InfoDomainService;

@Component
public class InfoService {

	@Autowired
	private InfoDomainService infoDomainService;
	
	public void delete(int id)
	{
		this.infoDomainService.delete(id);
	}
	
	public void save(Info model)
	{
		this.infoDomainService.save(model);
	}
	
	public List<Info> getQuery(int categoryid)
	{
		return this.infoDomainService.getQuery(categoryid);
	}
	
	public Info getModel(int id)
	{
		return this.infoDomainService.getModel(id);
	}
	
}
