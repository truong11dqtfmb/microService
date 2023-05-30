package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ChucDanh;
import com.mycompany.myapp.domain.NhanSu;
import com.mycompany.myapp.domain.PhongBan;
import com.mycompany.myapp.service.dto.request.PhongBanGroup;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the NhanSu entity.
 */
@Repository
//@EnableJpaRepositories
public interface NhanSuRepository extends JpaRepository<NhanSu, Long> {
    default Optional<NhanSu> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<NhanSu> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<NhanSu> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select distinct nhanSu from NhanSu nhanSu left join fetch nhanSu.chucDanh left join fetch nhanSu.phongBan",
        countQuery = "select count(distinct nhanSu) from NhanSu nhanSu"
    )
    Page<NhanSu> findAllWithToOneRelationships(Pageable pageable);

    @Query("select distinct nhanSu from NhanSu nhanSu left join fetch nhanSu.chucDanh left join fetch nhanSu.phongBan")
    List<NhanSu> findAllWithToOneRelationships();

    @Query("select nhanSu from NhanSu nhanSu left join fetch nhanSu.chucDanh left join fetch nhanSu.phongBan where nhanSu.id =:id")
    Optional<NhanSu> findOneWithToOneRelationships(@Param("id") Long id);

    List<NhanSu> findByChucDanh(ChucDanh chucDanh);

    List<NhanSu> findByPhongBan(PhongBan phongBan);

    List<NhanSu> findAllByChucDanh(ChucDanh chucDanh);

    List<NhanSu> findAllByPhongBan(PhongBan phongBan);

    //    @Query("select new com.mycompany.myapp.service.dto.request.PhongBanGroup(pb.id as PhongBan,count(ns.phong_ban_id) as SoLuong) from phong_ban as pb join nhan_su as ns on pb.id=ns.phong_ban_id  group by pb.id")
    //    @Query(value = "SELECT new com.mycompany.myapp.service.dto.request.PhongBanGroup(ns.phong_ban_id, COUNT(ns.phong_ban_id))" +
    //        "FROM nhan_su AS ns  GROUP BY ns.phong_ban_id ORDER BY ns.phong_ban_id DESC", nativeQuery = true)
    //    List<PhongBanGroup> groupByPhongBan();

    //    @Query(value = "SELECT ns.phong_ban_id, COUNT(ns.phong_ban_id) FROM nhan_su AS ns GROUP BY ns.phong_ban_id ", nativeQuery = true)
    //    @Query(value = "SELECT pb.ten_phong_ban, COUNT(ns.phong_ban_id) from phong_ban as pb join nhan_su as ns on pb.id=ns.phong_ban_id  group by pb.id", nativeQuery = true)
    @Query(
        value = "SELECT pb.id, COUNT(ns.phong_ban_id) from phong_ban as pb join nhan_su as ns on pb.id=ns.phong_ban_id  group by pb.id",
        nativeQuery = true
    )
    List<Object[]> groupByPhongBan();

    //    @Query(value = "SELECT cd.ten_chuc_danh, COUNT(ns.chuc_danh_id) from chuc_danh as cd join nhan_su as ns on cd.id=ns.chuc_danh_id  group by cd.id", nativeQuery = true)
    @Query(
        value = "SELECT cd.id, COUNT(ns.chuc_danh_id) from chuc_danh as cd join nhan_su as ns on cd.id=ns.chuc_danh_id  group by cd.id",
        nativeQuery = true
    )
    List<Object[]> groupByChucDanh();
}
