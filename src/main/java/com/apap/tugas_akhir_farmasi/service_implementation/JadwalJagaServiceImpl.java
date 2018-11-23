package com.apap.tugas_akhir_farmasi.service_implementation;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugas_akhir_farmasi.model.JadwalJagaModel;
import com.apap.tugas_akhir_farmasi.repository.JadwalJagaDb;
import com.apap.tugas_akhir_farmasi.service.service_interface.JadwalJagaService;

import java.util.List;

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
