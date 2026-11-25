import java.util.*;

abstract class SinhVien {
    final String ma, he;
    final int tinChi;
    static final long DON_GIA = 450_000L;
    SinhVien(String ma, String he, int tinChi) {
        this.ma = ma;
        this.he = he;
        this.tinChi = tinChi;
    }
    abstract long hocPhi();

    static SinhVien tao(String he, String ma, int tinChi) {
        switch (he) {
            case "CQ": return new ChinhQuy(ma, tinChi);
            case "CLC": return new ChatLuongCao(ma, tinChi);
            case "LT": return new LienThong(ma, tinChi);
            default: throw new IllegalArgumentException();
        }
    }
    public String toString() {
        return ma + " " + he + " " + hocPhi();
    }
}
class ChinhQuy extends SinhVien {
    ChinhQuy(String ma, int tinChi) {
        super(ma, "CQ", tinChi);
    }
    @Override
    long hocPhi() {
        return tinChi * DON_GIA;
    }
}

class ChatLuongCao extends SinhVien {
    ChatLuongCao(String ma, int tinChi) {
        super(ma, "CLC", tinChi);
    }
    @Override
    long hocPhi() {
        return tinChi * DON_GIA * 3 / 2;
    }
}

class LienThong extends SinhVien {
    LienThong(String ma, int tinChi) {
        super(ma, "LT", tinChi);
    }
    @Override
    long hocPhi() {
        return tinChi * DON_GIA + 2_000_000L;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.println(SinhVien.tao(sc.next(), sc.next(), sc.nextInt()));
        }
    }
}
