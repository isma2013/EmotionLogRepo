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
    public String EmotionDescription;


    public User(String userId, String userLastName, String userFirstName, String userBirthDate,
                String userGender, String userCountryName, String emotionDescription) {


        this.UserLastName = userLastName;
        this.UserFirstName = userFirstName;
        this.UserBirthDate = userBirthDate;
        this.UserGender = userGender;
        this.UserCountryName = userCountryName;
        this.EmotionDescription = emotionDescription;
    }

}
