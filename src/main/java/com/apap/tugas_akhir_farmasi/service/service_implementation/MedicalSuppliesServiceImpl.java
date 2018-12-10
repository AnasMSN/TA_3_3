package com.apap.tugas_akhir_farmasi.service.service_implementation;
import java.io.IOException;
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
import org.springframework.web.client.RestTemplate;

import com.apap.tugas_akhir_farmasi.model.FlagUrgentModel;
import com.apap.tugas_akhir_farmasi.model.JenisMedicalSuppliesModel;
import com.apap.tugas_akhir_farmasi.model.MedicalSuppliesModel;
import com.apap.tugas_akhir_farmasi.repository.FlagUrgentDb;
import com.apap.tugas_akhir_farmasi.repository.JenisMedicalSuppliesDb;
import com.apap.tugas_akhir_farmasi.repository.MedicalSuppliesDb;
import com.apap.tugas_akhir_farmasi.service.service_interface.MedicalSuppliesService;
import com.apap.tugas_akhir_farmasi.web_service.Rest.Setting;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	public int addMedicalSuppliesToRawatJalan(MedicalSuppliesModel medSupp, String nama, int jumlah) throws IOException {
			String path = Setting.siRawatJalanAddMedicalSupplyUrl;
		   
		   LinkedHashMap<String, Object> medicalSupplyForRawatJalan = new LinkedHashMap<String,Object>();
		   
		   medicalSupplyForRawatJalan.put("nama", nama);
		   medicalSupplyForRawatJalan.put("jumlah", jumlah);
		   
		   JSONObject json = new JSONObject(medicalSupplyForRawatJalan);
		   
		   HttpHeaders headers = new HttpHeaders();
		   headers.setContentType(MediaType.APPLICATION_JSON);
		   
		   HttpEntity<String> request = new HttpEntity<String>(json.toString(), headers);
		   RestTemplate restTemplate = new RestTemplate();
		   String jsonResponse = (restTemplate.postForObject(path, request, String.class));
		   ObjectMapper mapper = new ObjectMapper();
		   JsonNode node = mapper.readTree(jsonResponse);
		   JsonNode result = node.get("status");
		   int status = mapper.treeToValue(result, Integer.class);
		   
		   if(status == 200) {
			   medSupp.setJumlah(medSupp.getJumlah() - jumlah);
			   this.addMedicalSupplies(medSupp);
		   }
		   return status;
	}
	
	public Boolean cekStatusMedicalSupplies(String nama) {
		List<MedicalSuppliesModel> listMedical = medicalSuppliesDb.findAll();
		MedicalSuppliesModel newNama=medicalSuppliesDb.findByNama(nama);
		
		for (int i=0; i<listMedical.size();i++) {
			if (listMedical.get(i).getNama().equals(nama) ) {
				return true;
			}
		}
		return false;
	}
	
}
