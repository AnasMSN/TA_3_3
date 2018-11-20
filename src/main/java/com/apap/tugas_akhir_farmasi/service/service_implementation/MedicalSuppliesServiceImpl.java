package com.apap.tugas_akhir_farmasi.service.service_implementation;

import com.apap.tugas_akhir_farmasi.repository.MedicalSuppliesDb;
import com.apap.tugas_akhir_farmasi.service.service_interface.MedicalSuppliesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MedicalSuppliesServiceImpl implements MedicalSuppliesService {
    @Autowired
    private MedicalSuppliesDb medicalSuppliesDb;


}
