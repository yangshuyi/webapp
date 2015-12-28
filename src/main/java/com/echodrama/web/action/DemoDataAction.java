package com.echodrama.web.action;

import com.echodrama.service.DemoDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoDataAction extends BaseAction {

    @Autowired
    private DemoDataService demoDataService;


    @RequestMapping("createDemoData")
    public void createDemoData() {
//        try {
//            demoDataService.createDemoData();
//
//            super.writeJson(true);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            super.writeJsonErrorMessage(ex);
//        }
    }


}
