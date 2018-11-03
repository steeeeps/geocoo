package com.geocoo.earthview.repositories;

import com.geocoo.earthview.model.EarthView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * desc:
 *
 * @author taopy
 * @create 2018-10-31 10:36 PM
 */
@Repository
public interface EarthViewRepository extends JpaRepository<EarthView, Integer> {


    @Query("select v from EarthView v order by v.fetchtime desc")
    Page<EarthView> findLatest(Pageable page);

    @Query(value = "select * from earth_view order by random() limit ?1", nativeQuery = true)
    List<EarthView> findRadom(int size);
}