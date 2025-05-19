package User;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IdFound extends SignIn {

	private static Scanner scan = new Scanner(System.in);

	public IdFound(String id, String pw, String nickename, String birthday, String phnumber) {
		super(id, pw, nickename, birthday, phnumber);
	}

	public static void Found() {
		while (true) {
			System.out.println("[1]ID찾기 [2]PW찾기 [3]종료");
			int n = scan.nextInt();
			scan.nextLine();

			if (n == 1) {
				IDF();
			} else if (n == 2) {
				PWF();
			} else if (n == 3) {
				break;
			} else {
				System.out.println("다시 입력해주세요");
			}

		}
	}

	public static void IDF() { // 아이디찾기
		System.out.print("닉네임을 입력해주세요: ");

		String nk = scan.nextLine(); // 닉네임
		System.out.print("전화번호를 입력해주세요: ");
		String pn = scan.nextLine(); // 전화번호

		List<User> users = load();
		for (User user : users) {
			if (user.getNickname().equals(nk) && user.getPhnumber().equals(pn)) { // 닉네임과 전화번호 일치 시 아이디 출력
				System.out.println("회원님의 아이디는[" + user.getId() + "]입니다");
				break;
			} else {
				System.out.println("일치하는 아이디가 존재하지 않습니다.");
				return;
			}
		}

	}

	public static void PWF() { // 비밀번호 찾기
		System.out.print("아이디를 입력해주세요: ");
		String idc = scan.nextLine(); // 닉네임

		System.out.print("전화번호를 입력해주세요: ");
		String pn = scan.nextLine(); // 전화번호

		List<User> users = load();
		boolean found = false;
		for (User user : users) {
			if (user.getId().equals(idc) && user.getPhnumber().equals(pn)) {
				System.out.println("비밀번호를 변경합니다.");

				while (true) {
					System.out.print("비밀번호를 입력해주세요: ");
					String pw = scan.nextLine();
					System.out.print("다시 한번 입력해주세요: ");
					String pw2 = scan.nextLine();

					if (pw.equals(pw2)) {
						System.out.println("비밀번호 변경이 완료되었습니다.");
						user.setPw(pw2); // 메모리 내 객체에서 변경
						found = true;
						break;
					} else {
						System.out.println("비밀번호가 일치하지 않습니다.");
					}
				}
				break;
			}
		}

		if (found) {
			savepw(users); // 변경된 전체 리스트를 파일에 덮어쓰기
		} else {
			System.out.println("일치하는 아이디가 존재하지 않습니다.");
		}
	}

	private static void save(String id, String pw, String nickname, String birthday, String phnumber) { // 비밀번호 변경용
		try (BufferedWriter wr = new BufferedWriter(new FileWriter("Info.txt", true))) {
			wr.write(id + "," + pw + "," + nickname + "," + birthday + "," + phnumber);
			wr.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static List<User> load() { // 불러오기용
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

	private static void savepw(List<User> users) { // 비밀번호 변경용
		try (BufferedWriter wr = new BufferedWriter(new FileWriter("Info.txt", false))) {
			for (User user : users) {
				wr.write(user.getId() + "," + user.getPw() + "," + user.getNickname() + "," + user.getBirthday() + ","
						+ user.getPhnumber());
				wr.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
