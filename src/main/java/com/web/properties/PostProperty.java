package com.web.properties;

import com.web.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostProperty extends JpaRepository<Post, Long> {

}
