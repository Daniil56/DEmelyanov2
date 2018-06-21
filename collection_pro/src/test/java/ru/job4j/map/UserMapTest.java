package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class UserMapTest {
    private HashMap<User, Object> map = new HashMap<>();
    Calendar one = new Calendar() {
        @Override
        protected void computeTime() {

        }

        @Override
        protected void computeFields() {

        }

        @Override
        public void add(int field, int amount) {

        }

        @Override
        public void roll(int field, boolean up) {

        }

        @Override
        public int getMinimum(int field) {
            return 0;
        }

        @Override
        public int getMaximum(int field) {
            return 0;
        }

        @Override
        public int getGreatestMinimum(int field) {
            return 0;
        }

        @Override
        public int getLeastMaximum(int field) {
            return 0;
        }
    };


    @Test
    public void whenAddTwoUserThanPrintOne() {
        Object object = new Object();
        User first = new User("First", 10, one);
        User second = new User("First", 10, one);
        map.put(first, object);
        map.put(second, object);
        System.out.println(map);
        System.out.println(first.equals(second));
    }

}