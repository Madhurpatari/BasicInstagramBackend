package com.Geekster.BasicInstagramDesign.Repositories;

import com.Geekster.BasicInstagramDesign.Models.AuthenticationToken;
import com.Geekster.BasicInstagramDesign.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthenticationTokenRepository extends JpaRepository<AuthenticationToken, Long> {
    AuthenticationToken findByUser(User user);
}
