import java.util.*;

class SinhVien {
    final String ma, ten, ngaySinh;
    SinhVien(String ma, String hoTen, String ngaySinh) {
        this.ma = ma.trim();
        StringJoiner tenChuan = new StringJoiner(" ");
        for (String tu : hoTen.trim().toLowerCase(Locale.ROOT).split("\\s+")) {
            tenChuan.add(tu.substring(0, 1).toUpperCase(Locale.ROOT) + tu.substring(1));
        }
        ten = tenChuan.toString();
        String[] p = ngaySinh.trim().split("/");
        this.ngaySinh = String.format(Locale.ROOT, "%02d/%02d/%s",
                Integer.parseInt(p[0]), Integer.parseInt(p[1]), p[2]);
    }
    public String toString() {
        return ma + " " + ten + " " + ngaySinh;
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        for (int i = 0; i < n; i++) {
            System.out.println(new SinhVien(sc.nextLine(), sc.nextLine(), sc.nextLine()));
        }
    }
}
