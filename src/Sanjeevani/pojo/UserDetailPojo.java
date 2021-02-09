package Sanjeevani.pojo;

public class UserDetailPojo {

    @Override
    public String toString() {
        return "UserDetailPojo{" + "userId=" + userId + ", password=" + password + ", userType=" + userType + ", userName=" + userName + ", empid=" + empid + '}';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

private String userId,password,userType,userName,empid;

}
