package bulletinBoard.beans;
import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String object;
	private String text;
	private String category;
	private Date insert_time;
	private int user_id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getInsertTime() {
		return insert_time;
	}

	public void setInsertTime(Date insert_time) {
		this.insert_time = insert_time;
	}

	public int getUserId(){
		return user_id;
	}
	public int setUserId(int user_id){
		return this.user_id = user_id;
	}

}