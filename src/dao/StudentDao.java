package dao;

import java.util.List;

import dto.Student;

// DAOインターフェイス
public interface StudentDao {
    // すべての学生を検索するメソッド
    public List<Student> findAll();

    // IDを指定して学生を検索するメソッド
    public Student findById(int id);

    // 学生情報を保存するメソッド
    public boolean save(Student student);

    // 学生情報を更新するメソッド
    public boolean update(Student student);
    
    // IDを指定して学生を削除するメソッド
    public boolean delete(int id);
}