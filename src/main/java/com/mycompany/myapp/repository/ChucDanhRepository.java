package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ChucDanh;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ChucDanh entity.
 */
@Repository
public interface ChucDanhRepository extends JpaRepository<ChucDanh, Long> {
    ChucDanh findByTenChucDanh(String tenChucDanh);
}
