package com.echodrama.enumtype;

/**
 * Created by shuyi on 15/12/11.
 */
public enum TopicTypeEnum {
    BL(1), GL(2);

    private int key;

    TopicTypeEnum(int key){
         this.key = key;
    }
}
