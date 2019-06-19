package com.lenovo.concurrent;


import lombok.Getter;

/**
 * *************************
 *
 * @author : Administrator
 * @className: CountryEnum
 * @data : 2019/6/19 15:24:43
 * **********************
 */
public enum CountryEnum {

    ONE(1,"齐"),TWO(2,"楚"),THREE(3,"燕"),FOUR(4,"赵"),FIVE(5,"魏"),SIX(6,"韩");

     @Getter private Integer retCode;
     @Getter private String  retMessage;


    CountryEnum(Integer retCode, String retMessage) {
        this.retCode = retCode;
        this.retMessage = retMessage;
    }

    public static CountryEnum forEach_CountryEnum(int index){
        CountryEnum[] enums = CountryEnum.values();
        for (CountryEnum elemt : enums) {
            if (index == elemt.getRetCode()){
                return elemt;
            }
        }
        return null;
    }

}
