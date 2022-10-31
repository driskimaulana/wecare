package com.kelompok4.wecare.model;

public class PanduanKesehatanModel {
    String panduanTitle;
    String panduanDesc;
    int panduanImage;

    public PanduanKesehatanModel(String panduanTitle, String panduanDesc, int panduanImage) {
        this.panduanTitle = panduanTitle;
        this.panduanDesc = panduanDesc;
        this.panduanImage = panduanImage;
    }

    public String getPanduanTitle() {
        return panduanTitle;
    }

    public String getPanduanDesc() {
        return panduanDesc;
    }

    public int getPanduanImage() {
        return panduanImage;
    }
}
