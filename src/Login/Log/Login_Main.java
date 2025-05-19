package User;

import java.util.Scanner;

public class Login_Main   {


	private static Scanner scan = new Scanner(System.in);


    public static void main(String[] args) {
        while (true) {
            System.out.println("[1]로그인 [2]회원가입 [3]ID,PW찾기 [4]종료");
            int num = scan.nextInt();
            scan.nextLine();

            switch (num) {
                case 1:
                    SignIn.login(); // 로그인
                    break;
                case 2:
                    SignUp.SignUp(); // 회원가입
                    break;
                case 3:
                    IdFound.Found(); // ID,PW찾기
                    break;
                case 4:
                    System.out.println("프로그램을 종료합니다."); //프로그램 종료
                    return;
                default:
                    System.out.println("다시 입력해주세요."); //이상한값
            }
        }
    }


}
