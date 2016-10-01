package songokute.com.loginwithsqlite;

import java.util.List;

import songokute.com.loginwithsqlite.model.User;

/**
 * Created by KhuongDV on 6/8/2016.
 */
public interface UserInterface {
    public long saveUser(User user);

    public void deleteUser(String username);

    public List<User> getAllUser();

    public boolean checkIfUsernameExist(String username);

    public boolean deleteAllUser();
}
