package win.TIA202.www.web.estore.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.UUID;

@WebServlet("/estoreadmin/upload")
public class SavePhotosController extends HttpServlet {
    private static final long serialVersionUID = 1014692160548729533L;

    private static final Gson GSON = new Gson();
    private static final Decoder DECODER = Base64.getDecoder();
    private static final String UPLOAD_DIRECTORY = System.getProperty("catalina.home") + "\\files\\";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try {
            JsonObject reqBody = GSON.fromJson(req.getReader(), JsonObject.class);
            String base64img = reqBody.get("itemPhoto").getAsString();
            byte[] imgBytes = DECODER.decode(base64img);
            String itemPhotoPath = UPLOAD_DIRECTORY + UUID.randomUUID().toString() + ".jpg";
            Files.write(Paths.get(itemPhotoPath), imgBytes);
            resp.getWriter().write(itemPhotoPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
