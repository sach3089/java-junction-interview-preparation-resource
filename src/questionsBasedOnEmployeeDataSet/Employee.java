package questionsBasedOnEmployeeDataSet;

public class Employee {
	
	private int id;
	private String name;
	private String dept;
	private long salary;
	private int age;
	private String gender;
	private String city;
	private int yearOfjoining;
	
	
	public Employee(int id, String name, String dept, long salary, int age, String gender, String city, int yearOfJoining) {
		super();
		this.id = id;
		this.name = name;
		this.dept = dept;
		this.salary = salary;
		this.age = age;
		this.gender = gender;
		this.city = city;
		this.yearOfjoining = yearOfJoining;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public long getSalary() {
		return salary;
	}
	public void setSalary(long salary) {
		this.salary = salary;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public int getYearOfjoining() {
		return yearOfjoining;
	}


	public void setYearOfjoining(int yearOfjoining) {
		this.yearOfjoining = yearOfjoining;
	}


	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", dept=" + dept + ", salary=" + salary + ", age=" + age
				+ ", gender=" + gender + ", city=" + city + ", yearOfjoining=" + yearOfjoining + "]";
	}


	public static void main(String[] args) {
		
		Employee employee1 = new Employee(0, "sachin", "ECE", 23000, 23,"Male","Gaya",2017);
		Employee employee2 = new Employee(0, "sachin", "ECE", 23000, 23,"Male","Gaya", 2017);
		Employee employee3 = employee1;
		
		/*By default equals method of object class 
		compares object references, meaning it checks if both 
		references point to the same object in memory. 
		To check if two objects are considered 
		equal based on their data, you need to override the equals method in your class.*/
		System.out.println(employee1.equals(employee2)); 
		System.out.println(employee3.equals(employee1)); 
	}

}
