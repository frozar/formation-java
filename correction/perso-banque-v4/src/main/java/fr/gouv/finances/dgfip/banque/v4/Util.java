package fr.gouv.finances.dgfip.banque.v4;

public class Util {
  public static String padLeft(String inputString, int length, char paddingChar) {
    if (inputString.length() >= length) {
      return inputString;
    }
    StringBuilder sb = new StringBuilder();
    while (sb.length() < length - inputString.length()) {
      sb.append(paddingChar);
    }
    sb.append(inputString);

    return sb.toString();
  }

  public static String padRight(String inputString, int length, char paddingChar) {
    if (inputString.length() >= length) {
      return inputString;
    }
    StringBuilder sb = new StringBuilder();
    sb.append(inputString);
    while (sb.length() < length) {
      sb.append(paddingChar);
    }

    return sb.toString();
  }

  public static String padLeftZeros(String inputString, int length) {
    return Util.padLeft(inputString, length, '0');
  }

  public static String padLeftSpaces(String inputString, int length) {
    return Util.padLeft(inputString, length, ' ');
  }

  public static String padRightZeros(String inputString, int length) {
    return Util.padRight(inputString, length, '0');
  }

  public static String padRightSpaces(String inputString, int length) {
    return Util.padRight(inputString, length, ' ');
  }

}
