package aisino.reportform.model.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "T_PRINTPARAM", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Sytaoda {
	private String taodaid;
	private String resultname;
	private String htmlstyle;
	private String ticketmethod;

	@Id
	@Column(name = "TAODAID",length = 30)
	public String getTaodaid() {
		return taodaid;
	}
	public void setTaodaid(String taodaid) {
		this.taodaid = taodaid;
	}
	
	@Column(name = "RESULTNAME", length = 10)
	public String getResultname() {
		return resultname;
	}
	public void setResultname(String resultname) {
		this.resultname = resultname;
	}
	
	@Column(name = "STYLE", length = 500)
	public String getHtmlstyle() {
		return htmlstyle;
	}
	public void setHtmlstyle(String htmlstyle) {
		this.htmlstyle = htmlstyle;
	}
	
	@Column(name = "TICKETMETHOD", length = 10)
	public String getTicketmethod() {
		return ticketmethod;
	}
	public void setTicketmethod(String ticketmethod) {
		this.ticketmethod = ticketmethod;
	}
}
