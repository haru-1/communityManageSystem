package bean;

public class Association {
    private int as_id;
    private int stu_id;
    private String stu_name;
    private String as_name;
    private String as_level;
    private String as_introduce;
    private String as_number;
    private String mem_type;
    private String as_actnumber;

    public Association() {

    }

    public Association(int as_id,int stu_id, String stu_name, String as_name, String as_level, String as_introduce, String as_number,String mem_type,String as_actnumber) {
        this.as_id = as_id;
        this.stu_id = stu_id;
        this.stu_name = stu_name;
        this.as_name = as_name;
        this.as_level = as_level;
        this.as_introduce = as_introduce;
        this.as_number = as_number;
        this.mem_type = mem_type;
        this.as_actnumber = as_actnumber;
    }

    public int getAs_id() {
        return as_id;
    }

    public void setAs_id(int as_id) {
        this.as_id = as_id;
    }

    public String getAs_name() {
        return as_name;
    }

    public void setAs_name(String as_name) {
        this.as_name = as_name;
    }

    public String getAs_number() {
        return as_number;
    }

    public void setAs_number(String as_number) {
        this.as_number = as_number;
    }

    public String getAs_introduce() {
        return as_introduce;
    }

    public void setAs_introduce(String as_introduce) {
        this.as_introduce = as_introduce;
    }

    public String getAs_level() {
        return as_level;
    }

    public void setAs_level(String as_level) {
        this.as_level = as_level;
    }

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public String getMem_type() {
        return mem_type;
    }

    public void setMem_type(String mem_type) {
        this.mem_type = mem_type;
    }

    public String getAs_actnumber() {
        return as_actnumber;
    }

    public void setAs_actnumber(String as_actnumber) {
        this.as_actnumber = as_actnumber;
    }

    public int getStu_id() {
        return stu_id;
    }

    public void setStu_id(int stu_id) {
        this.stu_id = stu_id;
    }
}
