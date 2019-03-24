package leetcode.google;

public class ValidNumber {

  /**
   * Validate if a given string can be interpreted as a decimal number.
   *
   * Note: It is intended for the problem statement to be ambiguous. You should gather all requirements
   * up front before implementing one. However, here is a list of characters that can be in a valid decimal number:
   *
   * Numbers 0-9
   * Exponent - "e"
   * Positive/negative sign - "+"/"-"
   * Decimal point - "."
   * Of course, the context of these characters also matters in the input.
   */
  public static void main(String[] args) {
    ValidNumber solution = new ValidNumber();

    System.out.println(solution.isNumber("0"));           // true
    System.out.println(solution.isNumber(" 0.1 "));       // true
    System.out.println(solution.isNumber("53.5e93"));     // true
    System.out.println(solution.isNumber(" 6e-1"));       // true
    System.out.println(solution.isNumber("2e10"));        // true
    System.out.println(solution.isNumber(" -90e3   "));   // true
    System.out.println(solution.isNumber(" 005047e+6"));  // true
    System.out.println(solution.isNumber("32.e-80123"));  // true
    System.out.println(solution.isNumber(".1"));          // true
    System.out.println(solution.isNumber("abc"));         // false
    System.out.println(solution.isNumber("1 a"));         // false
    System.out.println(solution.isNumber(" 1e"));         // false
    System.out.println(solution.isNumber("e3"));          // false
    System.out.println(solution.isNumber(" 99e2.5 "));    // false
    System.out.println(solution.isNumber(" --6 "));       // false
    System.out.println(solution.isNumber("-+3"));         // false
    System.out.println(solution.isNumber("95a54e53"));    // false
    System.out.println(solution.isNumber("."));           // false
    System.out.println(solution.isNumber(".-4"));         // false
    System.out.println(solution.isNumber("6ee69"));       // false
    System.out.println(solution.isNumber("92e1740e91"));  // false
  }

  public boolean isNumber(String s) {
    Character previousChar = null;
    boolean foundDecimalPoint = false;
    boolean foundDigit = false;
    boolean foundExponent = false;

    int i = 0;
    while (i < s.length() && s.charAt(i) == ' ') {
      i++;
    }

    while (i < s.length()) {
      char c = s.charAt(i);
      if (c == ' ') {
        break;
      }

      boolean isSign = isSign(c);
      boolean isDecimalPoint = c == '.';
      boolean isExponent = isExponent(c);

      if ((isDecimalPoint && foundDecimalPoint) ||
          (isDecimalPoint && foundExponent)) {
        return false;
      }
      if ((isSign && foundDigit && !isExponent(previousChar)) ||
          (isSign && isSign(previousChar)) ||
          (isSign && foundDecimalPoint && !foundDigit)) {
        return false;
      }
      if (isExponent && foundExponent) {
        return false;
      }

      if (!isValid(c) || (c == 'e' && !foundDigit)) {
        return false;
      }
      previousChar = c;
      foundDigit |= Character.isDigit(c);
      foundExponent |= isExponent;
      foundDecimalPoint |= isDecimalPoint;

      i++;
    }

    while (i < s.length() && s.charAt(i) == ' ') {
      i++;
    }

    return foundDigit && i == s.length() && isValidEnd(previousChar);
  }

  boolean isValidEnd(Character c) {
    if (c == null) {
      return true;
    }
    return Character.isDigit(c) || c == '.';
  }

  boolean isValid(Character c) {
    return Character.isDigit(c) || isSign(c) || isExponent(c) || c == '.';
  }

  boolean isSign(Character c) {
    if (c == null) {
      return false;
    }
    return c == '+' || c == '-';
  }

  boolean isExponent(Character c) {
    if (c == null) {
      return false;
    }
    return c == 'e';
  }
}
