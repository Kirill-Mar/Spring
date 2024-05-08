package web.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void createOrUpdateUser(User user) {
        if (user.getId() == 0) {
            createUser(user);
        } else {
            updateUser(user);
        }
    }

    private void createUser(User user) {
        userDao.createUser(user);
    }

    private void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public User readUser(long id) {
        return userDao.readUser(id);
    }

    @Override
    public User deleteUser(long id) {
        User user = userDao.readUser(id);
        if (user != null) {
            userDao.deleteUser(id);
        }
        return user;
    }
}
