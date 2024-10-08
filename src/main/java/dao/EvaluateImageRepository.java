package dao;

import entity.EvaluateImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluateImageRepository extends JpaRepository<EvaluateImage,Long> {
}
