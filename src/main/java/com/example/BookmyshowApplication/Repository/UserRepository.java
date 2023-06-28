package com.example.BookmyshowApplication.Repository;

import com.example.BookmyshowApplication.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
