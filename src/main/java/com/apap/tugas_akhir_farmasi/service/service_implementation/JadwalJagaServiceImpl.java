package com.apap.tugas_akhir_farmasi.service.service_implementation;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugas_akhir_farmasi.model.JadwalJagaModel;
import com.apap.tugas_akhir_farmasi.repository.JadwalJagaDb;
import com.apap.tugas_akhir_farmasi.service.service_interface.JadwalJagaService;

@Service
@Transactional
public class JadwalJagaServiceImpl implements JadwalJagaService {
	@Autowired
	JadwalJagaDb jadwalJagaDb;
	
	@Override
	public JadwalJagaModel getJadwalJagaDetailsById(long id) {
		return jadwalJagaDb.findById(id);
	}

	@Override
	public JadwalJagaModel getJadwalJagaNow() {
		long millis=System.currentTimeMillis();
    	Date todayDate = new Date(millis);
    	Time todayTime = new Time(millis);
    	return jadwalJagaDb.findByTanggalAndWaktuMulaiLessThanEqualAndWaktuSelesaiGreaterThanEqual(todayDate, todayTime, todayTime);
	}
  
    @Override
    public List<JadwalJagaModel> findAll() {
        return jadwalJagaDb.findAll();
    }

    @Override
    public List<JadwalJagaModel> findByStaffId(int id) {
        return jadwalJagaDb.findByidStaff(id);
    }

    @Override
    public void add(JadwalJagaModel jadwalJagaModel) {
        jadwalJagaDb.save(jadwalJagaModel);
    }
}
