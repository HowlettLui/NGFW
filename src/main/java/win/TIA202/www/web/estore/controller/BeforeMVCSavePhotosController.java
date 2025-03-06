package win.TIA202.www.web.estore.controller;

import javax.servlet.http.HttpServlet;

//@WebServlet("/estoreadmin/upload")
public class BeforeMVCSavePhotosController extends HttpServlet {
    private static final long serialVersionUID = 1014692160548729533L;

//    private static final Gson GSON = new Gson();
//    private static final Decoder DECODER = Base64.getDecoder();
//    private static final String UPLOAD_DIRECTORY = System.getProperty("catalina.home") + "\\files\\";
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
////        resp.setContentType("application/json");
//        resp.setCharacterEncoding("UTF-8");
//
//        try {
//            JsonObject reqBody = GSON.fromJson(req.getReader(), JsonObject.class);
//            String base64img = reqBody.get("itemPhoto").getAsString();
//            byte[] imgBytes = DECODER.decode(base64img);
//            String itemPhotoPath = UPLOAD_DIRECTORY + UUID.randomUUID().toString() + ".jpg";
//            Files.write(Paths.get(itemPhotoPath), imgBytes);
//            resp.getWriter().write(itemPhotoPath);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
