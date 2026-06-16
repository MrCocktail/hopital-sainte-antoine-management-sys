package com.hsa.service;

import jakarta.ejb.Stateless;
@Stateless

public class HospitalServiceBean implements HospitalService {
    @Override
    public String getWelcomeMessage() {
        return "Welcome to the HSA's Management System!";
    }
}
