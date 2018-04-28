package aisino.reportform.model.base.task;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_task",schema="sshe" )
public class Task implements Serializable {
		 
	private String id;
	private String name;
	private Date startTime;
	private Date endTime;
	private Date finishSTime;
	private Date finishETime;
	private String finishLoad;
	
	private String load;
	private String unit;
	private String loadType;
	private String taskType;
	private String teamId;
	private String userId;
	private String taskId;
	private String taskSort;
	private String status;
	private String content;
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator") 
	@Column(name = "id", unique = true, nullable = false, length = 64)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "FINISH_STIME")
	public Date getFinishSTime() {
		return finishSTime;
	}
	public void setFinishSTime(Date finishSTime) {
		this.finishSTime = finishSTime;
	}
	@Column(name = "FINISH_ETIME")
	public Date getFinishETime() {
		return finishETime;
	}
	public void setFinishETime(Date finishETime) {
		this.finishETime = finishETime;
	}
	@Column(name = "FINISH_LOAD", length =30)
	public String getFinishLoad() {
		return finishLoad;
	}
	public void setFinishLoad(String finishLoad) {
		this.finishLoad = finishLoad;
	}
	@Column(name = "NAME", length = 4000)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "START_TIME")
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	@Column(name = "END_TIME")
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	@Column(name = "LOAD", length = 30)
	public String getLoad() {
		return load;
	}
	public void setLoad(String load) {
		this.load = load;
	}
	@Column(name = "UNIT", length = 30)
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	@Column(name = "LOAD_TYPE", length = 10)
	public String getLoadType() {
		return loadType;
	}
	public void setLoadType(String loadType) {
		this.loadType = loadType;
	}
	@Column(name = "TASK_TYPE", length = 10)
	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	@Column(name = "TEAM_ID", length = 64)
	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	@Column(name = "USER_ID", length = 64)
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Column(name = "TASK_ID", length =64)
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	@Column(name = "TASK_SORT", length = 10)
	public String getTaskSort() {
		return taskSort;
	}
	public void setTaskSort(String taskSort) {
		this.taskSort = taskSort;
	}
	@Column(name = "STATUS", length = 10)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name = "CONTENT", length = 4000)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	
}
