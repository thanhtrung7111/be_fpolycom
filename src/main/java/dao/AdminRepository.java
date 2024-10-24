package dao;

import entity.Administration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Administration,Long> {

    Optional<Administration> findByUserLogin(String userLogin);
    Optional<Administration> findByEmail(String email);

}
