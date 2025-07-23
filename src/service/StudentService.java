package service;

import java.util.List;

import dao.StudentDao;
import dao.StudentDaoImpl;
import dto.Student;

public class StudentService {
    // StudentDaoオブジェクトを格納するフィールド
    private StudentDao studentDao;
    
    // StudentDaoImplオブジェクトをインスタンス化し、StudentDaoフィールドに格納するコンストラクタ
    public StudentService() {
        this.studentDao = new StudentDaoImpl();
    }

    // すべての学生情報を検索して、検索結果を表示するメソッド
    public void findAll() {
        System.out.println("------------------------------------------------------");
        System.out.println("【全件検索】");

        // すべての学生情報を検索して、結果をStudentオブジェクト型のリストへ格納
        List<Student> students = studentDao.findAll();
        // 検索結果を1件ずつ表示
        for (Student student : students) {
            System.out.print("ID: " + student.getId() + ", ");
            System.out.print("名前: " + student.getName() + "さん, ");
            System.out.println("点数: " + student.getScore() + "点");
        }
        System.out.println("------------------------------------------------------");
    }

    // IDに合致する学生情報を検索して、検索結果を表示するメソッド
    public void findById(int id) {
        System.out.println("------------------------------------------------------");
        System.out.println("【ID検索】");

        // IDに合致する学生情報を検索して、結果をStudentオブジェクト型へ格納
        Student student = studentDao.findById(id);
        // 検索結果を表示
        System.out.print("ID: " + student.getId() + ", ");
        System.out.print("名前: " + student.getName() + "さん, ");
        System.out.println("点数: " + student.getScore() + "点");
        System.out.println("------------------------------------------------------");
    }

    // 学生情報を登録するメソッド(実行結果を表示する)
    public void save(int id, String name, int score) {
        System.out.println("------------------------------------------------------");
        System.out.println("【新規登録】");

        // Studentオブジェクトを作成、ID, Name, Scoreをセットする
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setScore(score);

        // オブジェクトの情報を保存、実行結果を受け取る
        boolean result = studentDao.save(student);

        // 実行結果の表示
        if (result) {
            System.out.println("登録しました。");
        } else {
            System.out.println("登録に失敗しました。");
        }
        System.out.println("------------------------------------------------------");
    }

    // 学生情報を更新するメソッド(実行結果を表示する)
    public void update(int id, int score) {
        System.out.println("------------------------------------------------------");
        System.out.println("【更新】");

        // Studentオブジェクトを作成、ID, Scoreをセットする
        Student student = new Student();
        student.setId(id);
        student.setScore(score);

        // オブジェクトの情報で更新、実行結果を受け取る
        boolean result = studentDao.update(student);

        // 実行結果の表示
        if (result) {
            System.out.println("更新しました。");
        } else {
            System.out.println("更新に失敗しました。");
        }
        System.out.println("------------------------------------------------------");
    }

    // 学生情報を削除するメソッド(実行結果を表示する)
    public void delete(int id) {
        System.out.println("------------------------------------------------------");
        System.out.println("【削除】");

        // ID情報で削除、実行結果を受け取る
        boolean result = studentDao.delete(id);

        // 実行結果の表示
        if (result) {
            System.out.println("削除しました。");
        } else {
            System.out.println("削除に失敗しました。");
        }
        System.out.println("------------------------------------------------------");
    }
}