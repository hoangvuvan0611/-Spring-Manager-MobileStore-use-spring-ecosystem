package com.example.managephone.reponsitory;

import com.example.managephone.entity.Mobile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface MobileReponsitory extends JpaRepository<Mobile, Long>, JpaSpecificationExecutor<Mobile> {
    @Query(nativeQuery = true, value = "SELECT c FROM Mobile c WHERE  c.createdTime BETWEEN (:dateFrom, :dateTo)")
    List<Mobile> getListMobileFromDate(@Param("dateFrom") Timestamp dateFrom, @Param("dateTo") Timestamp dateTo);

    Mobile findByActiveEqualsAndTypeEquals(int active, int type);

    List<Mobile> findAllByActiveEqualsAndTypeEquals(int active, int type);
}
