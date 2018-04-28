package aisino.reportform.service.base.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aisino.reportform.dao.base.BaseDaoI;
import aisino.reportform.model.base.Fileupload;
import aisino.reportform.service.base.FileUploadServiceI;
import aisino.reportform.service.impl.BaseServiceImpl;

@Service
public class FileUploadServiceImpl extends BaseServiceImpl<Fileupload> implements FileUploadServiceI{

	@Autowired
	private BaseDaoI<Fileupload> FileuploadDao;
	@Override
	public void saveFile(Fileupload fileupload) {
		// TODO Auto-generated method stub
		FileuploadDao.save(fileupload);
		
	}

}
