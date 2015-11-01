package com.echodrama.model;


import com.echodrama.pojo.CodePojo;
import com.echodrama.utility.StringUtility;

public class CodeModel extends BaseModel {

    private String name;

    private String code;

    private String description;


    public CodeModel() {
        super();
    }

    public CodeModel(CodePojo pojo) {
        super(pojo);

        if (pojo == null) {
            return;
        }

        this.name = pojo.getName();
        this.code = pojo.getCode();
        this.description = pojo.getDescription();
    }

    public static CodeModel fromPojo(CodePojo pojo) {
        CodeModel model = new CodeModel(pojo);

        return model;
    }

    public CodePojo toPojo(CodePojo pojo) {
        super.toPojo(pojo);

        if (StringUtility.isValueChanged(name, pojo.getName())) {
            pojo.setName(name);
        }

        if (StringUtility.isValueChanged(code, pojo.getCode())) {
            pojo.setCode(code);
        }

        if (StringUtility.isValueChanged(description, pojo.getDescription())) {
            pojo.setDescription(description);
        }

        return pojo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
