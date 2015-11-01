package com.echodrama.action;

import com.echodrama.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CodeAction extends BaseAction {
    @Autowired
    private CodeService codeService;

    @RequestMapping("queryCodeDataGrid")
    public void queryCodeDataGrid(@RequestParam Object codeForm) {
//        Map<String, Object> dataGridMap = new HashMap<String, Object>();
//        try {
//            CodeEnum codeEnum = CodeEnum.valueOf(codeForm.getType());
//            PaginationQueryModel queryModel = codeForm.toQueryModel();
//            queryModel = codeService.generalQuery(codeEnum, queryModel);
//
//            dataGridMap.put("codeDataGrid", queryModel);
//
//            super.writeJson(dataGridMap);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            super.writeJsonErrorMessage(ex);
//        }
    }

    @RequestMapping("listCodeByTypes")
    public void listByTypes() {
//        Map<String, Object> dataGridMap = new HashMap<String, Object>();
//        try {
//            Map<CodeEnum, List> codeModelListMap = new HashMap<CodeEnum, List>();
//            for (String codeType : codeForm.getTypes()) {
//                CodeEnum codeEnum = CodeEnum.valueOf(codeType);
//                List<? extends CodeModel> codeModelList = codeService.listByType(codeEnum);
//                codeModelListMap.put(codeEnum, codeModelList);
//            }
//
//            dataGridMap.put("codeModelListMap", codeModelListMap);
//
//            super.writeJson(dataGridMap);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            super.writeJsonErrorMessage(ex);
//        }
    }

    @RequestMapping("listCodeByType")
    public void listByType(@RequestParam Object codeForm) {
//        Map<String, Object> dataGridMap = new HashMap<String, Object>();
//        try {
//            CodeEnum codeEnum = CodeEnum.valueOf(codeForm.getType());
//            List<? extends CodeModel> codeModelList = codeService.listByType(codeEnum);
//            dataGridMap.put("codeModelList", codeModelList);
//            super.writeJson(dataGridMap);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            super.writeJsonErrorMessage(ex);
//        }
    }

    public CodeService getCodeService() {
        return codeService;
    }

    public void setCodeService(CodeService codeService) {
        this.codeService = codeService;
    }
}
