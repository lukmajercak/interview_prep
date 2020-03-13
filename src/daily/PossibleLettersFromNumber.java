package daily;

import java.util.*;
import java.util.stream.Collectors;

public class PossibleLettersFromNumber {

  /**
   * This problem was asked by Yelp.
   *
   * Given a mapping of digits to letters (as in a phone number), and a digit string,
   * return all possible letters the number could represent. You can assume each valid
   * number in the mapping is a single digit.
   *
   * For example if
   *  {“2”: [“a”, “b”, “c”],
   *  3: [“d”, “e”, “f”], …}
   *
   *  then “23” should
   * return [“ad”, “ae”, “af”, “bd”, “be”, “bf”, “cd”, “ce”, “cf"].
   */
  public static void main(String[] args) {
    Map<Character, List<Character>> mapping = new HashMap<>();
    mapping.put('2', Arrays.asList('a', 'b', 'c'));
    mapping.put('3', Arrays.asList('d', 'e', 'f'));

    System.out.println(possibleLetters(mapping, "23"));
    System.out.println(possibleLetters(mapping, "232"));
  }


  static List<String> possibleLetters(
      Map<Character, List<Character>> mapping, String number) {
    List<StringBuilder> results = new ArrayList<>();

    for (int i = 0; i < number.length(); i++) {
      char c = number.charAt(i);

      List<Character> possibleChars = mapping.get(c);
      if (possibleChars == null) {
        continue;
      }

      if (results.isEmpty()) {
        for (Character possibleChar : possibleChars) {
          StringBuilder newBuilder = new StringBuilder();
          newBuilder.append(possibleChar);
          results.add(newBuilder);
        }
      } else {
        List<StringBuilder> newResults = new ArrayList<>(results.size() * possibleChars.size());
        for (StringBuilder result : results) {
          for (Character possibleChar : possibleChars) {
            StringBuilder newBuilder = new StringBuilder();
            newBuilder.append(result).append(possibleChar);
            newResults.add(newBuilder);
          }
        }
        results = newResults;
      }
    }
    return results.stream().map(StringBuilder::toString).
        collect(Collectors.toList());
  }
}
