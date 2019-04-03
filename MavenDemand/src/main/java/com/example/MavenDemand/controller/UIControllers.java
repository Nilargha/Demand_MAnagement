/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.MavenDemand.controller;

/**
 *
 * @author Neel
 */


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UIControllers {
    @RequestMapping("/login")
    public String homePage(Model model) {
        return "DemandLogin";
    }
    @RequestMapping("/dashboard")
    public String dashboard(Model model) {
        return "dashboard";
    }
    
    @RequestMapping("/approverdashboard")
    public String approverdashboard(Model model) {
        return "ApproverDashboard";
    }
    
    
    @RequestMapping("/showdemands")
    public String showdemand(Model model) {
        return "ShowMyDemands";
    }
    
    @RequestMapping("/showeverydemands")
    public String showeverydemand(Model model) {
        return "ShowAllDemands";
    }
    
    @RequestMapping("/demandapproval")
    public String approvedemand(Model model) {
        return "DemandApproval";
    }
    
    @RequestMapping("/tpdldasboard")
    public String dashboardtpdl(Model model) {
        return "TPDLDashboard";
    }

    @RequestMapping("/ccoedqdemands")
    public String demandsdqccoe(Model model) {
        return "CcoeDQDemands";
    }
    
    @RequestMapping("/demandapproved")
    public String approveddemand(Model model) {
        return "DemandApproved";
    }
    
    @RequestMapping("/tpdldqdemands")
    public String demandsdqtpdl(Model model) {
        return "TpdlDQDemands";
    }


}
