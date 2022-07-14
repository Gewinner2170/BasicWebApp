package de.tum.in.ase.eist;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QueryProcessor {
    //comment to allow a commit

    public String process(String query) {
		query = query.toLowerCase();
        if (query.contains("shakespeare")) {
            return "William Shakespeare (26 April 1564 - 23 April 1616) was an " +
                    "English poet, playwright, and actor, widely regarded as the greatest " +
                    "writer in the English language and the world's pre-eminent dramatist.";
        } else if (query.contains("name")) {
            return "MyTeam";

        } else if (query.contains("%20what%20is%20")) {
            //question %20what%20is%203%20plus%205
            // TODO extend the programm here
            String[] numbersToAdd = query.split("what%20is%20")[1].split("%20plus%20");
            List<Integer> numbers = Arrays.stream(numbersToAdd).map(x -> Integer.parseInt(x)).collect(Collectors.toList());
            int output = 0;
            for (int curInt : numbers) {
                output += curInt;
            }
            return "" + output;
        } else if (query.contains("%20which%20of%20the%20following%20numbers%20is%20the%20largest:")) {
            //question: %20which%20of%20the%20following%20numbers%20is%20the%20largest:%20146,%2049,%20648,%2074
            String[] numbersToCompare = query.split("20which%20of%20the%20following%20numbers%20is%20the%20largest:%20")[1].split(",%20");
            List<Integer> numbers = Arrays.stream(numbersToCompare).map(x -> Integer.parseInt(x)).collect(Collectors.toList());
            int largest = numbers.get(0);
            for (int curInt : numbers) {
                if (curInt > largest) {
                    largest = curInt;
                }
            }
            return "" + largest;
        } else if (query.contains("%20which%20of%20the%20following%20numbers%20is%20both%20a%20square%20and%20a%20cube:%20")) {
            //question: %20which%20of%20the%20following%20numbers%20is%20both%20a%20square%20and%20a%20cube:%20113,%20289
            String[] numbersToCompare = query.split("%20which%20of%20the%20following%20numbers%20is%20both%20a%20square%20and%20a%20cube:%20")[1].split(",%20");
            List<Integer> numbers = Arrays.stream(numbersToCompare).map(x -> Integer.parseInt(x)).collect(Collectors.toList());
            for (int curInt : numbers) {
                if (isSquare(curInt) && isCube(curInt)) {
                    return "" + curInt;
                }
            }
        }
        return "";
    }

    private boolean isCube(int curInt) {
        double root = Math.cbrt(curInt);
        if (root == (int) root) {
            return true;
        }
        return false;
    }

    private boolean isSquare(int curInt) {
        double root = Math.sqrt(curInt);
        if (root == (int) root) {
            return true;
        }
        return false;
    }
}
