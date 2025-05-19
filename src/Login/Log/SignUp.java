package User;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SignUp extends User {

	public SignUp(String id, String pw, String nickename, String birthday, String phnumber) {
		super(id, pw, nickename, birthday, phnumber);
	}

	private static final String FILE_PATH = "Info.txt";

	private static Scanner scan = new Scanner(System.in);

	public static void SignUp() { //회원가입
		System.out.print("사용하실 아이디를 입력해주세요: ");
		String id;// 아이디
		String pw;// 비밀번호
		String pw2;// 비밀번호확인
		String nickname;// 닉네임
		String birthday;// 생일
		String phnumber;// 전화번호
		while (true) {
			id = scan.nextLine();
			if (checkid(id)) {// 아이디 중복체크
				System.out.println("사용 가능한 아이디 입니다.");
				break;
			} else {
				System.out.println("이미 존재하는 아이디 입니다.");
			}

		} // while

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

		} // while

		while (true) {
			System.out.print("사용하실 닉네임을 입력해주세요: ");
			nickname = scan.nextLine();
			if (checkname(nickname)) {
				System.out.println("사용 가능한 닉네임 입니다.");
				break;
			} else {
				System.out.println("이미 존재는 닉네임 입니다.");
			}

		}

		while (true) {
			System.out.println("생년월일을 입력해 주세요(예시.20010116):");
			birthday = scan.nextLine();
			if (birthday.length() == 8) {
				System.out.println("생년월일 입력이 완료되었습니다.");
				break;
			} else {
				System.out.println("잘못된 생년월일 입니다 다시 입력해주세요.");
			}
		}

		while (true) {
			System.out.print("전화번호를 입력해주세요(예시.01047757921):");
			phnumber = scan.nextLine();
			if (phnumber.length() == 11) {
				System.out.println("전화번호 입력이 완료되었습니다.");
				break;
			} else {
				System.out.println("잘못된 전화번호 입니다 다시 입력해주세요.");
			}
		}
		save(id, pw, nickname, birthday, phnumber);
		System.out.println(nickname + "");

	}

	private static boolean checkid(String id) { // 아이디 중복확인
		List<User> users = load();
		for (User user : users) {
			if (user.getId().equals(id)) {
				return false;
			}
		}

		return true;
	}

	private static boolean checkname(String nickname) { // 닉네임 중복확인
		List<User> users = load();
		for (User user : users) {
			if (user.getNickname().equals(nickname)) {
				return false;
			}
		}

		return true;
	}

	private static void save(String id, String pw, String nickname, String birthday, String phnumber) { // 회원정보 저장
		try (BufferedWriter wr = new BufferedWriter(new FileWriter("Info.txt", true))) {
			wr.write(id + "," + pw + "," + nickname + "," + birthday + "," + phnumber);
			wr.newLine();
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

}
