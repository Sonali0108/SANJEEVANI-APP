package Sanjeevani.pojo;

public class UserProfile { 

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        UserProfile.username = username;
    }

    public static String getUserType() {
        return userType;
    }

    public static void setUserType(String userType) {
        UserProfile.userType = userType;
    }
    private static String username;
    private  static String userType;
    
    
}
