package topica.edu.model;

import java.io.Serializable;

public class Music implements Serializable {
    private int iD;
    private String tenBaiHat;
    private String tenTacGia;
    private boolean yeuThich;

    public Music(int iD, String tenBaiHat, String tenTacGia ,boolean yeuThich) {
        this.iD = iD;
        this.tenBaiHat = tenBaiHat;
        this.tenTacGia = tenTacGia;
        this.yeuThich = yeuThich;

    }

    public Music() {
    }

    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    public String getTenBaiHat() {
        return tenBaiHat;
    }

    public void setTenBaiHat(String tenBaiHat) {
        this.tenBaiHat = tenBaiHat;
    }

    public String getTenTacGia() {
        return tenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }

    public boolean isYeuThich() {
        return yeuThich;
    }

    public void setYeuThich(boolean yeuThich) {
        this.yeuThich = yeuThich;
    }

    public String toString(){
        return this.getiD()+"\t"+this.getTenBaiHat()+"\t"+this.getTenTacGia();
    }

}
