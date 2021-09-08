package com.nutriyummy.backend.services;

import com.nutriyummy.backend.domain.Complaint;
import com.nutriyummy.backend.infrastructure.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintServiceImp implements ComplaintService{

    @Autowired
    private ComplaintRepository complaintRepository ;

    @Override
    public void saveComplaint(Complaint complaint) {
        complaintRepository.save(complaint);
    }

    @Override
    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    @Override
    public Complaint getComplaint(Long id) {
        return complaintRepository.findById(id).orElseThrow();
    }
}
