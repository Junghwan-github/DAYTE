package com.example.dayte.admin.mianslider.persistence;

import com.example.dayte.admin.mianslider.domain.IndexMainSlider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IndexMainSliderRepository extends JpaRepository<IndexMainSlider, Integer> {

    Optional<IndexMainSlider> findByCategory(String category);


}
