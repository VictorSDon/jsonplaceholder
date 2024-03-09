package com.donato.jsonplaceholder.repository.jpa;

import com.donato.jsonplaceholder.model.post.PostDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostDomain, Long> {
}
