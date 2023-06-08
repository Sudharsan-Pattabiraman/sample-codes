package com.datastructures.stack;

import java.util.*;

public class Main {

    public static void main(final String[] args) {

        System.out.println(count(List.of(1, 2, 2, 3, 1, 4)));
        System.out.println(count(List.of(1, 2, 3, 4, 5, 6)));

    }

    public static int count(List<Integer> nums) {

        int count = 0;
        Map<Integer, Boolean> dup = new HashMap<>();
        for (int i = 0; i < nums.size(); i++) {
            for (int j = i+1; j < nums.size(); j++) {
                if (nums.get(i).equals( nums.get(j))){
                    dup.put(nums.get(i), true);
                }
            }
        }

        System.out.println(dup);

        return dup.size();
    }
}
