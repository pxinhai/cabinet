package cabinet.web.manager.controller;

import java.io.File;
import java.util.*;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cabinet.application.UploadService;
import cabinet.domain.model.Image;
import cabinet.domain.model.ImageRelation;
import cabinet.domain.model.ValueObject.ImageRelationType;
import cabinet.infrastructure.CharacterUtility;
import cabinet.infrastructure.ResourceUtility;
import cabinet.web.util.UnifiedOut;

@Controller
@RequestMapping("/manager/image")
public class ImageController extends BaseController {
	
	@Autowired
	private UploadService uploadService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String home(Map<String, Object> map) {
		int cId = CharacterUtility.ConverterToInt(this.request.getParameter("cId"));
		map.put("viewModel",this.uploadService.getQuery(cId));
		return "manager/imageHome";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String upload(Map<String, Object> map)
	{
		int cId = CharacterUtility.ConverterToInt(this.request.getParameter("cId"));
		Calendar a=Calendar.getInstance();
		String errorMsg=null;
		String uploadPath = "resource/upload/"
		+a.get(Calendar.YEAR)+"/"+a.get(Calendar.MONTH)+1+"/"+a.get(Calendar.DAY_OF_MONTH)+"/"; // 上传文件的目录
		String tempPath = "resource/uploadtmp/"; // 临时文件目录
		String[] fileType = new String[] { ".jpg", ".gif", ".bmp", ".png", ".jpeg" };
		int sizeMax = 1;
		String serverPath = ResourceUtility.getBaseDirectory();
		if (!new File(serverPath + uploadPath).isDirectory()) {
			new File(serverPath + uploadPath).mkdirs();
		}
		if (!new File(serverPath + tempPath).isDirectory()) {
			new File(serverPath + tempPath).mkdirs();
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(sizeMax * 2 * 1024); // 最大缓存
		factory.setRepository(new File(serverPath + tempPath));
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(sizeMax * 1024 * 1024);// 文件最大上限
		List<FileItem> items = null;
		try {
			items = upload.parseRequest(this.request);

			for (FileItem item : items) {
				if (!item.isFormField()) {
					String fileName = item.getName().toLowerCase();

					if (fileName.endsWith(fileType[0]) 
							|| fileName.endsWith(fileType[1])
							|| fileName.endsWith(fileType[2]) 
							|| fileName.endsWith(fileType[3])
							|| fileName.endsWith(fileType[4]) ) {
						String uuid = UUID.randomUUID().toString().replace("-","");
						String filePath = serverPath + uploadPath + uuid
								+ fileName.substring(fileName.lastIndexOf("."));
						item.write(new File(filePath));
						Image m=new Image();
						m.setCategoryId(cId);
						m.setFileName(fileName);
						m.setDescription("");
						m.setAddress(filePath.replace(serverPath, ""));
						uploadService.save(m);
					}else
					{
						errorMsg="文件格式不正确";
						break;
					}
				}
			}
		} catch (SizeLimitExceededException ex)
		{
			errorMsg="文件大小超出"+sizeMax+"MB";
		} catch (Exception ex) {
			ex.printStackTrace();
			errorMsg="未知错误";
		}
		map.put("errorMsg",errorMsg);
		return this.home(map);
	}
	
	@RequestMapping("relationlist")
	public String relationlist(Map<String,Object> map)
	{
		int relationId = CharacterUtility.ConverterToInt(this.request.getParameter("relationId"));
		ImageRelationType type=ImageRelationType.valueOf(CharacterUtility.ConverterToInt(this.request.getParameter("type")));
		map.put("list",this.uploadService.getImages(type, relationId));
		return "manager/ImageLst";
	}
	
	@RequestMapping(method = RequestMethod.POST,value="saveRalation",produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String saveRalation()
	{
		ImageRelation model=new ImageRelation();	
		model.setRelationId(CharacterUtility.ConverterToInt(this.request.getParameter("relationId")));
		model.setRelationType(Byte.valueOf(this.request.getParameter("type")));
		model.setImageUrl(this.request.getParameter("inputImageURL"));
		model.setImageRelationId(CharacterUtility.ConverterToInt(this.request.getParameter("id")));
		UnifiedOut<String> rVal = new UnifiedOut<String>();
		try
		{
		this.uploadService.save(model);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			rVal.setErrorCode(500);
		}
		return rVal.toString();
	}
	
	@RequestMapping(method = RequestMethod.GET,value="deleteRalation",produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String deleteRalation()
	{
		UnifiedOut<String> rVal = new UnifiedOut<String>();
		try
		{
		this.uploadService.deleteImageRelation(CharacterUtility.ConverterToInt(this.request.getParameter("id")));
		}
		catch(Exception ex)
		{
			rVal.setErrorCode(500);
		}
		return rVal.toString();
	}
}
