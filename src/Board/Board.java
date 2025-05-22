package Board;

//Board 클래스: 기능(로직, 데이터 관리)을 담당하는 클래스
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

//게시판 기능을 담당하는 클래스 게시글 추가, 수정, 삭제, 조회, 파일 저장 및 불러오기 등을 처리함
public class Board {
	// 게시글 리스트를 저장하는 변수
	private ArrayList<Post> posts = new ArrayList<>();
	// 게시글 번호 자동 증가를 위한 변수
	private int postNoCounter = 1;

	// [기능] 게시글 추가
	public void addPost(String title, String content) {
		posts.add(new Post(postNoCounter++, title, content));
	}
	// [기능] 특정 게시글 가져오기
	public Post getPost(int postNo) {
		return posts.stream().filter(p -> p.getPostNo() == postNo).findFirst().orElse(null);
	}
	// [기능] 게시글 수정
	public void updatePost(int postNo, String newTitle, String newContent) {
		Post post = getPost(postNo);
		if (post != null) {
			post.setTitle(newTitle);
			post.setContent(newContent);
		}
	}
	// [기능] 게시글 삭제
	public void deletePost(int postNo) {
		// 게시글 삭제
		for (int i = 0; i < posts.size(); i++) {
			if (posts.get(i).getPostNo() == postNo) {
				posts.remove(i);
				break;
			}
		}
		// 번호 재정렬
		for (int i = 0; i < posts.size(); i++) {
			posts.get(i).setPostNo(i + 1);
		}
		// 다음 번호 초기화
		postNoCounter = posts.size() + 1;
	}
	public ArrayList<Post> getAllPosts() {
		return posts;
	}
	// [기능] 게시글을 JList와 같은 GUI에 보여주기 위한 모델 형태로 반환
	public DefaultListModel<Post> getPostListModel() {
		DefaultListModel<Post> model = new DefaultListModel<>();
		for (Post p : posts) {
			model.addElement(p); // 목록에 게시글 추가
		}
		return model;
	}
	// [기능] 게시글 목록과 번호 정보를 파일에 저장
	public void saveToFile(String filename) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
			oos.writeObject(posts); // 게시글 목록 저장
			oos.writeInt(postNoCounter); // 게시글 번호 상태 저장
		} catch (IOException e) {
			e.printStackTrace(); // 저장 실패 시 오류 출력
		}
	}
	// [기능] 저장된 파일로부터 게시글 목록과 번호 상태 불러오기
	@SuppressWarnings("unchecked")
	public void loadFromFile(String filename) {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
			posts = (ArrayList<Post>) ois.readObject(); // 게시글 리스트 불러오기
			postNoCounter = ois.readInt(); // 번호 상태 복원
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("저장된 파일이 없거나 불러오기에 실패했습니다.");
		}
	}
//--------------------------------------------------------------------------------
// 내부 Post 클래스 : 게시글 객체 정의
	public static class Post implements Serializable {
		private int postNo; // 게시글 번호
		private String title; // 게시글 제목
		private String content; // 게시글 내용
		// 운동과 음식 칼로리
		private double exerKcal;
		private double foodKcal;

		// [기능] 칼로리 정보 설정 메서드
		public void CalorieManager(double exerKcal, double foodKcal) {
			this.exerKcal = exerKcal;
			this.foodKcal = foodKcal;
		}
		// 생성자 : 게시글 번호, 제목, 내용 입력받아 초기화
		public Post(int postNo, String title, String content) {
			this.postNo = postNo;
			this.title = title;
			this.content = content;
		}
		// getter, setter 메서드들
		public int getPostNo() {
			return postNo;
		}
		public void setPostNo(int postNo) {
			this.postNo = postNo;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public double getExerKcal() {
			return exerKcal;
		}
		public double getFoodKcal() {
			return foodKcal;
		}
		// 리스트에서 보이게 될 문자열 형태 
		@Override
		public String toString() {
			return postNo + ". " + title;
		}

	}
}