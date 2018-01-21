package bg.android.isma.emotionlog;

/**
 * Created by ismail on 05/10/2017.
 */

public class User {
    public String UserLastName;
    public String UserFirstName;
    public String UserBirthDate;
    public String UserGender;
    public String UserCountryName;
    public String UserEmailAddress;
    public String UserName;
    public String UserPassword;
    public String UserRetypePassword;

    public User() {

    }

    public User(String userId, String userLastName, String userFirstName, String userBirthDate,
                String userGender, String userCountryName, String userEmailAddress,
                String userName, String userPassword, String userRetypePassword) {


        this.UserLastName = userLastName;
        this.UserFirstName = userFirstName;
        this.UserBirthDate = userBirthDate;
        this.UserGender = userGender;
        this.UserCountryName = userCountryName;
        this.UserEmailAddress = userEmailAddress;
        this.UserName = userName;
        this.UserPassword = userPassword;
        this.UserRetypePassword = userRetypePassword;
    }
    
}
