package com.apap.tugas_akhir_farmasi.service.service_implementation;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugas_akhir_farmasi.model.MedicalSuppliesModel;
import com.apap.tugas_akhir_farmasi.model.PerencanaanModel;
import com.apap.tugas_akhir_farmasi.repository.PerencanaanDb;
import com.apap.tugas_akhir_farmasi.service.service_interface.PerencanaanService;

@Service
@Transactional
public class PerencanaanServiceImpl implements PerencanaanService{

	@Autowired
	PerencanaanDb perencanaanDb;

	@Override
	public List<PerencanaanModel> findAll() {
		// TODO Auto-generated method stub
		return perencanaanDb.findAll();
	}

	@Override
	public void add(PerencanaanModel perencanaan) {
		// TODO Auto-generated method stub
		perencanaanDb.save(perencanaan);
	}

	@Override
	public PerencanaanModel findById(Long id) {
		// TODO Auto-generated method stub
		return perencanaanDb.findById(id).get();
	}

	@Override
	public String setStatus(Long id, String status, int jumlah) {
		// TODO Auto-generated method stub
		PerencanaanModel perencanaan = perencanaanDb.findById(id).get();
		String perubahan = null;
		
		String statusLama = perencanaan.getStatus();
		if (statusLama.equals("tersedia")) {
			if (!status.equals("tersedia")) {
				// set jumlah medical supplies
				MedicalSuppliesModel med = perencanaan.getMedicalSupplies();
				med.setJumlah(med.getJumlah() - perencanaan.getJumlah());
				perubahan = "kurang";
			}
		}
		if (status.equals("tersedia")) {
			if (!statusLama.equals("tersedia")) {
				// set jumlah medical supplies
				MedicalSuppliesModel med = perencanaan.getMedicalSupplies();
				med.setJumlah(med.getJumlah() + perencanaan.getJumlah());
				perubahan = "tambah";
			}
		}
		
		if (perubahan == null) {
			if (statusLama.equals(status)) {
				perubahan = "tidak berubah";
			}
			else {
				perubahan = "berubah";
			}
		}
		
		perencanaan.setStatus(status);
		return perubahan;
	}
	
}
