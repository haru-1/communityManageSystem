package bean;

public class ApplyMessage {
    private int ap_id;
    private String as_name;
    private int stu_id;
    private String stu_name;
    private String ap_content;
    private String ap_state;
    private String ap_applyDate;
    private String ap_passDate;

    public ApplyMessage() {
    }

    public ApplyMessage(int ap_id,String as_name, int stu_id, String stu_name,String ap_content, String ap_state, String ap_applyDate, String ap_passDate) {
        this.ap_id = ap_id;
        this.as_name = as_name;
        this.stu_id = stu_id;
        this.stu_name = stu_name;
        this.ap_content = ap_content;
        this.ap_state = ap_state;
        this.ap_applyDate = ap_applyDate;
        this.ap_passDate = ap_passDate;
    }

    public String getAs_name() {
        return as_name;
    }

    public void setAs_name(String as_name) {
        this.as_name = as_name;
    }

    public int getStu_id() {
        return stu_id;
    }

    public void setStu_id(int stu_id) {
        this.stu_id = stu_id;
    }

    public String getAp_content() {
        return ap_content;
    }

    public void setAp_content(String ap_content) {
        this.ap_content = ap_content;
    }

    public String getAp_state() {
        return ap_state;
    }

    public void setAp_state(String ap_state) {
        this.ap_state = ap_state;
    }

    public String getAp_applyDate() {
        return ap_applyDate;
    }

    public void setAp_applyDate(String ap_applyDate) {
        this.ap_applyDate = ap_applyDate;
    }

    public String getAp_passDate() {
        return ap_passDate;
    }

    public void setAp_passDate(String ap_passDate) {
        this.ap_passDate = ap_passDate;
    }

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public int getAp_id() {
        return ap_id;
    }

    public void setAp_id(int ap_id) {
        this.ap_id = ap_id;
    }
}
