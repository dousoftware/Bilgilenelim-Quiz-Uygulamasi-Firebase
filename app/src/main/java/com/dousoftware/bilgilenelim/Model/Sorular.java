package com.dousoftware.bilgilenelim.Model;

public class Sorular {
    public String soru, seçenek1, seçenek2, seçenek3, seçenek4, cevap;

    public Sorular(String soru, String seçenek1, String seçenek2, String seçenek3, String seçenek4, String cevap) {
        this.soru = soru;
        this.seçenek1 = seçenek1;
        this.seçenek2 = seçenek2;
        this.seçenek3 = seçenek3;
        this.seçenek4 = seçenek4;
        this.cevap = cevap;
    }
    public Sorular(){

    }

    public String getSoru() {
        return soru;
    }

    public void setSoru(String soru) {
        this.soru = soru;
    }

    public String getSeçenek1() {
        return seçenek1;
    }

    public void setSeçenek1(String seçenek1) {
        this.seçenek1 = seçenek1;
    }

    public String getSeçenek2() {
        return seçenek2;
    }

    public void setSeçenek2(String seçenek2) {
        this.seçenek2 = seçenek2;
    }

    public String getSeçenek3() {
        return seçenek3;
    }

    public void setSeçenek3(String seçenek3) {
        this.seçenek3 = seçenek3;
    }

    public String getSeçenek4() {
        return seçenek4;
    }

    public void setSeçenek4(String seçenek4) {
        this.seçenek4 = seçenek4;
    }

    public String getCevap() {
        return cevap;
    }

    public void setCevap(String cevap) {
        this.cevap = cevap;
    }
}
