package yimu.domain;

import java.util.List;

/**
 * @author
 * @Program
 * @create 2022-02-26-12:29
 */
public class VO {
    private List<User> userList;

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public String toString() {
        return "VO{" +
                "userList=" + userList +
                '}';
    }
}
