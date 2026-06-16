package com.hsa.service;

import jakarta.ejb.Local;
@Local
public interface HospitalService {
    String getWelcomeMessage();
}
