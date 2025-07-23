package employeeManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class FindByIdStudentScanner {
    public static void main(String[] args) {
        // MySQLデータベースの接続URL
        final String URL = "jdbc:mysql://localhost:3306/sampledb";
        // MySQLデータベースのユーザー名
        final String USER_NAME = "root";
        // ユーザーのパスワード
        final String USER_PASS = "pass";

        // 学生IDをscannerクラスを使用して指定
        Scanner scanner = new Scanner(System.in);
        System.out.print("検索する学生のIDを入力してください：");
        int id = scanner.nextInt();
        //studentテーブルからIDに合致するレコードを検索するSQL文
	String sql = "SELECT * FROM student WHERE delete_date IS NULL AND id = ?";
        
        // データベース接続、準備済みステートメント、および結果セットのプライベート変数を宣言する
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // データベースとの接続
            con = DriverManager.getConnection(URL, USER_NAME, USER_PASS);
            // SELECT文の準備
            ps = con.prepareStatement(sql);
            // パラメーターを設定
            ps.setInt(1, id);
            // SELECT文を実行して結果を取得
            rs = ps.executeQuery();

            // 検索結果をResultSetからレコードを取り出してコンソールに出力
            while (rs.next()) {
                System.out.print("id: " + rs.getInt("id") + ", ");
                System.out.print("name: " + rs.getString("name") + ", ");
                System.out.println("score: " + rs.getInt("score") + "点");
            }
        } catch (SQLException e) {
            // エラーが発生した場合はスタックトレースを出力
            e.printStackTrace();
        } finally {
        	//標準入力を閉じる
        	scanner.close(); 
            // リソースの開放
            // ResultSetのクローズ
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            // PreparedStatmentのクローズ
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            // Connectionのクローズ
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
