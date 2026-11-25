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
    MienGiam chinhSach;

    long tienGiam() {
        return chinhSach == null ? 0 : chinhSach.tinhGiam(hocPhi());
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
interface MienGiam {
    long tinhGiam(long hocPhi);
}
class ConChinhSach implements MienGiam {
    @Override
    public long tinhGiam(long hocPhi) {
        return hocPhi / 2;
    }
}

class HoNgheo implements MienGiam {
    @Override
    public long tinhGiam(long hocPhi) {
        return hocPhi * 7 / 10;
    }
}

class HocBong implements MienGiam {
    @Override
    public long tinhGiam(long hocPhi) {
        return Math.min(hocPhi, 3_000_000L);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, SinhVien> danhSach = new LinkedHashMap<>();
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            SinhVien sv = SinhVien.tao(sc.next(), sc.next(), sc.nextInt());
            danhSach.put(sv.ma, sv);
        }
        Map<String, MienGiam> chinhSach = new HashMap<>();
        chinhSach.put("CS", new ConChinhSach());
        chinhSach.put("HN", new HoNgheo());
        chinhSach.put("HB", new HocBong());
        int m = sc.nextInt();
        for (int i = 0; i < m; i++) {
            String ma = sc.next();
            String cs = sc.next();
            SinhVien sv = danhSach.get(ma);
            if (sv == null) {
                System.out.println("NOT FOUND " + ma);
            } else {
                sv.chinhSach = chinhSach.get(cs);
            }
        }
        Map<String, Integer> soLuong = new HashMap<>();
        Map<String, Long> tong = new HashMap<>();
        for (SinhVien sv : danhSach.values()) {
            long giam = sv.tienGiam();
            long phaiNop = sv.hocPhi() - giam;
            System.out.println(sv + " " + giam + " " + phaiNop);
            soLuong.merge(sv.he, 1, Integer::sum);
            tong.merge(sv.he, phaiNop, Long::sum);
        }
        for (String he : new String[]{"CQ", "CLC", "LT"}) {
            if (soLuong.containsKey(he)) {
                System.out.println(he + " " + soLuong.get(he) + " " + tong.get(he));
            }
        }
    }
}
