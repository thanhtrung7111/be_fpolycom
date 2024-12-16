package dao;

import entity.KeySearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeySearchRepository extends JpaRepository<KeySearch,Long> {
}
