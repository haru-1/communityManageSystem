package bean;

/**
 * 请求体的封装
 *
 */
public class ResponseObject {
	
	private String msg;  //服务端返回给客户端的信息
	private int state = 1; //0 表示返回数据失败
	private Object data; //存放真正有用的解析数据
	private int page;
	private int size;
	private int count;
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	//失败的时候返回失败信息，以及state
	public ResponseObject(int state, String msg) {
		this.state = state;
		this.msg = msg;
	}
	//成功的时候
	public ResponseObject(int state, Object data) {
		this.state = state;
		this.data = data;
	}
	public ResponseObject(int state, String msg, Object data) {
		this.state = state;
		this.msg = msg;
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
