package com.example.dayte.admin.mianslider.persistence;

import com.example.dayte.admin.mianslider.domain.IndexMainSlider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IndexMainSliderRepository extends JpaRepository<IndexMainSlider, Integer> {
   List<IndexMainSlider> findByHref(String href);
}
