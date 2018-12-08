package com.apap.tugas_akhir_farmasi.web_service.Rest;

public class Setting {
    final public static String stafFarmasiUrl = "http://si-appointment.herokuapp.com/api/{id}/getAllStaffFarmasi";
    final public static String getStafUrl = "http://si-appointment.herokuapp.com/api/getStaff/{id}";
    final public static String siRawatJalanAddMedicalSupplyUrl = "unknown";


    public static String urlGenerator(String id , String text){
        String res = text.replace("{id}",id);
        return res;
    }


}
