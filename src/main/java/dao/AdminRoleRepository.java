package dao;

import entity.AdminRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import service.common.CommonService;

@Repository
public interface AdminRoleRepository extends JpaRepository<AdminRole, Integer> {

}
