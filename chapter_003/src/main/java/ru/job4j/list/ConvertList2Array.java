package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;

public class ConvertList2Array {


    public int[][] toArray(List<Integer> list, int rows) {
        int cells = list.size() % rows != 0 ? (list.size() / rows) + 1 : list.size() / rows;

        int[][] array = new int[rows][cells];
        int count = 0;
        int index = 0;
        int k = 0;
        for (int[] in : array) {
            for (int out : in) {

                if (k < list.size()) {
                    System.out.println("k - " + k);
                    System.out.println("index - " + (index));
                    System.out.println("count - " + (count));
                    array[count][index] = list.get(k++);
                    index++;
                    if (index == cells) {
                        index = 0;
                    }
                }
            }
            System.out.println("count - " + (count));
            count++;
        }
        return array;
    }

    public List<Integer> convert(List<int[]> list) {
        List<Integer> result = new ArrayList<>();
        for (int[] in: list) {
            for (int out: in) {
                result.add(out);
            }

        }
        return result;
    }
    }
