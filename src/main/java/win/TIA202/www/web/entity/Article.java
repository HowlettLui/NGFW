package win.TIA202.www.web.entity;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.annotation.processing.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import win.TIA202.www.core.pojo.CoreMsg;

@Entity
@Getter
@Setter
//@NoArgsConstructor
public class Article extends CoreMsg {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "article_id")
	private Integer articleId;
	@Column(name = "user_id")
	private Integer userId;
	@Column(name = "staff_id")
	private Integer staffId;
	@Column(name = "news_photo")
	private String newsPhoto;
//	@Column(name = "maintitle")
	private String mainTitle;
//	@Column(name = "subtitle")
	private String subTitle;
//	@Column(name = "content")
	private String content;
	@Column(name = "publish_date")
	@JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
	private Timestamp publishDate;
//	@Column(name = "status")
	private Boolean status;
	@Column(name = "article_type")
	private String articleType;
	@Column(name = "create_time", insertable = false)
	private Timestamp createTime; // Corresponds to CREATED_DATE (Timestamp of creation)

//	預設建構子
	public Article() {
		this.userId = 6;
		this.staffId = 1;
		this.status = true;
		this.articleType = "1";
	}

//	public Integer getArticleId() {
//		return articleId;
//	}
//	
//	public void setArticleId(Integer articleId) {
//		this.articleId = articleId;
//	}
//	
//	public Integer getUserId() {
//		return userId;
//	}
//	
//	public void setUserId(Integer userId) {
//		this.userId = userId;
//	}
//	
//	public Integer getStaffId() {
//		return staffId;
//	}
//	
//	public void setStaffId(Integer staffId) {
//		this.staffId = staffId;
//	}
//	
//	public String getNewsPhoto() {
//		return newsPhoto;
//	}
//	
//	public void setNewsPhoto(String newsPhoto) {
//		this.newsPhoto = newsPhoto;
//	}
//	
//	public String getMainTitle() {
//		return mainTitle;
//	}
//
//	public void setMainTitle(String mainTitle) {
//		this.mainTitle = mainTitle;
//	}
//	
//	public String getSubTitle() {
//		return subTitle;
//	}
//
//	public void setSubTitle(String subTitle) {
//		this.subTitle = subTitle;
//	}
//	
//	public String getContent() {
//		return content;
//	}
//
//	public void setContent(String content) {
//		this.content = content;
//	}
//	
//	public Timestamp getPublishDate() {
//		return publishDate;
//	}
//
//	public void setPublishDate(Timestamp publishDate) {
//		this.publishDate = publishDate;
//	}
//
//	public Boolean getStatus() {
//		return status;
//	}
//
//	public void setStatus(Boolean status) {
//		this.status = status;
//	}
//	
//	public String getArticleType() {
//		return articleType;
//	}
//
//	public void setArticleType(String articleType) {
//		this.articleType = articleType;
//	}
//
//	public Timestamp getCreatedTime() {
//		return createdTime;
//	}
//
//	public void setCreatedTime(Timestamp createdTime) {
//		this.createdTime = createdTime;
//	}


}
