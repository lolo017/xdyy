package aisino.reportform.model.base;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.StringUtils;

/**
 * Fileupload entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "FILEUPLOAD", schema = "")
public class Fileupload implements java.io.Serializable {

	// Fields

	private String id;// 编号
	private String name;// 文件原名
	private String alias;// 文件完整路径（上传后的路径）
	private String filepath;// 文件在服务器的路径
	private Date uploadtime = new Date();// 上传时间
	private String uploadauthor;// 上传人
	private String filetype;// 是否读取导入了数据

	// Constructors

	/** default constructor */
	public Fileupload() {
	}

	/** minimal constructor */
	public Fileupload(String id) {
		this.id = id;
	}

	/** full constructor */
	public Fileupload(String id, String name, String alias, String filepath,
			Date uploadtime, String uploadauthor, String filetype) {
		this.id = id;
		this.name = name;
		this.alias = alias;
		this.filepath = filepath;
		this.uploadtime = uploadtime;
		this.uploadauthor = uploadauthor;
		this.filetype = filetype;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 36)
	public String getId() {
		if (!StringUtils.isBlank(this.id)) {
			return this.id;
		}
		return UUID.randomUUID().toString();
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "NAME", length = 36)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "ALIAS", length = 200)
	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	@Column(name = "FILEPATH", length = 200)
	public String getFilepath() {
		return this.filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}


//	public Date getUploadtime() {
//		return this.uploadtime;
//	}
//
//	public void setUploadtime(Date uploadtime) {
//		this.uploadtime = uploadtime;
//	}

	@Temporal(TemporalType.DATE)
	@Column(name = "UPLOADTIME", length = 7)
	public Date getUploadtime() {
		return uploadtime;
	}

	public void setUploadtime(Date uploadtime) {
		this.uploadtime = uploadtime;
	}

	
	@Column(name = "UPLOADAUTHOR", length = 36)
	public String getUploadauthor() {
		return this.uploadauthor;
	}

	
	public void setUploadauthor(String uploadauthor) {
		this.uploadauthor = uploadauthor;
	}

	@Column(name = "FILETYPE", length = 36)
	public String getFiletype() {
		return this.filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

}