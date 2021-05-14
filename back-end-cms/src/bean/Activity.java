package bean;

public class Activity {
    private int act_id;
    private int as_id;
    private String as_name;
    private String act_name;
    private String act_content;
    private String act_form;
    private String act_address;
    private String act_regStart;
    private String act_regEnd;
    private String act_start;
    private String act_end;
    private String act_number;
    private String act_level;
    private String att_date;

    public Activity() {
    }

    public Activity(int act_id, int as_id, String as_name,String act_name, String act_content, String act_form, String act_address,
                    String act_regStart, String act_regEnd, String act_start, String act_end, String act_number, String act_level,String att_date) {
        this.act_id = act_id;
        this.as_id = as_id;
        this.as_name = as_name;
        this.act_name = act_name;
        this.act_content = act_content;
        this.act_form = act_form;
        this.act_address = act_address;
        this.act_regStart = act_regStart;
        this.act_regEnd = act_regEnd;
        this.act_start = act_start;
        this.act_end = act_end;
        this.act_number = act_number;
        this.act_level = act_level;
        this.att_date = att_date;
    }

    public String getAct_name() {
        return act_name;
    }

    public void setAct_name(String act_name) {
        this.act_name = act_name;
    }

    public int getAct_id() {
        return act_id;
    }

    public void setAct_id(int act_id) {
        this.act_id = act_id;
    }

    public String getAct_content() {
        return act_content;
    }

    public void setAct_content(String act_content) {
        this.act_content = act_content;
    }

    public String getAct_form() {
        return act_form;
    }

    public void setAct_form(String act_form) {
        this.act_form = act_form;
    }

    public String getAct_address() {
        return act_address;
    }

    public void setAct_address(String act_address) {
        this.act_address = act_address;
    }

    public String getAct_regStart() {
        return act_regStart;
    }

    public void setAct_regStart(String act_regStart) {
        this.act_regStart = act_regStart;
    }

    public String getAct_regEnd() {
        return act_regEnd;
    }

    public void setAct_regEnd(String act_regEnd) {
        this.act_regEnd = act_regEnd;
    }

    public String getAct_start() {
        return act_start;
    }

    public void setAct_start(String act_start) {
        this.act_start = act_start;
    }

    public String getAct_end() {
        return act_end;
    }

    public void setAct_end(String act_end) {
        this.act_end = act_end;
    }

    public String getAct_number() {
        return act_number;
    }

    public void setAct_number(String act_number) {
        this.act_number = act_number;
    }

    public String getAs_name() {
        return as_name;
    }

    public void setAs_name(String as_name) {
        this.as_name = as_name;
    }

    public String getAct_level() {
        return act_level;
    }

    public void setAct_level(String act_level) {
        this.act_level = act_level;
    }

    public int getAs_id() {
        return as_id;
    }

    public void setAs_id(int as_id) {
        this.as_id = as_id;
    }

    public String getAtt_date() {
        return att_date;
    }

    public void setAtt_date(String att_date) {
        this.att_date = att_date;
    }
}
