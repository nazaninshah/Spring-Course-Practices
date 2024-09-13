package persistence.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ISC_address")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name = "country", nullable = false, unique = false)
	private String counrty;
	
	@Column(name = "city", nullable = false, unique = false)
	private String city;
	
	@Column(name = "street", nullable = false, unique = false)
	private String street;
	
	@Column(name = "department_Number", nullable = false, unique = false)
	private int depNumber;
	
	@OneToOne(mappedBy = "address")
	private Bookstore bookstore;

	public Address() {
		super();
	}

	public Address(String counrty, String city, String street, int depNumber, Bookstore bookstore) {
		super();
		this.counrty = counrty;
		this.city = city;
		this.street = street;
		this.depNumber = depNumber;
		this.bookstore = bookstore;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCounrty() {
		return counrty;
	}

	public void setCounrty(String counrty) {
		this.counrty = counrty;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getDepNumber() {
		return depNumber;
	}

	public void setDepNumber(int depNumber) {
		this.depNumber = depNumber;
	}

	public Bookstore getBookstore() {
		return bookstore;
	}

	public void setBookstore(Bookstore bookstore) {
		this.bookstore = bookstore;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", counrty=" + counrty + ", city=" + city + ", street=" + street + ", depNumber="
				+ depNumber + ", bookstore=" + bookstore + "]";
	}

}
