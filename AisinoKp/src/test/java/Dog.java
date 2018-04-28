


public class Dog extends Animal{
	private String name="1";  
    private Integer weight=3;  
    private static Integer id=4;
    static{
    	System.out.println("静态初始。。。id"+id);
    	id=1;
    	System.out.println("静态初始。。。id="+id);
    }
    {
    	this.name="name";
    	this.weight=2;
    	System.out.println("非静态初始。。。");
    	id=2;
    	System.out.println("非静态初始。。。id="+id);
    }
    public Dog(){};
    public Dog(String name, Integer weight) {  
        this.setName(name);  
        this.weight = weight;  
    }  
 
    public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public void setName(String name) {  
        this.name = name;  
    }  
    public String getName() {  
        return name;  
    }  
}
