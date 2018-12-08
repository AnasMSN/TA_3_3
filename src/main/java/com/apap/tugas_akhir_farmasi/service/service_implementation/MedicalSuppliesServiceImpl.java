package com.apap.tugas_akhir_farmasi.service.service_implementation;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.apap.tugas_akhir_farmasi.model.FlagUrgentModel;
import com.apap.tugas_akhir_farmasi.model.JenisMedicalSuppliesModel;
import com.apap.tugas_akhir_farmasi.model.MedicalSuppliesModel;
import com.apap.tugas_akhir_farmasi.repository.FlagUrgentDb;
import com.apap.tugas_akhir_farmasi.repository.JenisMedicalSuppliesDb;
import com.apap.tugas_akhir_farmasi.repository.MedicalSuppliesDb;
import com.apap.tugas_akhir_farmasi.service.service_interface.MedicalSuppliesService;
import com.apap.tugas_akhir_farmasi.web_service.Rest.Setting;

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

	@Override
	public String addMedicalSuppliesToRawatJalan(String nama, int jumlah) {
			String path = Setting.siRawatJalanAddMedicalSupplyUrl;
		   
		   LinkedHashMap<String, Object> medicalSupplyForRawatJalan = new LinkedHashMap<String,Object>();
		   
		   LinkedHashMap<String, Object> medicalSupply = new LinkedHashMap<String,Object>();
		   medicalSupply.put("nama", nama);
		   
		   medicalSupplyForRawatJalan.put("medicalSupply", new JSONObject(medicalSupply));
		   medicalSupplyForRawatJalan.put("jumlah", jumlah);
		   
		   JSONObject json = new JSONObject(medicalSupplyForRawatJalan);
		   
		   HttpHeaders headers = new HttpHeaders();
		   headers.setContentType(MediaType.APPLICATION_JSON);
		   
		   HttpEntity<String> request = new HttpEntity<String>(json.toString(), headers);
		   System.out.println(request);
		   //System.out.println(restTemplate.postForObject(path, request, String.class));
		   return null;
	}
	
}
