package ru.job4j.count;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UserStorageTest {
    private final UserStorage firstStorage = new UserStorage();
    private final User firstUser = new User(1, 100);
    private final User secondUser = new User(2, 200);

    @Before
    public void setUp() throws InterruptedException {
        Thread first = new StorageAdd(firstStorage, firstUser);
        Thread second = new StorageAdd(firstStorage, secondUser);
        first.start();
        second.start();
        first.join();
        second.join();
    }
    private class StorageAdd extends Thread {
        private final UserStorage storage;
        private final User user;

        private StorageAdd(UserStorage storage, final User user) {
            this.storage = storage;
            this.user = user;
        }

        @Override
        public void run() {
            this.storage.add(user);
        }
    }

    private class StorageUpdate extends Thread {
        private final UserStorage storage;
        private final User user;

        private StorageUpdate(UserStorage storage, final User user) {
            this.storage = storage;
            this.user = user;
        }

        @Override
        public void run() {
            this.storage.update(user);
        }

    }

    private class StorageTransfer extends  Thread {
        private  UserStorage storage;
        private  int fromID;
        private  int toID;
        private  int amount;

        private StorageTransfer(UserStorage storage,  int fromID,  int toID,  int amount) {
            this.storage = storage;
            this.fromID = fromID;
            this.toID = toID;
            this.amount = amount;
        }

        @Override
        public void run() {
            this.storage.transfer(fromID, toID, amount);
        }

    }

    @Test
    public void whenUserEqualseUser2() {
        User user1 = new User(1, 1000);
        User user2 = new User(2, 5000);
        User user3 = new User(1, 1000);
        assertThat(user1.equals(user2), is(false));
        assertThat(user1.equals(user3), is(true));
    }

    @Test
    public void whenAddUserThenTrue() {
          Map<Integer, User> userMap = new HashMap<>();
          userMap.put(firstUser.getId(), firstUser);
          userMap.put(secondUser.getId(), secondUser);
        assertThat(firstStorage.getUserMap(), is(userMap));

    }


    @Test
    public void whenUpdateUserIncludeStoregeThenTrue() throws InterruptedException {
       final User fourUser = new User(2, 1000);
       final User thredUser = new User(1, 500);
        Thread firstStorageUpdate = new StorageUpdate(firstStorage, thredUser);
        Thread secondStorageUpdate = new StorageUpdate(firstStorage, fourUser);
        firstStorageUpdate.start();
        secondStorageUpdate.start();
        firstStorageUpdate.join();
        secondStorageUpdate.join();
        Map<Integer, User> userMap = new HashMap<>();
        userMap.put(fourUser.getId(), fourUser);
        userMap.put(thredUser.getId(), thredUser);
        assertThat(firstStorage.getUserMap(), is(userMap));
    }

    @Test
    public void whenTransferUseToUserThenTrue() throws InterruptedException {
        Thread first = new StorageTransfer(firstStorage, 1, 2, 100);
        Thread second = new StorageTransfer(firstStorage, 2, 1, 300);
        first.start();
        second.start();
        first.join();
        second.join();
        Map<Integer, User> userMap = new HashMap<>();
        userMap.put(firstUser.getId(), firstUser);
        userMap.put(secondUser.getId(), secondUser);
        assertThat(firstStorage.getUserMap(), is(userMap));
    }
}