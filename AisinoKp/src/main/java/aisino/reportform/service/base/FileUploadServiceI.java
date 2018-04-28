package aisino.reportform.service.base;


import aisino.reportform.model.base.Fileupload;
import aisino.reportform.service.BaseServiceI;

public interface FileUploadServiceI  extends BaseServiceI<Fileupload>{

	public void saveFile(Fileupload fileupload);
}
