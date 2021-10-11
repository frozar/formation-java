package com.example;

import java.util.HashMap;

public class Application {
  public static void main(String[] args) {
    String word0 = args[0];
    String word1 = args[1];

    HashMap<Character, Integer> word0Map = new HashMap<Character, Integer>();

    for (char c : word0.toCharArray()) {
      Integer occurence = word0Map.get(c);
      if (occurence == null) {
        word0Map.put(c, 0);
      } else {
        word0Map.put(c, occurence + 1);
      }
    }

    HashMap<Character, Integer> word1Map = new HashMap<Character, Integer>();

    for (char c : word1.toCharArray()) {
      Integer occurence = word1Map.get(c);
      if (occurence == null) {
        word1Map.put(c, 0);
      } else {
        word1Map.put(c, occurence + 1);
      }
    }

    boolean res = true;
    for (char c : word0Map.keySet()) {
      Integer occ0 = word0Map.get(c);
      Integer occ1 = word1Map.get(c);
      if (occ1 == null) {
        res = false;
        break;
      } else if (occ0 != occ1) {
        res = false;
        break;
      }
    }

    if (res) {
      System.out.println(1);
    } else {
      System.out.println(0);
    }
  }
}
