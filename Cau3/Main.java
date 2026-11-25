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
class DuLieuKhongHopLeException extends Exception {
    DuLieuKhongHopLeException(String ma) {
        super(ma);
    }
}

class QuanLyHocPhi {
    private final ArrayList<SinhVien> danhSach = new ArrayList<>();
    private final Set<String> maDaNhap = new HashSet<>();

    void them(String he, String ma, String so) throws DuLieuKhongHopLeException {
        SinhVien sv;
        try {
            int tc = Integer.parseInt(so);
            sv = SinhVien.tao(he, ma, tc);
            if (tc < 1 || tc > 30 || maDaNhap.contains(ma)) {
                throw new DuLieuKhongHopLeException(ma);
            }
        } catch (IllegalArgumentException e) {
            throw new DuLieuKhongHopLeException(ma);
        }
        danhSach.add(sv);
        maDaNhap.add(ma);
    }
    void inKetQua() {
        danhSach.sort(Comparator.comparingLong(SinhVien::hocPhi).reversed()
                .thenComparing(sv -> sv.ma));
        long tong = 0;
        for (SinhVien sv : danhSach) {
            System.out.println(sv);
            tong += sv.hocPhi();
        }
        System.out.println("TONG " + tong);
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        QuanLyHocPhi quanLy = new QuanLyHocPhi();
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            try {
                quanLy.them(sc.next(), sc.next(), sc.next());
            } catch (DuLieuKhongHopLeException e) {
                System.out.println("INVALID " + e.getMessage());
            }
        }
        quanLy.inKetQua();
    }
}
