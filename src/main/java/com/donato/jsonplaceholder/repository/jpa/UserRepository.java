package com.donato.jsonplaceholder.repository.jpa;

import com.donato.jsonplaceholder.model.user.domain.UserDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDomain, Long> {
}
