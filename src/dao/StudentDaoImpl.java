package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Student;

// StudentDaoの実装クラス
public class StudentDaoImpl implements StudentDao {
    // データベースのURL
    private final String URL = "jdbc:mysql://localhost:3306/sampledb";
    //データベースのユーザー名
    private final String USER_NAME = "root";
    //ユーザーのパスワード
    private final String USER_PASS = "pass";
    
    //データベース接続、準備済みステートメント、および結果セットのプライベート変数を宣言する
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    /**
     * studentテーブルからすべてのレコードを検索します
     * @return Studentオブジェクトのリスト
     */
    @Override
    public List<Student> findAll() {
        // 検索結果を格納するリスト変数
        List<Student> students = new ArrayList<>();
        // studentテーブルからすべてのレコードを検索するSQL文
        String sql = "SELECT * FROM student";

        try {
            // データベースとの接続
            con = DriverManager.getConnection(URL, USER_NAME, USER_PASS);
            // SELECT文の準備
            ps = con.prepareStatement(sql);
            // SELECT文を実行して結果を取得
            rs = ps.executeQuery();

            // 検索結果を1レコードずつ取り出してリストに追加する
            while(rs.next()) {
                // 1レコード分のStudentオブジェクトのインスタンスを生成
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setScore(rs.getInt("score"));
                students.add(student);
            }
        } catch (SQLException e) {
            // 例外が発生した場合にスタックトレースを出力
            e.printStackTrace();
        } finally {
            // リソースを閉じる
            // ResultSetの接続を解除
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
			
            // PreparedStatementの接続を解除
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            // データベース接続の解除
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
		
        // 結果を返す
        return students;
    }

    /**
     * studentテーブルからIDに合致するレコードを検索します
     * @return Studentオブジェクト
     */
    @Override
    public Student findById(int id) {
        // 検索結果を格納する変数
        Student student = null;
        // studentテーブルからIDに合致するレコードを検索するSQL文
        String sql = "SELECT * FROM student WHERE id = ?";

        try {
            // データベースとの接続
            con = DriverManager.getConnection(URL, USER_NAME, USER_PASS);
            // SELECT文の準備
            ps = con.prepareStatement(sql);
            // SELECT文のパラメーター(プレースホルダー)に値を設定
            ps.setInt(1, id);
            // SELECT文を実行して結果を取得
            rs = ps.executeQuery();

            // 結果セットからレコードを1件ずつ読み込む
            while (rs.next()) {
                // 1レコード分のStudentオブジェクトのインスタンスを生成
                student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setScore(rs.getInt("score"));
            }
        } catch (SQLException e) {
            // 例外が発生した場合にスタックトレースを出力
            e.printStackTrace();
        } finally {
            // リソースを閉じる
            // ResultSetの接続を解除
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            // PreparedStatementの接続を解除
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            // データベース接続の解除
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        // 結果を返す
        return student;
    }

    /**
     * studentテーブルにデータを追加する
     * @return boolean
     */
    @Override
    public boolean save(Student student) {
        // 保存が成功したかどうかを示す変数
        boolean result = true;
        // 学生情報を新規追加するSQL文
        String sql = "INSERT INTO student (id, name, score) VALUES (?, ?, ?)";

        try {
            // データベースとの接続
            con = DriverManager.getConnection(URL, USER_NAME, USER_PASS);
            // SELECT文の準備
            ps = con.prepareStatement(sql);
            // SELECT文のパラメーター(プレースホルダー)に値を設定
            ps.setInt(1, student.getId());
            ps.setString(2, student.getName());
            ps.setInt(3, student.getScore());
            // SELECT文を実行して、データベースに追加
            ps.executeUpdate();
        } catch (SQLException e) {
            // エラーが発生した場合、resultにfalseを設定
            result = false;
            // 例外が発生した場合にスタックトレースを出力
            e.printStackTrace();
        } finally {
            // リソースを閉じる
            // PreparedStatementの接続を解除
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            // データベース接続の解除
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        // 結果を返す
        return result;
    }

    /**
     * studentテーブルにデータを更新する
     * @return boolean
     */
    @Override
    public boolean update(Student student) {
        // 保存が成功したかどうかを示す変数
        boolean result = true;
        // 学生情報を更新するSQL文
        String sql = "UPDATE student SET score = ? WHERE id = ?";

        try {
            // データベースとの接続
            con = DriverManager.getConnection(URL, USER_NAME, USER_PASS);
            // SELECT文の準備
            ps = con.prepareStatement(sql);
            // SELECT文のパラメーター(プレースホルダー)に値を設定
            ps.setInt(1, student.getScore());
            ps.setInt(2, student.getId());
            // SELECT文を実行して、データベースを更新
            ps.executeUpdate();
        } catch (SQLException e) {
            // エラーが発生した場合、resultにfalseを設定
            result = false;
            // 例外が発生した場合にスタックトレースを出力
            e.printStackTrace();
        } finally {
            // リソースを閉じる
            // PreparedStatementの接続を解除
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            // データベース接続の解除
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        // 結果を返す
        return result;
    }

    /**
     * studentテーブルにデータを削除する
     * @return boolean
     */
    @Override
    public boolean delete(int id) {
        // 保存が成功したかどうかを示す変数
        boolean result = true;
        // IDに合致する学生情報を削除するSQL文
        String sql = "DELETE FROM student WHERE id = ?";
        
        try {
            // データベースとの接続
            con = DriverManager.getConnection(URL, USER_NAME, USER_PASS);
            // SELECT文の準備
            ps = con.prepareStatement(sql);
            // SELECT文のパラメーター(プレースホルダー)に値を設定
            ps.setInt(1, id);
            // SELECT文を実行して、データベースから削除
            ps.executeUpdate();
        } catch (SQLException e) {
            // エラーが発生した場合、resultにfalseを設定
            result = false;
            // 例外が発生した場合にスタックトレースを出力
            e.printStackTrace();
        } finally {
            // リソースを閉じる
            // PreparedStatementの接続を解除
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            // データベース接続の解除
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        // 結果を返す
        return result;
    }
}