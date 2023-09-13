 

public class UserModel {

    public int Id_User;
    public int Id_Post;
    public String User_Login;
    public String User_password;
    public String Salt;

    public int getId_User() {
        return Id_User;
    }

    public void setId_User(int id_User) {
        Id_User = id_User;
    }

    public int getId_Post() {
        return Id_Post;
    }

    public void setId_Post(int id_Post) {
        Id_Post = id_Post;
    }

    public String getUser_Login() {
        return User_Login;
    }

    public void setUser_Login(String user_Login) {
        User_Login = user_Login;
    }

    public String getUser_password() {
        return User_password;
    }

    public void setUser_password(String user_password) {
        User_password = user_password;
    }

    public String getSalt() {
        return Salt;
    }

    public void setSalt(String salt) {
        Salt = salt;
    }
}
