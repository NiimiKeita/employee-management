package dto;

public class Student {
    //学生ID
    private int id;
    
    //学生の名前
    private String name;

    //学生の点数
    private int score;


    //学生ID用のgetter/setter
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    //学生名前用getter/setter
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    //学生の点数用getter/setter
    public int getScore() {
        return this.score;
    }
    public void setScore(int score) {
        this.score = score;
    }
}