package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.PhongBan;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the PhongBan entity.
 */
@Repository
public interface PhongBanRepository extends JpaRepository<PhongBan, Long> {
    PhongBan findByTenPhongBan(String tenPhongBan);
}
