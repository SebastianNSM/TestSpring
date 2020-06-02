package com.shopped.demo.dao;

import com.shopped.demo.model.User;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("noSql")
public class FakeUserDataAccessService implements UserDao {

    private static List<User> DB = new ArrayList<User>();

    @Override
    public int insertUser(UUID id, User user) {
        if (user.getName().isEmpty())
            return 0;
        DB.add(new User(id, user.getName()));
        return 1;
    }

    @Override
    public List<User> getAllUsers() {
        return DB;
    }

    @Override
    public Optional<User> selectUserById(UUID id) {
        return DB.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deleteUserById(UUID id) {
        Optional<User> userMaybe = selectUserById(id);
        if (userMaybe.isEmpty())
                return 0;
        else
            DB.remove(userMaybe.get());
        return 1;
    }

    @Override
    public int updateUserById(UUID id, User update) {
        return selectUserById(id)
                .map(u -> {
                    int indexOfUser = DB.indexOf(u);
                    if (indexOfUser >= 0) {
                        DB.set(indexOfUser, update);
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }
}
