package com.Geekster.BasicInstagramDesign.Repositories;

import com.Geekster.BasicInstagramDesign.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    User findFirstByEmail(String email);

    User getUserByEmail(String email);
}
