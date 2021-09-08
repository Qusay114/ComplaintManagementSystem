package com.nutriyummy.backend.web;

import com.nutriyummy.backend.domain.Complaint;
import com.nutriyummy.backend.services.AppUserService;
import com.nutriyummy.backend.services.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService ;
    @Autowired
    private AppUserService appUserService ;

    @GetMapping("/complaints")
    public String getComplaintsPage(Model model , Principal principal){
        model.addAttribute("complaints" ,
                appUserService.getAppUser(principal.getName()).getComplaintList());
        model.addAttribute("role" , "ADMIN");

        return "complaints";
    }

    @PostMapping("/complaints")
    public RedirectView addNewComplaint(@RequestParam String content , Principal principal){
        Complaint complaint = new Complaint(content) ;
        complaint.setStatus("pending");
        complaint.setAppUser(appUserService.getAppUser(principal.getName()));
        complaintService.saveComplaint(complaint);
        return new RedirectView("/complaints") ;
    }

    @GetMapping("/clientscomplaints")
    public String getAllComplaints(Model model){
        model.addAttribute("complaints" , complaintService.getAllComplaints());
        return "clientscomplaints" ;
    }

    @PostMapping("clientscomplaints/status/{id}")
    public RedirectView changeStatus(@PathVariable Long id , @RequestParam String status){
        Complaint complaint = complaintService.getComplaint(id);
        complaint.setStatus(status);
        complaintService.saveComplaint(complaint);
        return new RedirectView("/clientscomplaints");
    }
}
