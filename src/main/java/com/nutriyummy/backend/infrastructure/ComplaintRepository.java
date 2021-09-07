package com.nutriyummy.backend.infrastructure;

import com.nutriyummy.backend.domain.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintRepository extends JpaRepository<Long , Complaint> {
}
