package dao;

import entity.TokenRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRegisterRepository extends JpaRepository<TokenRegister,String> {
}
