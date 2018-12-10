package com.apap.tugas_akhir_farmasi.web_service.Rest;

public class Setting {
    final public static String stafFarmasiUrl = "http://si-appointment.herokuapp.com/api/{id}/getAllStaffFarmasi";
    final public static String getStafUrl = "http://si-appointment.herokuapp.com/api/getStaff/{id}";
    final public static String siRawatJalanAddMedicalSupplyUrl = "https://siralan.herokuapp.com/rawat-jalan/obat/tambah";
    final public static String addBilling = "http://si-appointment.herokuapp.com/api/3/addBilling";
    final public static String getLabKebutuhan = "http://si-laboratorium.herokuapp.com/lab/kebutuhan/perencanaan";
    final public static String getLabKebutuhan2 = "https://ef19881e-faaf-4ff1-9038-3c8dad784235.mock.pstmn.io/lab/kebutuhan/perencanaan";

    public static String urlGenerator(String id , String text){
        String res = text.replace("{id}",id);
        return res;
    }

}
