package questionsBasedOnStrings;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StringsStreamDriver {
	
	public static void main(String[] args) {
		
		String input = "i-love-java-junction-very-much-so-please-subscribe";
		
		//1. given a string, find it's characters count
		Map<String, Long> charactersMap;
		String[] charsArray = input.split("");
		charactersMap = Arrays.stream(charsArray).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		charactersMap.forEach((key , value) -> {
			System.out.println("count of character " + key + " is " + value);
		});
		
		//2a. find all duplicates characters in the above given string
		charactersMap = Arrays.stream(charsArray).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		charactersMap.forEach((key , value) -> {
			if(value > 1) {
				System.out.println("count of character " + key + " is " + value);
			}
		});
		
		//another approach
		//2b. find all the duplicates character in the above given string
		Arrays.stream(charsArray).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
		                        .entrySet().stream().filter(entry -> entry.getValue() > 1).forEach(System.out::println);
		
	}

}
