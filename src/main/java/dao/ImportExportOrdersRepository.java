package dao;

import entity.ImportExportOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImportExportOrdersRepository extends JpaRepository<ImportExportOrders, Long> {
}
