package com.example.insideseoul.OpenAPI;

/**
 * USE_DT: 사용일자
 * LINE_NUM: 호선명
 * SUB_STA_NM: 역명
 * RIDE_PASGR_NUM: 승차총승객수
 * ALIGHT_PASGR_NUM: 하차총승객수
 * WORK_DT: 등록일자
 */
public class MetroEntity {
    String USE_DT;
    String LINE_NUM;
    String SUB_STA_NM;
    double RIDE_PASGR_NUM;
    double ALIGHT_PASGR_NUM;
    String WORK_DT;

    public String getUSE_DT() {
        return USE_DT;
    }

    public void setUSE_DT(String USE_DT) {
        this.USE_DT = USE_DT;
    }

    public String getLINE_NUM() {
        return LINE_NUM;
    }

    public void setLINE_NUM(String LINE_NUM) {
        this.LINE_NUM = LINE_NUM;
    }

    public String getSUB_STA_NM() {
        return SUB_STA_NM;
    }

    public void setSUB_STA_NM(String SUB_STA_NM) {
        this.SUB_STA_NM = SUB_STA_NM;
    }

    public double getRIDE_PASGR_NUM() {
        return RIDE_PASGR_NUM;
    }

    public void setRIDE_PASGR_NUM(double RIDE_PASGR_NUM) {
        this.RIDE_PASGR_NUM = RIDE_PASGR_NUM;
    }

    public double getALIGHT_PASGR_NUM() {
        return ALIGHT_PASGR_NUM;
    }

    public void setALIGHT_PASGR_NUM(double ALIGHT_PASGR_NUM) {
        this.ALIGHT_PASGR_NUM = ALIGHT_PASGR_NUM;
    }

    public String getWORK_DT() {
        return WORK_DT;
    }

    public void setWORK_DT(String WORK_DT) {
        this.WORK_DT = WORK_DT;
    }

    @Override
    public String toString() {
        return "MetroEntity{" +
                "USE_DT='" + USE_DT + '\'' +
                ", LINE_NUM='" + LINE_NUM + '\'' +
                ", SUB_STA_NM='" + SUB_STA_NM + '\'' +
                ", RIDE_PASGR_NUM=" + RIDE_PASGR_NUM +
                ", ALIGHT_PASGR_NUM=" + ALIGHT_PASGR_NUM +
                ", WORK_DT='" + WORK_DT + '\'' +
                '}';
    }
}
