package cabinet.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cabinet.domain.model.Image;
import cabinet.domain.model.ImageRelation;
import cabinet.domain.model.ValueObject.ImageRelationType;
import cabinet.domain.repository.ImageRepository;

@Service
public class UploadDomainService {

	@Autowired
	private ImageRepository imageRepository;

	public void save(Image m) {
		this.imageRepository.save(m);
	}

	public List<Image> getQuery(int cId) {
		return this.imageRepository.getQuery(cId);
	}
	
	public void save(ImageRelation r)
	{
		this.imageRepository.save(r);
	}
	
	public void deleteImageRelation(int pkID)
	{
		this.imageRepository.deleteImageRelation(pkID);
	}

	public List<ImageRelation> getImages(ImageRelationType type, int relationID)
	{
		return this.imageRepository.getImages(type, relationID);
	}
}
