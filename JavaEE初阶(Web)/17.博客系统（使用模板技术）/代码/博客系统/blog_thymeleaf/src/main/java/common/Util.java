package common;
import dao.Users;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author wangyimu
 * @Program 2022
 * @create 2022-02-14-19:51
 */
public class Util {
    public static Users checkLoginStatus(HttpServletRequest req){
        // 此处判定当前是否处于登录状态，如果能够拿到 Session 里的 User 对象，就认为是
        // 已经都登陆的状态
        HttpSession session = req.getSession(false);
        if(session == null){
            // 未登录状态
            return null;
        }
        Users user = (Users) session.getAttribute("user");
        if(user == null){
            // 未登录状态
            return null;
        }
        return user;
    }
}
