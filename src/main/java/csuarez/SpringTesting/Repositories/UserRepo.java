package csuarez.SpringTesting.Repositories;

import csuarez.SpringTesting.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    @Query ("SELECT u FROM Users u WHERE u.username = :username")
    User findByUsername(@Param("username") String username);
}
