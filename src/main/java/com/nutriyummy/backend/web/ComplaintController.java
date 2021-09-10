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

    //this will get the complaints page for the user
    @GetMapping("/complaints")
    public String getComplaintsPage(Model model , Principal principal){
        model.addAttribute("complaints" ,
                appUserService.getAppUser(principal.getName()).getComplaintList());

        return "complaints";
    }

    //to let the user add complaints
    @PostMapping("/complaints")
    public RedirectView addNewComplaint(@RequestParam String content , Principal principal){
        Complaint complaint = new Complaint(content) ;
        complaint.setStatus("pending");
        complaint.setAppUser(appUserService.getAppUser(principal.getName()));
        complaintService.saveComplaint(complaint);
        return new RedirectView("/complaints") ;
    }

    //this will get the complaints page for the admin
    @GetMapping("/clientscomplaints")
    public String getAllComplaints(Model model){
        model.addAttribute("complaints" , complaintService.getAllComplaints());
        //this will be for the selected status , where in this case the page will show all the complaints
        model.addAttribute("selected" , "all");
        return "clientscomplaints" ;
    }

    // to let the admin change the status of the complaint
    @PostMapping("/clientscomplaints/status/{id}")
    public RedirectView changeStatus(@PathVariable Long id , @RequestParam String status){
        Complaint complaint = complaintService.getComplaint(id);
        complaint.setStatus(status);
        complaintService.saveComplaint(complaint);
        return new RedirectView("/clientscomplaints");
    }

    //in case the admin wants to show complaints with a specific status
    @PostMapping("/clientscomplaints")
    public String showSpecificComplaints(@RequestParam String status , Model model){

        //in case selected all the complaints , so it will show all the complaints and there is no need to loop
        //over them and check the status
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


    //to get the statics chart of the complaints
    @GetMapping("/clientscomplaints/statics")
    public String getStaticsPage(Model model){
        Map<String , Integer> statusNums = getComplaintsStatusNumbers(complaintService.getAllComplaints());
        //pass the statics , to use them to show the chart statics
        model.addAttribute("pending" , statusNums.get("pending"));
        model.addAttribute("resolution" , statusNums.get("resolution"));
        model.addAttribute("dismissed" , statusNums.get("dismissed"));
        return "statics" ;
    }

    /**
     * this function will return the number for each complaint status
     * @param complaintList
     * @return hashmap , that has the status as a key  , and the number of the this complaint as a vaue
     */
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
