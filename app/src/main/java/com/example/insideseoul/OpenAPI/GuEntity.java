package com.example.insideseoul.OpenAPI;

/**
 * GIGAN: 기간
 * JACHIGU: 자치구
 * SEDAE: 세대
 * GYE_1: 한국인 + 등록 외국인 총합
 * NAMJA_1: 한국인 + 등록 외국인 남자 총합
 * YEOJA_1: 한국인 + 등록 외국인 여자 총합
 * GYE_2: 한국인 총합
 * NAMJA_2: 한국인 남자 총합
 * YEOJA_2: 한국인 여자 총합
 * GYE_3: 등록 외국인 총합
 * NAMJA_3: 등록 외국인 남자 총합
 * YEOJA_3: 등록 외국인 여자 총합
 * SEDAEDANGINGU: 세대당 인구
 * N_65SEISANGGORYEONGJA: 65세 이상 고령자 수
 */
public class GuEntity {
    String GIGAN;
    String JACHIGU;
    int SEDAE;
    int GYE_1;
    int NAMJA_1;
    int YEOJA_1;
    int GYE_2;
    int NAMJA_2;
    int YEOJA_2;
    int GYE_3;
    int NAMJA_3;
    int YEOJA_3;
    String SEDAEDANGINGU;
    int N_65SEISANGGORYEONGJA;

    public String getGIGAN() {
        return GIGAN;
    }

    public void setGIGAN(String GIGAN) {
        this.GIGAN = GIGAN;
    }

    public String getJACHIGU() {
        return JACHIGU;
    }

    public void setJACHIGU(String JACHIGU) {
        this.JACHIGU = JACHIGU;
    }

    public int getSEDAE() {
        return SEDAE;
    }

    public void setSEDAE(int SEDAE) {
        this.SEDAE = SEDAE;
    }

    public int getGYE_1() {
        return GYE_1;
    }

    public void setGYE_1(int GYE_1) {
        this.GYE_1 = GYE_1;
    }

    public int getNAMJA_1() {
        return NAMJA_1;
    }

    public void setNAMJA_1(int NAMJA_1) {
        this.NAMJA_1 = NAMJA_1;
    }

    public int getYEOJA_1() {
        return YEOJA_1;
    }

    public void setYEOJA_1(int YEOJA_1) {
        this.YEOJA_1 = YEOJA_1;
    }

    public int getGYE_2() {
        return GYE_2;
    }

    public void setGYE_2(int GYE_2) {
        this.GYE_2 = GYE_2;
    }

    public int getNAMJA_2() {
        return NAMJA_2;
    }

    public void setNAMJA_2(int NAMJA_2) {
        this.NAMJA_2 = NAMJA_2;
    }

    public int getYEOJA_2() {
        return YEOJA_2;
    }

    public void setYEOJA_2(int YEOJA_2) {
        this.YEOJA_2 = YEOJA_2;
    }

    public int getGYE_3() {
        return GYE_3;
    }

    public void setGYE_3(int GYE_3) {
        this.GYE_3 = GYE_3;
    }

    public int getNAMJA_3() {
        return NAMJA_3;
    }

    public void setNAMJA_3(int NAMJA_3) {
        this.NAMJA_3 = NAMJA_3;
    }

    public int getYEOJA_3() {
        return YEOJA_3;
    }

    public void setYEOJA_3(int YEOJA_3) {
        this.YEOJA_3 = YEOJA_3;
    }

    public String getSEDAEDANGINGU() {
        return SEDAEDANGINGU;
    }

    public void setSEDAEDANGINGU(String SEDAEDANGINGU) {
        this.SEDAEDANGINGU = SEDAEDANGINGU;
    }

    public int getN_65SEISANGGORYEONGJA() {
        return N_65SEISANGGORYEONGJA;
    }

    public void setN_65SEISANGGORYEONGJA(int n_65SEISANGGORYEONGJA) {
        N_65SEISANGGORYEONGJA = n_65SEISANGGORYEONGJA;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "GIGAN='" + GIGAN + '\'' +
                ", JACHIGU='" + JACHIGU + '\'' +
                ", SEDAE=" + SEDAE +
                ", GYE_1=" + GYE_1 +
                ", NAMJA_1=" + NAMJA_1 +
                ", YEOJA_1=" + YEOJA_1 +
                ", GYE_2=" + GYE_2 +
                ", NAMJA_2=" + NAMJA_2 +
                ", YEOJA_2=" + YEOJA_2 +
                ", GYE_3=" + GYE_3 +
                ", NAMJA_3=" + NAMJA_3 +
                ", YEOJA_3=" + YEOJA_3 +
                ", SEDAEDANGINGU='" + SEDAEDANGINGU + '\'' +
                ", N_65SEISANGGORYEONGJA=" + N_65SEISANGGORYEONGJA +
                '}';
    }
}
