package win.TIA202.www.web.controller;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.extensions.servlet.auth.oauth2.AbstractAuthorizationCodeServlet;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.store.FileDataStoreFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@WebServlet("/anotherOauthLogin")
public class OauthController extends AbstractAuthorizationCodeServlet {
	private static final long serialVersionUID = 1L;
	private static final String CLIENT_ID = "";
	private static final String CLIENT_SECRET = "";
	private static final java.io.File DATA_STORE_DIR = new java.io.File("credentials");
	private static final DataStoreFactory DATA_STORE_FACTORY;

	static {
		try {
			DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String url = String.valueOf(initializeFlow().newAuthorizationUrl());
		String[] urlSplit = url.split("redirect_uri");
		String finalUrl = urlSplit[0].concat("redirect_uri=http://localhost:8080/NGFW/callback")
				.concat(urlSplit[1]);
		resp.sendRedirect(finalUrl);
	}

	@Override
	protected String getRedirectUri(HttpServletRequest req) throws ServletException, IOException {
		GenericUrl url = new GenericUrl(req.getRequestURL().toString());
		url.setRawPath("/NGFW/callback");
		return url.build();
	}

	@Override
	protected AuthorizationCodeFlow initializeFlow() throws IOException {
		Set<String> scopes = new HashSet<>();
		scopes.add("email");
		scopes.add("profile");
		return new GoogleAuthorizationCodeFlow.Builder(new NetHttpTransport(), GsonFactory.getDefaultInstance(),
				CLIENT_ID, CLIENT_SECRET, scopes).setDataStoreFactory(DATA_STORE_FACTORY).setAccessType("offline")
						.build();
	}

	@Override
	protected String getUserId(HttpServletRequest req) throws ServletException, IOException {
		return req.getSession().getId();
	}
}
