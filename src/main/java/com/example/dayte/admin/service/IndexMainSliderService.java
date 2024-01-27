package com.example.dayte.admin.service;


import com.example.dayte.admin.domain.IndexMainSlider;
import com.example.dayte.admin.dto.IndexMainSliderDTO;
import com.example.dayte.admin.persistence.IndexMainSliderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndexMainSliderService {

    @Autowired
    private IndexMainSliderRepository indexMainSliderRepository;

    @Autowired
    private ModelMapper modelMapper;



    public void InsertSlider(IndexMainSliderDTO indexMainSliderDTO) {
        IndexMainSlider indexMainSlider = modelMapper.map(indexMainSliderDTO, IndexMainSlider.class);
        indexMainSliderRepository.save(indexMainSlider);

    }
}
