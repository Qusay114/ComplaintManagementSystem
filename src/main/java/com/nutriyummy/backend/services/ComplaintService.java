package com.nutriyummy.backend.services;

import com.nutriyummy.backend.domain.Complaint;

import java.util.List;

public interface ComplaintService {
    void saveComplaint(Complaint complaint);

    List<Complaint> getAllComplaints();

    Complaint getComplaint(Long id);
}
