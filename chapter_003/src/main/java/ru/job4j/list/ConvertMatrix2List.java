package ru.job4j.list;


import java.util.ArrayList;
import java.util.List;

public class ConvertMatrix2List {
    public List<Integer> toList(int[][] array) {
        int count = 0;
        int index = 0;
    List<Integer> list = new ArrayList<>();
        for (int[] in: array) {
            for (int out: in) {
                list.add(out);
            }
        }
        return list;
    }
}