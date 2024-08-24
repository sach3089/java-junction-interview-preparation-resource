package questionsBasedOnStrings;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StringsStreamDriver {
	
	public static void main(String[] args) {
		
		//given a string, find it's characters count
		String input = "ilovejavajunctionverymuchsopleasesubscribe";
		String[] charsArray = input.split("");
		Map<String, List<String>> collect = Arrays.stream(charsArray).collect(Collectors.groupingBy(s -> s));
		
	}

}
