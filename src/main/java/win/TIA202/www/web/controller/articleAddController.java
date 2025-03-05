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

import org.springframework.web.multipart.MultipartRequest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import win.TIA202.www.web.entity.Article;
import win.TIA202.www.web.service.ArticleService;
import win.TIA202.www.web.service.UserService;
import win.TIA202.www.web.service.impl.ArticleServiceImpl;
import win.TIA202.www.web.service.impl.UserServiceImpl;

@WebServlet("/articleadd")
public class articleAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleService service;
	
	@Override
	public void init() throws ServletException {
		try {
			service = new ArticleServiceImpl();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("newsf/news_fedit.html");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		MultipartRequest mreq = new MultipartRequest(req,"");
				
		String newsPhoto = req.getParameter("titleimage");
		String mainTitle = req.getParameter("maintitle");
		String subTitle = req.getParameter("subtitle");
		String content = req.getParameter("summernote");
		String pDate = req.getParameter("publishDate");
		pDate = pDate + " 00:00:00";
		Timestamp publishDate = Timestamp.valueOf(pDate);
		
		Article article = new Article();
		article.setNewsPhoto(newsPhoto);
		article.setMainTitle(mainTitle);
		article.setSubTitle(subTitle);
		article.setContent(content);
		article.setPublishDate(publishDate);

		
//		Gson gson = new Gson();
//		Article article = gson.fromJson(req.getReader(), Article.class);
		
		String errMsg = service.add(article);
		
		JsonObject respBody = new JsonObject();
		respBody.addProperty("successfully", errMsg == null);
		respBody.addProperty("errMsg", errMsg);
		resp.getWriter().write(respBody.toString());
	}
}
