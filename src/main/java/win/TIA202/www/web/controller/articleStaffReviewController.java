package win.TIA202.www.web.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.google.gson.JsonObject;


import win.TIA202.www.web.entity.Article;
import win.TIA202.www.web.service.ArticleService;
import win.TIA202.www.web.service.UserService;
import win.TIA202.www.web.service.impl.ArticleServiceImpl;
import win.TIA202.www.web.service.impl.UserServiceImpl;

@RestController
@RequestMapping("articlereview")
public class articleStaffReviewController{

	@Autowired
	private ArticleService service;
	

	@GetMapping
	public Article View(Integer articleId) {
		return service.findIndex(articleId);
	}
	
	@PostMapping
	public View articlereview(
			@RequestParam("articleid") Integer articleId,
			@RequestParam("reviewContent") String reviewContent,
			@RequestParam("status") String status
			) {
		
		
				
		Article article = new Article();
		article.setArticleId(articleId);
		article.setReviewContent(reviewContent);
		article.setStatus(status);
		
		String errMsg = service.updateReview(article);
		
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode respBody = mapper.createObjectNode();
		respBody.put("successfully", errMsg == null);
		respBody.put("errMsg", errMsg);
		
		return new RedirectView("newsb/news_bmgrlist.html");

	}
	

}
