package com.apap.tugas_akhir_farmasi.service_implementation;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugas_akhir_farmasi.model.FlagUrgentModel;
import com.apap.tugas_akhir_farmasi.model.JenisMedicalSuppliesModel;
import com.apap.tugas_akhir_farmasi.model.MedicalSuppliesModel;
import com.apap.tugas_akhir_farmasi.repository.FlagUrgentDb;
import com.apap.tugas_akhir_farmasi.repository.JenisMedicalSuppliesDb;
import com.apap.tugas_akhir_farmasi.model.MedicalSuppliesModel;
import com.apap.tugas_akhir_farmasi.repository.MedicalSuppliesDb;
import com.apap.tugas_akhir_farmasi.service.service_interface.MedicalSuppliesService;

@Service
@Transactional
public class MedicalSuppliesServiceImpl implements MedicalSuppliesService{
	
	@Autowired
	MedicalSuppliesDb medicalSuppliesDb;
	
	@Autowired
	FlagUrgentDb flagUrgentDb;
	
	@Autowired
	JenisMedicalSuppliesDb jenisMedicalSuppliesDb;

	@Override
	public List<MedicalSuppliesModel> findAll() {
		// TODO Auto-generated method stub
		return medicalSuppliesDb.findAll();
	}

	@Override
	public List<MedicalSuppliesModel> findByUrgent() {
		// TODO Auto-generated method stub
		List<MedicalSuppliesModel> listMedUrg = new ArrayList<MedicalSuppliesModel>();

		// get urgent
		FlagUrgentModel urgent =  flagUrgentDb.findByFlag((short)1);
		
		// get list jenis urgent
		List<JenisMedicalSuppliesModel> listJenUrg = jenisMedicalSuppliesDb.findByFlagUrgentModel(urgent);
		
		// masukkan ke list medical supplies
		for (JenisMedicalSuppliesModel jenisUrg : listJenUrg) {
			listMedUrg.addAll(medicalSuppliesDb.findByJenisMedicalSuppliesModel(jenisUrg));
		}
		
		return listMedUrg;
	}
	
	@Override
	public MedicalSuppliesModel getMedicalSuppliesDetailsByNama(String nama) {
		return medicalSuppliesDb.findByNama(nama);
	}


	@Override
    public MedicalSuppliesModel addMedicalSupplies(MedicalSuppliesModel medicalSupplies) {
        return medicalSuppliesDb.save(medicalSupplies);
    }
	
	@Override
	public MedicalSuppliesModel getDetailMedicalSuppliesById(long id) {
		return medicalSuppliesDb.findById(id);
	}
	
	@Override
	public void updateMedicalSupplies(MedicalSuppliesModel newMedicalSupplies, long id) {
		MedicalSuppliesModel oldMedicalSupplies= this.getDetailMedicalSuppliesById(id);
		oldMedicalSupplies.setNama(newMedicalSupplies.getNama());
		oldMedicalSupplies.setJumlah(newMedicalSupplies.getJumlah());
		oldMedicalSupplies.setPrice(newMedicalSupplies.getPrice());
		oldMedicalSupplies.setDeskripsi(newMedicalSupplies.getDeskripsi());
		oldMedicalSupplies.setJenisMedicalSuppliesModel(newMedicalSupplies.getJenisMedicalSuppliesModel());
	}

	@Override
	public List<MedicalSuppliesModel> getAll() {
		// TODO Auto-generated method stub
		return medicalSuppliesDb.findAll();
	}

	@Override
	public MedicalSuppliesModel getMedicalSuppliesById(long id) {
		// TODO Auto-generated method stub
		return medicalSuppliesDb.findById(id);
	}
}
