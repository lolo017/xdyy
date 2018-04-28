package aisino.reportform.util.base.excel;

public class Book {

	private String id;
	private String bookName;
	private String zuozhe;
	private Double jiage;
	private String isbn;
	private String chbanshe;
	private String fengmian;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getZuozhe() {
		return zuozhe;
	}

	public void setZuozhe(String zuozhe) {
		this.zuozhe = zuozhe;
	}

	public Double getJiage() {
		return jiage;
	}

	public void setJiage(Double jiage) {
		this.jiage = jiage;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getChbanshe() {
		return chbanshe;
	}

	public void setChbanshe(String chbanshe) {
		this.chbanshe = chbanshe;
	}

	public String getFengmian() {
		return fengmian;
	}

	public void setFengmian(String fengmian) {
		this.fengmian = fengmian;
	}

	public Book(String id, String bookName, String zuozhe, Double jiage,
			String isbn, String chbanshe, String fengmian) {
		// TODO Auto-generated constructor stub
		this.id=id;
		this.bookName=bookName;
		this.zuozhe=zuozhe;
		this.jiage=jiage;
		this.isbn=isbn;
		this.chbanshe=chbanshe;
		this.fengmian=fengmian;
	}
}
