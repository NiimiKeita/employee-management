package employeeManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertStudent {
    public static void main(String[] args) {
        // MySQLデータベースの接続URL
        final String URL = "jdbc:mysql://localhost:3306/sampledb";
        // MySQLデータベースのユーザー名
        final String USER_NAME = "root";
        // ユーザーのパスワード
        final String USER_PASS = "pass";

        // 学生ID
        int id = 6;
        // 学生の名前
        String name = "田中";
        // 学生の点数
        int score = 45;
        
        // studentテーブルに新規レコードを追加するSQL文
        String sql = "INSERT INTO student (id, name, score) VALUES (?, ?, ?)";
        
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
       	    ps.setString(2, name);
       	    ps.setInt(3, score);
            
            // SELECT文を実行して結果を取得
            int count = ps.executeUpdate();
            // 追加した件数を表示
            System.out.println("【info】" + count + "件追加しました");
        } catch (SQLException e) {
            // エラーが発生した場合はスタックトレースを出力
            e.printStackTrace();
        } finally {
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
