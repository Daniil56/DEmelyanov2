package ru.job4j.map;

import ru.job4j.user.User;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class UserConvert {

    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> result = new HashMap<>();
        for (User user : list) {
            result.put(user.getId(), user);
        }

        return  list.stream().collect(Collectors.toMap(User::getId, users -> users, (a, b) -> b, HashMap::new));
    }
}
