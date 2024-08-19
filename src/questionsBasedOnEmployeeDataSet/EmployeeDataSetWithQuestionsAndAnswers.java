package questionsBasedOnEmployeeDataSet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class EmployeeDataSetWithQuestionsAndAnswers {
	
	
public static List<Employee> getEmployeesDataSet(){
		
		List<Employee> employees = new ArrayList<>();
		employees.add(new Employee(0, "sachin", "ECE", 23000, 23,"Male","Blore",2020));
		employees.add(new Employee(2, "minu", "CSE", 27000, 22,"Female","Hyderabad",2015));
		employees.add(new Employee(2, "anshu", "ME", 27700, 21,"Female","Chennai",2014));
		employees.add(new Employee(2, "ravi", "CIVIL", 25000, 22,"Male","Chennai",2013));
		employees.add(new Employee(2, "bineet", "MSME", 20000, 22,"Male","Noida",2013));
		employees.add(new Employee(2, "mona", "CSE", 29000, 29,"Female","Gurugram",2017));
		employees.add(new Employee(2, "sona", "ME", 29100, 29,"Female","Pune",2016));
		employees.add(new Employee(2, "rona", "CSE", 29600, 24,"Female","Gurugram",2010));
		employees.add(new Employee(2, "hina", "ECE", 29400, 24,"Female","Noida",2020));
		employees.add(new Employee(2, "mina", "MSME", 29800, 21,"Female","Blore",2020));
		
		return employees;
	}
	
	public static void  main(String[] args) {
		
		List<Employee> employeesDataSet = getEmployeesDataSet();
		
		//1. Group the employees by city
		Map<String, List<Employee>> employeeByCity = employeesDataSet.stream().collect(Collectors.groupingBy(Employee::getCity));
		employeeByCity.forEach((key,value) -> {
			System.out.println(key +" : "  + " employees : " + value);
		});
		
		//1a. Group the employees by city and return only names
		Map<String, List<String>> employeesNameByCity = employeesDataSet.stream().collect(Collectors.groupingBy(Employee::getCity,
				Collectors.mapping(Employee::getName, Collectors.toList())));
		employeesNameByCity.forEach((key,value) -> {
				System.out.println(key +" : "  + " employees : " + value);
		});
		
		//2. Group the employees by age
		Map<Integer, List<Employee>> employeeByAge = employeesDataSet.stream().collect(Collectors.groupingBy(Employee::getAge));
		employeeByAge.forEach((key,value) -> {
			System.out.println(key + " : " + value);
		});
		
		//2b Group the employees by age and return only names
		Map<Integer, List<String>> employeeNamesByAge = employeesDataSet.stream().collect(Collectors.groupingBy(Employee::getAge,
				Collectors.mapping(Employee::getName, Collectors.toList())));
		employeeNamesByAge.forEach((key,value) -> {
			System.out.println(key + " : " + value);
		});
		
		//3.find the count of the Male and Female employees present in the organization
		Map<String, Long> countOfMaleAndFemaleEmp = employeesDataSet.stream().collect(Collectors.groupingBy(Employee::getGender,Collectors.counting()));
		countOfMaleAndFemaleEmp.forEach((key,value) -> {
			System.out.println(key + " : " + value);
	    });
		
		//4.print the names of all departments in the organization
		employeesDataSet.stream().map(Employee::getDept).distinct().forEach(System.out::println);
		
		//5.print employee details whose age is greater than 23
		employeesDataSet.stream().filter(emp -> emp.getAge() > 23).map(Employee::getName).forEach(System.out::println);
		
		//6. find the oldest employee in organization
		Optional<String> oldestEmployee = employeesDataSet.stream().max((emp1,emp2) -> (int) (emp1.getAge() - emp2.getAge())).map(Employee::getName);
		if(oldestEmployee.isPresent()) {
			System.out.println(oldestEmployee.get());
		}
		
		//7. print average of male and female employee
		Map<String, Double> avgAgeOfMaleAndFem = employeesDataSet.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge)));
		avgAgeOfMaleAndFem.forEach((key,value) -> {
			System.out.println(key + " : " + value);
		});
		
		//7. print number of employee in each department
		Map<String, Long> numberOfEmpDepartmentwise = employeesDataSet.stream().collect(Collectors.groupingBy(Employee::getDept,Collectors.counting()));
		numberOfEmpDepartmentwise.forEach((key,value) -> {
			System.out.println(key + " : " + value);
		});
		
		//8. find youngest female employee
		Optional<Employee> youngestFemaleEmp = employeesDataSet.stream().filter(emp -> emp.getGender().equalsIgnoreCase("female")).min(Comparator.comparingInt(Employee::getAge));
		if(youngestFemaleEmp.isPresent()) {
			System.out.println(youngestFemaleEmp.get());
		}
		
		//9. find the employees whose age is greater than 23 and less than  23
		Map<Boolean, List<String>> empPartionByAge = employeesDataSet.stream().collect(Collectors.partitioningBy(emp -> emp.getAge() > 23,Collectors.mapping(Employee::getName, Collectors.toList())));
		empPartionByAge.forEach((key,value) -> {
			if(Boolean.TRUE.equals(key)) {
				System.out.println(value);
			}else {
				System.out.println(value);
			}
		});
		
		//10. find the department name which has the highest number of employees
		Entry<String, Long> maxEmpInADepartment = employeesDataSet.stream().collect(Collectors.groupingBy(Employee::getDept, Collectors.counting()))
		       .entrySet().stream().max(Map.Entry.comparingByValue()).get();
		System.out.println(maxEmpInADepartment.getKey() + " : " + maxEmpInADepartment.getValue());
		
		//11. find if there any employees from ME department
		Optional<Employee> meEmployee = employeesDataSet.stream().filter(emp -> emp.getDept().equalsIgnoreCase("ME")).findAny();
		if(meEmployee.isPresent()) {
			System.out.println(meEmployee.get().getName());
		}
		
		//12.find the department names where the number of employees in the department is over 3
		employeesDataSet.stream().collect(Collectors.groupingBy(Employee::getDept, Collectors.counting()))
				.entrySet().stream().filter(entry -> entry.getValue() > 2).forEach(System.out::println);
		
		//13. find distinct department names that employees work for
		employeesDataSet.stream().map(Employee::getDept).distinct().forEach(System.out::println);
		
		//14.find all employees who lives in 'Blore' city, sort them by their name and print the names of employees
		employeesDataSet.stream().filter(emp -> emp.getCity().equalsIgnoreCase("Blore"))
		      .sorted(Comparator.comparing(Employee::getName)).map(Employee::getName).forEach(System.out::println);
		
		//15.number of employees in the organization
		long count = employeesDataSet.stream().count();
		System.out.println("total employee in the organization : " + count);
		
		//16. find employee count in every department
		employeesDataSet.stream().collect(Collectors.groupingBy(Employee::getDept, Collectors.counting()))
		    .entrySet().stream().forEach(System.out::println);
		
		//17.find the department which has the highest number of employee
		Optional<Entry<String, Long>> highestNoOfEmployee = employeesDataSet.stream().collect(Collectors.groupingBy(Employee::getDept,Collectors.counting()))
		     .entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).findFirst();
		if(highestNoOfEmployee.isPresent()) {
			System.out.println(highestNoOfEmployee.get().getKey()+" : " + highestNoOfEmployee.get().getValue());
		}
		
		//18. sorting a stream by age and name fields
		employeesDataSet.stream().sorted(Comparator.comparing(Employee::getName).thenComparingInt(Employee::getAge))
		          .forEach(System.out::println);
		
		//19.highest experienced employee in the organization
		Optional<Employee> mostExperiencedEmployee = employeesDataSet.stream().sorted(Comparator.comparingInt(Employee::getYearOfjoining)).findFirst();
		if(mostExperiencedEmployee.isPresent()) {
			System.out.println(mostExperiencedEmployee.get().getName());
		}
		
		//20. find average and total salary of the organization
		long totalSalary = employeesDataSet.stream().collect(Collectors.summarizingLong(Employee::getSalary)).getSum();
		System.out.println("total salary : " + totalSalary);
		double averageSalary = employeesDataSet.stream().collect(Collectors.summarizingLong(Employee::getSalary)).getAverage();
		System.out.println("total salary : " + averageSalary);
		
		//21. find average salary of each department
		Map<String, LongSummaryStatistics> avgSalaryDeptWise = employeesDataSet.stream().collect(Collectors.groupingBy(Employee::getDept, Collectors.summarizingLong(Employee::getSalary)));
		avgSalaryDeptWise.forEach((key,value) -> {
			System.out.println(key + " : " + value.getAverage());
		});
		
		//22.find highest salary in the organization
		//approach-1
		Optional<Employee> highestPaidEmployee = employeesDataSet.stream().max(Comparator.comparingLong(Employee::getSalary));
		if(highestPaidEmployee.isPresent()) {
			System.out.println(highestPaidEmployee.get().getName() + " : " + highestPaidEmployee.get().getSalary());
		}
		
		//22.find highest salary in the organization
		//approach-2 - more generic
		Optional<Employee> highestPaidEmployee2 = employeesDataSet.stream().sorted(Comparator.comparingLong(Employee::getSalary).reversed()).findFirst();
		if(highestPaidEmployee2.isPresent()) {
			System.out.println(highestPaidEmployee.get().getName() + " : " + highestPaidEmployee.get().getSalary());
		}
		
		//23.find the second highest salary in the organization
		Optional<Employee> secondHighestPaidEmployee = employeesDataSet.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).skip(1).findFirst();
		if(secondHighestPaidEmployee.isPresent()) {
			System.out.println(secondHighestPaidEmployee.get().getName() + " : " + secondHighestPaidEmployee.get().getSalary());
		}
		
		//23.find the second highest salary in the organization
		int n  = 5; //this is variable 
		Optional<Employee> nthHighestPaidEmployee = employeesDataSet.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).skip(n-1).findFirst();
		if(secondHighestPaidEmployee.isPresent()) {
			System.out.println(nthHighestPaidEmployee.get().getName() + " : " + nthHighestPaidEmployee.get().getSalary());
		}
		
		//24. find the lowest paid employee in organization
		Optional<Employee> lowestPaidEmployee = employeesDataSet.stream().sorted(Comparator.comparingLong(Employee::getSalary)).findFirst();
		if(lowestPaidEmployee.isPresent()) {
			System.out.println(lowestPaidEmployee.get().getName() + " : " + lowestPaidEmployee.get().getSalary());
		}
		
		//25. sort the employee salaries in ascending order
		employeesDataSet.stream().sorted(Comparator.comparingLong(Employee::getSalary).reversed()).map(Employee::getSalary)
		        .forEach(System.out::println);
		
		//26 . find highest salary based on department wise
		Map<String, Optional<Employee>> highestPaidEmployeesDeptWise = employeesDataSet.stream().collect(Collectors.groupingBy(Employee::getDept,Collectors.collectingAndThen(Collectors.toList(), 
				     empList ->  empList.stream().max(Comparator.comparingLong(Employee::getSalary)))));
		highestPaidEmployeesDeptWise.forEach((key,value) -> {
			if(value.isPresent()) {
				System.out.println(value.get().getDept() +" --> " + value.get().getName() + " : " + value.get().getSalary());
			}
		});
		
		//27. find the second highest salary based on department wise
		Map<String, Optional<Employee>> secondHighestPaidEmployeesDeptWise = employeesDataSet.stream().collect(Collectors.groupingBy(Employee::getDept,Collectors.collectingAndThen(Collectors.toList(), 
			     empList ->  empList.stream().sorted(Comparator.comparingLong(Employee::getSalary).reversed()).skip(1).findFirst())));
		secondHighestPaidEmployeesDeptWise.forEach((key,value) -> {
		     if(value.isPresent()) {
			    System.out.println(value.get().getDept() +" --> " + value.get().getName() + " : " + value.get().getSalary());
		     }
	    });
		
		//28. sort the employees salary in each department in ascending order
		 Map<String, List<Employee>> employeesBySortedSalaryAscDeptWise = employeesDataSet.stream().collect(Collectors.groupingBy(Employee::getDept, Collectors.collectingAndThen(Collectors.toList(), 
				   empList -> empList.stream().sorted(Comparator.comparingLong(Employee::getSalary)).collect(Collectors.toList()))));
		 employeesBySortedSalaryAscDeptWise.forEach((key, value) -> {
			System.out.println(key + " : " + value);
		});
		 
		//28. sort the employees salary in each department in ascending order
		Map<String, List<Employee>> employeesBySortedSalaryDescDeptWise = employeesDataSet.stream().collect(Collectors.groupingBy(Employee::getDept, Collectors.collectingAndThen(Collectors.toList(), 
				   empList -> empList.stream().sorted(Comparator.comparingLong(Employee::getSalary).reversed()).collect(Collectors.toList()))));
		employeesBySortedSalaryDescDeptWise.forEach((key, value) -> {
			System.out.println(key + " : " + value);
		});
	}

}
