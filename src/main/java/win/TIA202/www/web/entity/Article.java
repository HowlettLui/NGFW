package win.TIA202.www.web.entity;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import win.TIA202.www.core.pojo.CoreMsg;

public class Article extends CoreMsg {
	private Integer articleId;
	private Integer userId;
	private Integer staffId;
	private byte[] newsPhoto;
	private String mainTitle;
	private String subTitle;
	private String content;
	private Timestamp publishDate;
	private Boolean status;
	private String articleType;
	private Timestamp createdTime; // Corresponds to CREATED_DATE (Timestamp of creation)

//	預設建構子
	public Article() {
		this.userId = 6;
		this.staffId = 1;
		this.status = false;
		this.articleType = "Type";
	}

	public Integer getArticleId() {
		return articleId;
	}
	
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	
	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public Integer getStaffId() {
		return staffId;
	}
	
	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}
	
	public byte[] getNewsPhoto() {
		return newsPhoto;
	}
	
	public void setNewsPhoto(byte[] newsPhoto) {
		this.newsPhoto = newsPhoto;
	}
	
	public String getMainTitle() {
		return mainTitle;
	}

	public void setMainTitle(String mainTitle) {
		this.mainTitle = mainTitle;
	}
	
	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Timestamp getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Timestamp publishDate) {
		this.publishDate = publishDate;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	public String getArticleType() {
		return articleType;
	}

	public void setArticleType(String articleType) {
		this.articleType = articleType;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

}
