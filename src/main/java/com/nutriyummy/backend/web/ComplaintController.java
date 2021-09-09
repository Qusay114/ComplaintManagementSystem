package com.nutriyummy.backend.web;

import com.nutriyummy.backend.domain.Complaint;
import com.nutriyummy.backend.services.AppUserService;
import com.nutriyummy.backend.services.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.*;

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
        model.addAttribute("selected" , "all");
        Map<String , Integer> statusNums = getComplaintsStatusNumbers(complaintService.getAllComplaints());
        model.addAttribute("pending" , statusNums.get("pending"));
        model.addAttribute("resolution" , statusNums.get("resolution"));
        model.addAttribute("dismissed" , statusNums.get("dismissed"));
        return "clientscomplaints" ;
    }

    @PostMapping("/clientscomplaints/status/{id}")
    public RedirectView changeStatus(@PathVariable Long id , @RequestParam String status){
        Complaint complaint = complaintService.getComplaint(id);
        complaint.setStatus(status);
        complaintService.saveComplaint(complaint);
        return new RedirectView("/clientscomplaints");
    }

    @PostMapping("/clientscomplaints")
    public String showSpecificComplaints(@RequestParam String status , Model model){
        Map<String , Integer> statusNums = getComplaintsStatusNumbers(complaintService.getAllComplaints());
        model.addAttribute("pending" , statusNums.get("pending"));
        model.addAttribute("resolution" , statusNums.get("resolution"));
        model.addAttribute("dismissed" , statusNums.get("dismissed"));

        if (status.equals("all"))
        {
            model.addAttribute("complaints" , complaintService.getAllComplaints());
            model.addAttribute("selected" , status);
            return "clientscomplaints" ;
        }
        List<Complaint> complaintList = new ArrayList<>();
        for (Complaint complaint : complaintService.getAllComplaints())
            if (complaint.getStatus().equals(status))
                complaintList.add(complaint);

        model.addAttribute("complaints" , complaintList);
        model.addAttribute("selected" , status);
        return "clientscomplaints" ;
    }


    @GetMapping("/clientscomplaints/statics")
    public String getStaticsPage(Model model){
        Map<String , Integer> statusNums = getComplaintsStatusNumbers(complaintService.getAllComplaints());
        model.addAttribute("pending" , statusNums.get("pending"));
        model.addAttribute("resolution" , statusNums.get("resolution"));
        model.addAttribute("dismissed" , statusNums.get("dismissed"));
        return "statics" ;
    }

    private Map< String, Integer> getComplaintsStatusNumbers(List<Complaint> complaintList){
        int pendings = 0 ;
        int resolutions = 0 ;
        int dismissed = 0 ;
        for (Complaint complaint : complaintList)
        {
            if (complaint.getStatus().equals("pending"))
                pendings++;
            if (complaint.getStatus().equals("resolution"))
                resolutions++;
            if (complaint.getStatus().equals("dismissed"))
                dismissed++;
        }
        Map< String , Integer> nums = new HashMap<>();
        nums.put( "pending", pendings);
        nums.put( "resolution", resolutions);
        nums.put("dismissed", dismissed);
        return nums ;
    }
}
