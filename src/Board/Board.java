
package Board;

import java.io.*;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

public class Board implements Serializable {
	private ArrayList<Post> posts = new ArrayList<>();
	private int postNoCounter = 1;

	public void addPostAndUpdateModel(String title, String content, DefaultListModel<Post> model) {
		Post newPost = new Post(postNoCounter++, title, content);
		posts.add(newPost);
		model.addElement(newPost);
	}

	public void deletePostAndUpdateModel(int index, DefaultListModel<Post> model) {
		if (index >= 0 && index < posts.size()) {
			posts.remove(index);
			model.remove(index);

			// 번호 재정렬
			for (int i = 0; i < posts.size(); i++) {
				posts.get(i).setPostNo(i + 1);
			}
			postNoCounter = posts.size() + 1;

			// 모델 초기화 후 재등록 (번호 변경 반영 위해)
			model.clear();
			for (Post p : posts) {
				model.addElement(p);
			}
		}
	}

	public void updatePostAndModel(int index, String newTitle, String newContent, DefaultListModel<Post> model) {
		if (index >= 0 && index < posts.size()) {
			Post post = posts.get(index);
			post.setTitle(newTitle);
			post.setContent(newContent);
			model.set(index, post);
		}
	}

	public DefaultListModel<Post> getPostListModel() {
		DefaultListModel<Post> model = new DefaultListModel<>();
		for (Post p : posts) {
			model.addElement(p);
		}
		return model;
	}

	// [생략] 파일 저장/불러오기 메서드, 내부 Post 클래스 등은 이전과 동일

	public static class Post implements Serializable {
		private int postNo;
		private String title;
		private String content;
		private double exerKcal;
		private double foodKcal;

		public Post(int postNo, String title, String content) {
			this.postNo = postNo;
			this.title = title;
			this.content = content;
		}
		public int getPostNo() { return postNo; }
		public void setPostNo(int postNo) { this.postNo = postNo; }
		public String getTitle() { return title; }
		public void setTitle(String title) { this.title = title; }
		public String getContent() { return content; }
		public void setContent(String content) { this.content = content; }
		@Override
		public String toString() {
			return postNo + ". " + title;
		}
	}
}