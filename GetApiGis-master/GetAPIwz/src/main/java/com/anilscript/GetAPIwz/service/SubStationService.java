package com.anilscript.GetAPIwz.service;

import com.anilscript.GetAPIwz.model.SubStation;
import com.anilscript.GetAPIwz.repository.SubStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubStationService {

    @Autowired
    private SubStationRepository subStationRepository;

    public List<SubStation> getAllSubStation() {
        System.out.println("In getallsubstaion service class");
        return subStationRepository.findAll();
    }
}
