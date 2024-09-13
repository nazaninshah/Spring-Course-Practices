package persistence.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import persistence.model.Address;
import persistence.model.Bookstore;

//JQL & native
@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>{
	
	List<Address> findByBookstore(Bookstore bookstore);
	
	@Query("SELECT a from Address a where a.counrty = ?1")
	List<Address> findAllByCountry(String country);
	
	@Query("SELECT a from Address a where a.city = ?1")
	List<Address> findAllByCity(String city);
	
	@Query("SELECT a from Address a where a.street = ?1")
	List<Address> findAllByStreet(String street);
	
	@Query("SELECT a FROM Address a WHERE a.country = :country and a.city = :city")
	List<Address> findAddressByCountryAndCity(@Param("country") String country, @Param("city") String city);
	
	@Modifying
    @Query("update Address a set a.city = :city where a.id = :id")
    int updateAddressSetCityForId(@Param("city") String city, @Param("id") Integer id);
	
	@Modifying
	@Query(value = "INSERT INTO ISC_address (country, city, street, department_Number, bookstore) VALUES (:country, :city, :street, :depNumber, :bookstore)", nativeQuery = true)
    void insertUser(@Param("country") String country, @Param("city") String city, @Param("street") String street, @Param("depNumber") Integer depNumber, @Param("bookstore") Bookstore bookstore);
}
