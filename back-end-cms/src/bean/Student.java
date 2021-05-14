package bean;

public class Student {
    private int stu_id;
    private String stu_name;
    private String stu_password;
    private String stu_sex;
    private String stu_major;
    private String stu_grade;
    private String stu_college;
    private String stu_tel;

    public Student() {
    }

    public Student(int stu_id, String stu_name, String stu_password, String stu_sex, String stu_major, String stu_grade, String stu_college, String stu_tel) {
        this.stu_id = stu_id;
        this.stu_name = stu_name;
        this.stu_password = stu_password;
        this.stu_sex = stu_sex;
        this.stu_major = stu_major;
        this.stu_grade = stu_grade;
        this.stu_college = stu_college;
        this.stu_tel = stu_tel;
    }

    public int getStu_id() {
        return stu_id;
    }

    public void setStu_id(int stu_id) {
        this.stu_id = stu_id;
    }

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public String getStu_password() {
        return stu_password;
    }

    public void setStu_password(String stu_password) {
        this.stu_password = stu_password;
    }

    public String getStu_sex() {
        return stu_sex;
    }

    public void setStu_sex(String stu_sex) {
        this.stu_sex = stu_sex;
    }

    public String getStu_major() {
        return stu_major;
    }

    public void setStu_major(String stu_major) {
        this.stu_major = stu_major;
    }

    public String getStu_grade() {
        return stu_grade;
    }

    public void setStu_grade(String stu_grade) {
        this.stu_grade = stu_grade;
    }

    public String getStu_college() {
        return stu_college;
    }

    public void setStu_college(String stu_college) {
        this.stu_college = stu_college;
    }

    public String getStu_tel() {
        return stu_tel;
    }

    public void setStu_tel(String stu_tel) {
        this.stu_tel = stu_tel;
    }
}