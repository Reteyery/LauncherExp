package com.reteyery.launcherexp.test.entity;

/**
 * RxBusEntity
 * code 100
 */
public class BusEntity {
    int codeId;
    String desc;

    public BusEntity(int codeId, String desc) {
        this.codeId = codeId;
        this.desc = desc;
    }

    public int getCodeId() {
        return codeId;
    }

    public void setCodeId(int codeId) {
        this.codeId = codeId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
