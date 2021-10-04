package com.urise.webapp;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainStream {
    public static void main(String[] args) {
        System.out.println(minValue(new int[]{9, 2, 3, 5, 3, 6, 8}));
        System.out.println(oddOrEven(List.of(1, 5, 8, 6, 4)));
    }

    private static int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce(0, (a, b) -> a * 10 + b);
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        return integers.stream()
                .collect(Collectors.teeing(
                        Collectors.filtering(i -> i % 2 == 0, Collectors.toList()),
                        Collectors.filtering(i -> i % 2 != 0, Collectors.toList()),
                        (even, odd) -> odd.size() % 2 != 0 ? even : odd
                ));
/*        int rod = integers.stream()
                .mapToInt(Integer::intValue).sum() % 2;
        return integers
                .stream()
                .filter(i -> i % 2 != rod)
                .collect(Collectors.toList());*/
    }
}
