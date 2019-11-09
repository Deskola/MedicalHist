package com.example.medicalhist.model;

import java.io.Serializable;
import java.util.ArrayList;

public class HospitalList implements Serializable {
    public ArrayList<Hospital> hospitals;

    public HospitalList() {
    }

    public ArrayList<Hospital> getHospitals() {
        return hospitals;
    }

    public void setHospitals(ArrayList<Hospital> hospitals) {
        this.hospitals = hospitals;
    }
}
