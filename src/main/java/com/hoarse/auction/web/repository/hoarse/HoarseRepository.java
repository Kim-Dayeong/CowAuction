package com.hoarse.auction.web.repository.hoarse;

import com.hoarse.auction.web.entity.hoarse.Hoarse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoarseRepository extends JpaRepository<Hoarse, Long> {
}
