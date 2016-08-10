package cabinet.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cabinet.domain.repository.goodsRepository;
import cabinet.domain.model.Goods;

@Service
public class GoodsDomainService {
	
	@Autowired
	private goodsRepository goodsHome;
	
	public List<Goods> getQuery(int cId)
	{
		return this.goodsHome.getQuery(cId);		
	}
	
	public Goods getModel(int id)
	{
		return this.goodsHome.getModel(id);
	}
	
	public void save(Goods model)
	{
		this.goodsHome.save(model);
	}
	
	public void delete(int id)
	{
		this.goodsHome.delete(id);
	}
	
}