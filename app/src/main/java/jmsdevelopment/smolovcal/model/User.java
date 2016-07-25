package jmsdevelopment.smolovcal.model;


/**
 * Created by Jesper on 04/07/2016.
 */

public class User {
    private String email;
    private int age;
    private String userID;

    private static User instance;

    public static User getCurrentUser() {
        if(instance == null) {
            instance = new User();
        }
        return instance;
    }

    private User() {
    }

    private String generateUserID(){
        if (email != null && !email.isEmpty()) {
            int index = email.indexOf('@');
            userID = email.substring(0, index);
            return userID;
        } else {
            return null;
        }
    }

    public String getUserID(){
        if(userID == null) {
            return generateUserID();
        } else {
            return userID;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
