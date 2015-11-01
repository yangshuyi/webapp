package com.echodrama.service;

import com.echodrama.enumtype.CodeEnum;
import com.echodrama.model.CodeModel;
import com.echodrama.model.PaginationQueryModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CodeService {

    public PaginationQueryModel generalQuery(CodeEnum codeEnum, PaginationQueryModel pQueryModel) throws Exception;

    public List<? extends CodeModel> listByType(CodeEnum codeEnum) throws Exception;

    public <T extends CodeModel> T loadCode(CodeEnum codeEnum, String id) throws Exception;

    public CodeModel initNewCode(CodeEnum codeEnum) throws Exception;

    public void deleteCodes(CodeEnum codeEnum, String[] ids) throws Exception;

    public void saveCode(CodeEnum codeEnum, CodeModel codeModel) throws Exception;
}
