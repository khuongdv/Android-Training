package songokute.com.loginwithsqlite.model;

/**
 * Created by KhuongDV on 6/8/2016.
 */
public class User {
    private String username;
    private String description;

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public User setDescription(String description) {
        this.description = description;
        return this;
    }

    public boolean equals(Object obj){
        if(obj == null || !(obj instanceof User)){
            return false;
        }
        return ((User) obj).getUsername().equals(username);
    }
}
