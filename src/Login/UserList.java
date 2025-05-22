package Login;

import java.util.ArrayList;
import java.util.List;

public class UserList {

    public static ArrayList<User> userList = new ArrayList<>();

    public boolean signup(String id, String pw,String pw2, String nickname, String birthday, String phnumber) {
        if (checkid(id)) {
            if (!pw.equals(pw2)) {
                System.out.println("비밀번호가 다릅니다");
                return false;
            }
            User newUser = new User(id, pw, nickname, birthday, phnumber);
            userList.add(newUser);
            System.out.println("회원 추가됨: " + newUser);
            return true;

        }
        else {
            System.out.println("같은 아이디 존재");
            return false;
        }
    }

    public boolean login(String id, String pw) {
        for (User user : userList) {
            if (user.getId().equals(id) && user.getPw().equals(pw)) {
                System.out.println("로그인 성공");
                return true;
            }
        }
        return false;
    }

    public boolean checkid(String id) { // 아이디 중복확인
        for (User user : userList) {
            if (user.getId().equals(id)) {
                System.out.println("같은 아이디가 존재");
                return false;
            }
        }
        return true;
    }

    public boolean checkNickname(String nickName) { // 아이디 중복확인
        for (User user : userList) {
            if (user.getId().equals(nickName)) {
                System.out.println("같은 닉네임 존재");
                return false;
            }
        }
        return true;
    }

    public void save(User user) {
        userList.add(user);
    }

    public List<User> getAllUser() {
        return userList;
    }

}
