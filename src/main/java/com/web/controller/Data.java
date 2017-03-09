package com.web.controller;

import com.web.exception.PostNotFoundException;
import com.web.model.Post;

import java.util.ArrayList;

public class Data {
	public static ArrayList<Post> ramData = new ArrayList<>();

	static {
		ramData.add(new Post("it`s a title", "how to learn springMvc", 408708006l));
		ramData.add(new Post("second title", "my example", 1l));
	}

	public static Post getId(long id) {
		return ramData.stream().filter(p -> p.getId() == id).findFirst().orElseThrow(PostNotFoundException::new);
	}

	public static void add(Post post) {
		ramData.add(post);
	}

	public static Post add(String title, String content, long id) {
		Post result = new Post(title, content, id);
		ramData.add(result);
		return result;
	}

}
