package dao;

import entity.PasswordRecover;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordRecoverRepository  extends JpaRepository<PasswordRecover,String> {
}
