package com.echodrama.servlet;

import com.echodrama.service.DemoDataService;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class ApplicationInitServlet extends HttpServlet {

    /**
     * Servlet implementation
     */
    @Override
    public void init() throws ServletException {
        super.init();

        try {
            initDatabase();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    private void initDatabase() throws Exception {

        boolean needDemoData = true;
        if (needDemoData) {
            DemoDataService demoDataService = (DemoDataService) WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext()).getBean("demoDataService");
            demoDataService.createDemoData();
        }
    }
}
