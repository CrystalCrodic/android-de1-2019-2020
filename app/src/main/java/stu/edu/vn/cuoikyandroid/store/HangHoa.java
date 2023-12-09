package stu.edu.vn.cuoikyandroid.store;

import java.io.Serializable;

public class HangHoa implements Serializable {
    private int ma, gia;
    private String ten;

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public HangHoa(int ma, int gia, String ten) {
        this.ma = ma;
        this.gia = gia;
        this.ten = ten;
    }
}
