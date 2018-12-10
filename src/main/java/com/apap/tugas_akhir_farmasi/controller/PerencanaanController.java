package com.apap.tugas_akhir_farmasi.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.apap.tugas_akhir_farmasi.model.MedicalSuppliesModel;
import com.apap.tugas_akhir_farmasi.model.PerencanaanModel;
import com.apap.tugas_akhir_farmasi.model.UserRoleModel;
import com.apap.tugas_akhir_farmasi.service.service_interface.MedicalSuppliesService;
import com.apap.tugas_akhir_farmasi.service.service_interface.PerencanaanService;
import com.apap.tugas_akhir_farmasi.service.service_interface.UserRoleService;


@Controller
public class PerencanaanController {

    @Autowired
    private PerencanaanService perencanaanService;

    @Autowired
    private MedicalSuppliesService medicalSuppliesService;

    @Autowired
    private UserRoleService userRoleService;

    @RequestMapping(value = "/medical-supplies/perencanaan/tambah", method = RequestMethod.POST)
    public String tambahPerencanaan(@ModelAttribute PerencanaanModel perencanaan){
        perencanaan.setStatus("diajukan");
        perencanaanService.add(perencanaan);

        return "redirect:/medical-supplies/perencanaan";
    }

    @RequestMapping(value = "/medical-supplies/perencanaan", method = RequestMethod.GET)
    public String tampilanPerencanaan(Model model){
        List<PerencanaanModel> listPlan = perencanaanService.findAll();

        // make status list
        String[] statusArray = {"diajukan", "diproses", "tersedia"};
        List<String> statusArraylist = Arrays.asList(statusArray);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserRoleModel user = userRoleService.getUser(authentication.getName());
        model.addAttribute("user", user.getRole());

        model.addAttribute("listPlan", listPlan);
        model.addAttribute("statusPlan", statusArraylist);
        return "tampilan-perencanaan";
    }

    @RequestMapping(value = "/medical-supplies/perencanaan/ganti-status", method = RequestMethod.POST)
    public String gantiStatusPerencanaan(@RequestParam(value = "id") Long id,
                                         @RequestParam(value = "status") String status,
                                         @RequestParam(value = "jumlah") int jumlah,
                                         RedirectAttributes redir){

        // ubah status berdasarkan status baru
        String perubahan = perencanaanService.setStatus(id, status, jumlah);

        redir.addFlashAttribute("message", perubahan);
        return "redirect:/medical-supplies/perencanaan";
    }






}