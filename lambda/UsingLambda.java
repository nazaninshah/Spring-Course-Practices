package ir.freeland.lambda.practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UsingLambda {

	public static void main(String[] args) {
		Person p1 = new Person("nazi", "shah", 17, new Address("iran", "hamedan", "daneshgah", 127));
		Person p2 = new Person("az", "shah", 15, new Address("iran", "tehran", "namjoo", 58));
		Person p3 = new Person("mar", "sam", 53, new Address("iran", "tehran", "namjoo", 58));
		Person p4 = new Person("hoj", "shah", 54, new Address("iran", "hamedan", "daneshgah", 127));
		Person p5 = new Person("mehr", "shah", 77, new Address("iran", "nahavand", "shahi", 236));
		Person p6 = new Person("maj", "sam", 45, new Address("iran", "tehran", "abasabad", 78));
		Person p7 = new Person("am", "sam", 43, new Address("iran", "tehran", "namjoo", 67));
		Person p8 = new Person("akr", "hoj", 50, new Address("iran", "tehran", "namjoo", 67));
		Person p9 = new Person("kor", "shah", 52, new Address("iran", "tehran", "hossein", 352));
		Person p10 = new Person("meh", "shah", 45, new Address("iran", "tehran", "qarb", 89));
		
		List<Person> persons = new ArrayList<>();
		Collections.addAll(persons, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10);
		
		// ******** sorting based on city name
		
		//first way
		System.out.println("sorting result - first way: ");
		Collections.sort(persons, new Comparator<Person>() {
			@Override
			public int compare(Person per1, Person per2) {
				String c1 = per1.getAddress().getCity();
				String c2 = per2.getAddress().getCity();
				return c1.compareTo(c2);
			}
		});
		persons.forEach(p -> System.out.println(p));
		System.out.println("---------------------------------------------------------------------");
		
		//second way
		System.out.println("sorting result - second way: ");
		List<Person> sortedPersons = persons.stream().
				sorted((per1, per2) -> per1.getAddress().getCity().compareTo(per2.getAddress().getCity())).
						collect(Collectors.toList());
		sortedPersons.forEach(System.out::println);
		System.out.println("---------------------------------------------------------------------");
		
		// ******** partitioning based on age
		System.out.println("partitioning result: ");
		sortedPersons.stream()
			.collect(Collectors.partitioningBy(p -> p.getAge() > 18))
			.forEach((t, p) -> {
				String ageGroup = t ? "adults" : "minors";
				System.out.println(ageGroup);
				p.forEach(System.out::println);
				System.out.println();
				});
			
		System.out.println("---------------------------------------------------------------------");
		
		// ******** groupig based on city
		System.out.println("grouping result: ");
		Map<String, List<Person>> groupedPersons = sortedPersons.stream()
				.collect(Collectors.groupingBy(p -> p.getAddress().getCity()));
		System.out.println(groupedPersons);
		System.out.println("---------------------------------------------------------------------");
		
		//******** calculating age average
		System.out.println("average result: ");
		double averageAge = persons.stream()
				.mapToDouble(p -> p.getAge())
				.average()
				.getAsDouble();
		System.out.println(averageAge);
	}
	

}
