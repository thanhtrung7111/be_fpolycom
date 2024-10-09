package dao;

import entity.TypeGood;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface TypeGoodRepository extends JpaRepository<TypeGood, Long> {

}
