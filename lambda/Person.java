package ir.freeland.lambda.practice;

public class Person implements Comparable<Person>{
	private String name;
	private String fname;
	private int age;
	private Address address;
	
	public Person(String name, String fname, int age, Address address) {
		super();
		this.name = name;
		this.fname = fname;
		this.age = age;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public int compareTo(Person o) {
		return this.age - o.age;
	}
	
	@Override
	public String toString() {
		return "Person{ name= " + name + ", fname= " + fname + ", age= " + age + ", address= " + address + "}";
	}
	
}
