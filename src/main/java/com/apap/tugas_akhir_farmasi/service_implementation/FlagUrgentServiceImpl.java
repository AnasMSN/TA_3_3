package com.apap.tugas_akhir_farmasi.service_implementation;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugas_akhir_farmasi.model.FlagUrgentModel;
import com.apap.tugas_akhir_farmasi.repository.FlagUrgentDb;
import com.apap.tugas_akhir_farmasi.service.service_interface.FlagUrgentService;

@Service
@Transactional
public class FlagUrgentServiceImpl implements FlagUrgentService{
	@Autowired
	private FlagUrgentDb  flagUrgentDb;

	@Override
	public List getListFlagUrgent() {
		List<FlagUrgentModel> listFlag = flagUrgentDb.findAll();
		return listFlag;
	}

}
