 

public class UsersModel {
    public String secondName;
    public String name;
    public String middleName;
    public String email;
    public String numberPhone;
    public String login;
    public String password;

    public UsersModel(String secondName, String name, String middleName, String email, String numberPhone, String login, String password){
        this.secondName = secondName;
        this.name = name;
        this.middleName = middleName;
        this.email = email;
        this.numberPhone = numberPhone;
        this.login = login;
        this.password = password;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
