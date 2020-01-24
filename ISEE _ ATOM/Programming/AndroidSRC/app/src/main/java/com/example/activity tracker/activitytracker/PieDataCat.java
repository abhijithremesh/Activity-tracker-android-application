package com.example.manish.activitytracker;

public class PieDataCat {
    String cat;
    float dur;

    public PieDataCat(String cat, float dur) {
        this.cat = cat;
        this.dur = dur;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public float getDur() {
        return dur;
    }

    public void setDur(float dur) {
        this.dur = dur;
    }
}
