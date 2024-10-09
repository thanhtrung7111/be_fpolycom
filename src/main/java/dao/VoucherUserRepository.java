package dao;

import entity.VoucherUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherUserRepository extends JpaRepository<VoucherUser,Long> {
}
