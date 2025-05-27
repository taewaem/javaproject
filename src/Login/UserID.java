package Login;

import java.io.*;
import java.util.*;

import Login.User;

public class UserID {

	private static Scanner scan = new Scanner(System.in);

	// 회원 가입
	public static void signUp(String id, String pw, String pw2, String nickname, String birthday, String phnumber) {

		save(new User(id, pw, nickname, birthday, phnumber));

	}

	// 로그인
	public static void login(String id, String pw) {

		List<User> users = load();
		for (User user : users) {
			if (user.getId().equals(id) && user.getPw().equals(pw)) {
				System.out.println("로그인에 성공하셨습니다.");
				return;
			}
		}

		System.out.println("존재하지 않는 아이디 혹은 잘못된 비밀번호 입니다. 다시 입력해주세요");
	}


	// 로그인 체크 (아이디, 비밀번호가 맞으면 true 반환)
	public static boolean loginCheck(String id, String pw) {
		List<User> users = load();
		for (User user : users) {
			if (user.getId().equals(id) && user.getPw().equals(pw)) {
				return true;
			}
		}
		return false;
	}

	// ID 찾기 (닉네임 + 전화번호로 아이디 반환, 없으면 null)
	public static String findId(String nickname, String phnumber) {
		List<User> users = load();
		for (User user : users) {
			if (user.getNickname().equals(nickname) && user.getPhnumber().equals(phnumber)) {
				return user.getId();
			}
		}
		return null;
	}

	// 비밀번호 변경 (아이디 + 전화번호 맞으면 새 비밀번호 저장 후 true 반환)
	public static boolean changePassword(String id, String phnumber, String newPw) {
		List<User> users = load();
		for (User user : users) {
			if (user.getId().equals(id) && user.getPhnumber().equals(phnumber)) {
				user.setPw(newPw);
				saveAll(users);
				return true;
			}
		}
		return false;
	}

	// 파일 저장
	public static void save(User user) {

		if (!checkid(user.getId())) {
			System.out.println("회원가입 실패");
		}
		else {
			System.out.println("회원가입 성공");
		}

		try (BufferedWriter wr = new BufferedWriter(new FileWriter("Info.txt", true))) {
			wr.write(user.getId() + "," + user.getPw() + "," + user.getNickname() + "," + user.getBirthday() + ","
					+ user.getPhnumber());
			wr.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void saveAll(List<User> users) {
		try (BufferedWriter wr = new BufferedWriter(new FileWriter("Info.txt"))) {
			for (User user : users) {
				wr.write(user.getId() + "," + user.getPw() + "," + user.getNickname() + "," + user.getBirthday() + ","
						+ user.getPhnumber());
				wr.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static List<User> load() { // 회원정보 불러오기
		List<User> users = new ArrayList<>();
		try (BufferedReader rd = new BufferedReader(new FileReader("Info.txt"))) {
			String line;
			while ((line = rd.readLine()) != null) {
				String[] log = line.split(",");
				if (log.length == 5) {
					users.add(new User(log[0], log[1], log[2], log[3], log[4]));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return users;

	}

	static boolean checkid(String id) { // 아이디 중복확인
		List<User> users = load();
		for (User user : users) {
			if (user.getId().equals(id)) {
				System.out.println("같은 아이디가 존재");
				return false;
			}
		}

		return true;
	}

	static boolean checkname(String nickname) { // 닉네임 중복확인
		List<User> users = load();
		for (User user : users) {
			if (user.getNickname().equals(nickname)) {
				return false;
			}
		}

		return true;
	}

}