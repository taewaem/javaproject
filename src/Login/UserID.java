package UserMain;

import java.io.*;
import java.util.*;

import User.User;

public class UserID {

	private static Scanner scan = new Scanner(System.in);

	// 회원 가입
	public static void signUp() {
		System.out.print("사용하실 아이디를 입력해주세요: ");
		String id, pw, pw2, nickname, birthday, phnumber;

		while (true) {
			id = scan.nextLine();
			if (checkid(id)) {
				System.out.println("사용 가능한 아이디 입니다.");
				break;
			} else {
				System.out.println("이미 존재하는 아이디 입니다.");
			}
		}

		while (true) {
			System.out.print("비밀번호를 입력해주세요: ");
			pw = scan.nextLine();
			System.out.print("다시 한번 입력해주세요: ");
			pw2 = scan.nextLine();
			if (pw.equals(pw2)) {
				break;
			} else {
				System.out.println("비밀번호가 일치하지 않습니다.");
			}
		}

		while (true) {
			System.out.print("사용하실 닉네임을 입력해주세요: ");
			nickname = scan.nextLine();
			if (checkname(nickname)) {
				System.out.println("사용 가능한 닉네임 입니다.");
				break;
			} else {
				System.out.println("이미 존재하는 닉네임 입니다.");
			}
		}

		while (true) {
			System.out.print("생년월일을 입력해 주세요(예시.20010116): ");
			birthday = scan.nextLine();
			if (birthday.length() == 8) {
				break;
			} else {
				System.out.println("잘못된 생년월일 입니다 다시 입력해주세요.");
			}
		}

		while (true) {
			System.out.print("전화번호를 입력해주세요(예시.01047757921): ");
			phnumber = scan.nextLine();
			if (phnumber.length() == 11) {
				break;
			} else {
				System.out.println("잘못된 전화번호 입니다 다시 입력해주세요.");
			}
		}

		save(new User(id, pw, nickname, birthday, phnumber));
		System.out.println(nickname + "님 회원가입이 완료되었습니다.");
	}

	// 로그인
	public static void login() {
		System.out.print("아이디: ");
		String id = scan.nextLine();
		System.out.print("비밀번호: ");
		String pw = scan.nextLine();

		List<User> users = load();
		for (User user : users) {
			if (user.getId().equals(id) && user.getPw().equals(pw)) {
				System.out.println("로그인에 성공하셨습니다.");
				return;
			}
		}

		System.out.println("존재하지 않는 아이디 혹은 잘못된 비밀번호 입니다. 다시 입력해주세요");
	}

	// 아이디 비밀번호 찾기
	public static void findMenu() {
		while (true) {
			System.out.println("[1]ID 찾기 [2]PW 찾기 [3]종료");
			int choice = scan.nextInt();
			scan.nextLine(); // 버퍼 클리어

			switch (choice) {
			case 1:
				findId();
				break;
			case 2:
				findPassword();
				break;
			case 3:
				return;
			default:
				System.out.println("잘못된 입력입니다.");
			}
		}
	}

	// 아이디 찾기
	public static void findId() {
		System.out.print("닉네임을 입력해주세요: ");
		String nickname = scan.nextLine();
		System.out.print("전화번호를 입력해주세요: ");
		String phnumber = scan.nextLine();

		for (User user : load()) {
			if (user.getNickname().equals(nickname) && user.getPhnumber().equals(phnumber)) {
				System.out.println("회원님의 아이디는 [" + user.getId() + "]입니다.");
				return;
			}
		}

		System.out.println("일치하는 아이디가 없습니다.");
	}

	// 비밀번호 찾기
	public static void findPassword() {
		System.out.print("아이디를 입력해주세요: ");
		String id = scan.nextLine();
		System.out.print("전화번호를 입력해주세요: ");
		String phnumber = scan.nextLine();

		List<User> users = load();
		for (User user : users) {
			if (user.getId().equals(id) && user.getPhnumber().equals(phnumber)) {
				while (true) {
					System.out.print("새 비밀번호를 입력해주세요: ");
					String newPw1 = scan.nextLine();
					System.out.print("다시 한번 입력해주세요: ");
					String newPw2 = scan.nextLine();
					if (newPw1.equals(newPw2)) {
						user.setPw(newPw2);
						saveAll(users);
						System.out.println("비밀번호가 성공적으로 변경되었습니다.");
						return;
					} else {
						System.out.println("비밀번호가 일치하지 않습니다.");
					}
				}
			}
		}

		System.out.println("일치하는 사용자 정보가 없습니다.");
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
