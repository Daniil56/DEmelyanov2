package ru.job4j.generic;

public class UserStore implements Store<User> {
    private SimpleArray<User> value;

    public UserStore(int size) {
        this.value = new SimpleArray<>(size);
    }


    @Override
    public void add(User model) {
        value.add(model);
    }

    @Override
    public boolean replace(String id) {
        boolean result = false;
      if (findVyId(id) != null) {
          result = true;
      }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        if (findVyId(id) != null) {
            result = true;
        }
            return result;
    }

    @Override
    public User findVyId(String id) {
        User itUser = null;
     for (int index = 0; index < value.size(); index++) {
         if (id.equals(value.get(index).getId())) {
             itUser = value.get(index);
         }
     }
        return itUser;
    }
}
