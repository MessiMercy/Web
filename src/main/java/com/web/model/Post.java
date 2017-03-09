package com.web.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Post {
	private static long count = 0;

	@Id
	@NotNull
	private long id;
	@Size(min = 5, max = 10)
	private String title;
	@Size(min = 10)
	private String content;
	private Date created = new Date();

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Post() {
		count++;
	}

	public Post(String title, String content, long id) {
		count++;
		this.id = id;
		this.title = title;
		this.content = content;
	}

	public static long getCount() {
		return count;
	}

	public static void setCount(long count) {
		Post.count = count;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

}
