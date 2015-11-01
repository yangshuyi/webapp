package com.echodrama.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.echodrama.dao.JobTitleDao;
import com.echodrama.enumtype.CodeEnum;
import com.echodrama.model.CodeModel;
import com.echodrama.model.JobTitle;
import com.echodrama.model.PaginationQueryModel;
import com.echodrama.pojo.CodePojo;
import com.echodrama.pojo.JobTitlePojo;
import com.echodrama.service.CacheSupport;
import com.echodrama.service.CodeService;
import com.echodrama.utility.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: yangsh
 * Date: 2/7/14
 * Time: 9:33 AM
 * To change this template use File | Settings | File Templates.
 */

@Service("codeService")
public class CodeServiceImpl implements CodeService, CacheSupport {
    //    @Autowired
    //        private MemcachedClient memcachedClient;

    @Autowired
    private JobTitleDao jobTitleDao;

    @Override
    public PaginationQueryModel generalQuery(CodeEnum codeEnum, PaginationQueryModel pQueryModel) throws Exception {
        PaginationQueryModel queryModel = null;
        switch (codeEnum) {
            case JobTitle:
                queryModel = jobTitleDao.generalQuery(pQueryModel);
                break;
            default:
                throw new Exception("Could not load CodeEnum[" + codeEnum + "]");
        }

        List<CodePojo> codePojoList = queryModel.getRows();
        List<CodeModel> codeModelList = new ArrayList<CodeModel>();
        for (CodePojo pojo : codePojoList) {
            codeModelList.add(CodeModel.fromPojo(pojo));
        }
        queryModel.setRows(codeModelList);
        return queryModel;
    }

    @Override
    public List<? extends CodeModel> listByType(CodeEnum codeEnum) throws Exception {
        List<CodeModel> codeModelList = null;

        List<? extends CodePojo> codePojoList = null;
        switch (codeEnum) {
            case JobTitle:
                codePojoList = jobTitleDao.listAll(JobTitlePojo.class);
                break;
            default:
                throw new Exception("Could not load CodeEnum[" + codeEnum + "]");
        }

        codeModelList = new ArrayList<CodeModel>();
        for (CodePojo pojo : codePojoList) {
            codeModelList.add(new CodeModel(pojo));
        }

        return codeModelList;
    }

    @Override
    public CodeModel loadCode(CodeEnum codeEnum, String id) throws Exception {
        switch (codeEnum) {
            case JobTitle:
                JobTitlePojo jobTitlePojo = jobTitleDao.get(JobTitlePojo.class, id);
                if (jobTitlePojo == null) {
                    throw new Exception("Could not find JobTitle Code by id [" + id + "]");
                }
                return JobTitle.fromPojo(jobTitlePojo);
            default:
                throw new Exception("Could not load CodeEnum[" + codeEnum + "]");
        }
    }

    @Override
    public CodeModel initNewCode(CodeEnum codeEnum) throws Exception {
        CodeModel codeModel = null;
        switch (codeEnum) {
            case JobTitle:
                codeModel = new JobTitle();
                codeModel.setName("New JobTitle");
                break;
            default:
                throw new Exception("Could not init CodeEnum[" + codeEnum + "]");
        }

        return codeModel;
    }

    @Override
    public void deleteCodes(CodeEnum codeEnum, String[] ids) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void saveCode(CodeEnum codeEnum, CodeModel codeModel) throws Exception {
        CodePojo codePojo = null;
        if (StringUtils.isEmpty(codeModel.getId())) {
            switch (codeEnum) {
                case JobTitle:
                    codePojo = new JobTitlePojo();
                    break;
            }
        } else {
            switch (codeEnum) {
                case JobTitle:
                    codePojo = jobTitleDao.get(JobTitlePojo.class, codeModel.getId());
                    break;
            }
        }

        if (codePojo == null) {
            throw new Exception("Could not find Code with id [" + codeModel.getId() + "]");
        }

        codePojo = codeModel.toPojo(codePojo);

        switch (codeEnum) {
            case JobTitle:
                jobTitleDao.save((JobTitlePojo) codePojo);
                break;
            default:
                throw new Exception("Could not save CodeEnum[" + codeEnum + "]");
        }

        clearCache(new String[]{Constants.XMEMCACHED_KEY_CODE_LIST + codeEnum.toString()});
    }

    @Override
    public void clearCache(String[] keys) throws Exception {
        //        for (String key : keys) {
        //            this.memcachedClient.delete(key);
        //        }
    }
}
