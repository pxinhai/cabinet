package cabinet.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cabinet.domain.model.Image;
import cabinet.domain.model.ImageRelation;
import cabinet.domain.model.ValueObject.ImageRelationType;
import cabinet.domain.service.UploadDomainService;

@Component
public class UploadService {
	@Autowired
	private UploadDomainService uploadService;

	public void save(Image m) {
		this.uploadService.save(m);
	}

	public List<Image> getQuery(int cId) {
		return this.uploadService.getQuery(cId);
	}
	
	public void save(ImageRelation r)
	{
		this.uploadService.save(r);
	}
	
	public void deleteImageRelation(int pkID)
	{
		this.uploadService.deleteImageRelation(pkID);
	}

	public List<ImageRelation> getImages(ImageRelationType type, int relationID)
	{
		return this.uploadService.getImages(type, relationID);
	}
}
