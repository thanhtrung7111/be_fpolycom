package dao;

import entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AcceptStoreRepository extends JpaRepository<Store,Long> {

}
