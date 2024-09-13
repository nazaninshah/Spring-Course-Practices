package ir.freeland.spring;


import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import ir.freeland.spring.dto.Employee;
import ir.freeland.spring.dto.EmployeeList;


/**
 * Application that shows how to use Lists with RestTemplate.
 */
public class EmployeeClient {
	
	//restTemplate is a static field of type RestTemplate. This will be the core object used to make HTTP requests like GET, POST, etc.
	private static RestTemplate restTemplate;
	
    public static void main(String[] args) {
    
    	//creating an instance of RestTemplate, which is used to send HTTP requests to a REST service.
    	restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
    	
    	//Interceptors are particularly helpful for:Logging requests and responses
    	List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
    	interceptors.add(new LoggingRequestInterceptor());
    	restTemplate.setInterceptors(interceptors);
    	
        EmployeeClient employeeClient = new EmployeeClient();

        System.out.println("Calling GET for entity using arrays");
        employeeClient.getForEntityEmployeesAsArray();

        System.out.println("Calling GET using ParameterizedTypeReference");
        employeeClient.getAllEmployeesUsingParameterizedTypeReference();

        System.out.println("Calling GET using wrapper class");
        employeeClient.getAllEmployeesUsingWrapperClass();

        System.out.println("Calling POST using normal lists");
        employeeClient.createEmployeesUsingLists();

        System.out.println("Calling POST using wrapper class");
        employeeClient.createEmployeesUsingWrapperClass();
    }

    public EmployeeClient() {

    }

    //This method sends an HTTP GET request to the provided URL (http://127.0.0.1:8080/employees/)
    //and expects the response to be mapped into an array of Employee objects.
    public Employee[] getForEntityEmployeesAsArray() {
    	//The response from getForEntity is wrapped in a ResponseEntity. 
    	//This class provides access to both the response body and the status code (like 200 OK) of the HTTP response.
        ResponseEntity<Employee[]> response =
                restTemplate.getForEntity(
                        "http://127.0.0.1:8080/employees/",
                        Employee[].class);

        //This method extracts the actual array of Employee objects from the ResponseEntity.
        Employee[] employees = response.getBody();

        //This line is a sanity check to ensure that the employees array is not null.
        //If for some reason the API did not return a valid list of employees, 
        //this assertion would throw an AssertionError, stopping the program at this point.
        assert employees != null;
        
        //Converts the Employee[] array into a List<Employee>.
        //will loop through each Employee in the list and print out its toString() representation to the console.
        asList(employees).forEach(System.out::println);

        return employees;

    }


    //this approach is used when dealing with generic types, like List<Employee>, 
    //because Java has limitations with type erasure when dealing with generics (the actual type List<Employee> would be erased at runtime) 
    // is different from the previous method, getForEntityEmployeesAsArray(), primarily in the way the response type is handled.
    public List<Employee> getAllEmployeesUsingParameterizedTypeReference() {
        ResponseEntity<List<Employee>> response =
        		//flexible, for any HTTP method,not just get
                restTemplate.exchange(
                        "http://localhost:8080/employees/",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Employee>>() {
                        });

        List<Employee> employees = response.getBody();

        assert employees != null;
        employees.forEach(System.out::println);

        return employees;
    }

    
    public List<Employee> getAllEmployeesUsingWrapperClass() {
        EmployeeList response =
                restTemplate.getForObject(
                        "http://127.0.0.1:8080/employees/v2",
                        EmployeeList.class);

        List<Employee> employees = response.getEmployees();

        employees.forEach(System.out::println);

        return employees;
    }

    public void createEmployeesUsingLists() {
        List<Employee> newEmployees = new ArrayList<>();
        newEmployees.add(new Employee(3, "Intern"));
        newEmployees.add(new Employee(4, "CEO"));

        restTemplate.postForObject(
                "http://localhost:8080/employees/",
                newEmployees,
                ResponseEntity.class);
    }

    public void createEmployeesUsingWrapperClass() {
        List<Employee> newEmployees = new ArrayList<>();
        newEmployees.add(new Employee(3, "Intern"));
        newEmployees.add(new Employee(4, "CEO"));

        //This method is used to send an HTTP POST request. 
        restTemplate.postForObject(
                "http://localhost:8080/employees/v2",
                //is the body of the request, which in this case is the newEmployees list.
                //Spring will automatically serialize this list into JSON and send it in the HTTP request body.
                new EmployeeList(newEmployees),
                //is the expected response type (ResponseEntity.class). However, in this case,
                //since you are not storing the response, it doesn't really impact the flow. 
                //The ResponseEntity represents the entire HTTP response including status, headers, and the body.
                ResponseEntity.class);
    }
}
