
package User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SignIn extends SignUp { //로그인

	public SignIn(String id, String pw, String nickename, String birthday, String phnumber) {
		super(id, pw, nickename, birthday, phnumber);
	}

	private static Scanner scan = new Scanner(System.in);

	protected static void login() {
		System.out.print("아이디: ");
		String id = scan.nextLine();
		System.out.print("비밀번호: ");
		String pw = scan.nextLine();

		List<User> users = load();
		boolean loginsc = false;

		for (User user : users) {
			if (user.getId().equals(id) && user.getPw().equals(pw)) { //로그인 성공 시 
				System.out.println("로그인에 성공하셨습니다.");
				loginsc = true;
				break;
			}
		}

		if (!loginsc) { //로그인 실패 시
			System.out.println("존재하지 않는 아이디 혹은 잘못된 비밀번호 입니다. 다시 입력해주세요");
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

}
