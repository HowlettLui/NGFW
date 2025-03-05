//package win.TIA202.www.web.estore.dao.impl;
//
//import win.TIA202.www.web.estore.dao.ItemDao;
//import win.TIA202.www.web.estore.entity.Item;
//import win.TIA202.www.web.estore.entity.ItemInfo;
//import win.TIA202.www.web.estore.entity.ItemModel;
//
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//
//public class ItemDaoImplWOFramework implements ItemDao {
//
/// /    @PersistenceContext
/// /    private Session session;
//
//    private DataSource ds;
//
//    public ItemDaoImplWOFramework() throws NamingException {
//        ds = (DataSource) new InitialContext().lookup("java:comp/env/NGFW");
//    }
//
//    @Override
//    public Item selectById(Integer id) {
//        String sql = "select * from item where item_id = ?";
//        try (
//            Connection conn = ds.getConnection();
//            PreparedStatement ps = conn.prepareStatement(sql);
//        ) {
//            ps.setString(1, id.toString());
//            try (ResultSet rs = ps.executeQuery()) {
//                if (rs.next()) {
//                    Item item = new Item();
//                    item.setItemId(rs.getInt("item_id"));
//                    item.setItemName(rs.getString("item_name"));
//                    item.setStaffId(rs.getInt("staff_id"));
//                    item.setItemContent(rs.getString("item_content"));
//                    item.setItemType(rs.getString("item_type"));
//                    item.setItemPhoto(rs.getString("item_photo"));
//                    item.setItemModelId(rs.getInt("item_model_id"));
//                    item.setItemPrice(rs.getInt("item_price"));
//                    item.setItemDetails(rs.getString("item_details"));
//                    item.setCreateTime(rs.getTimestamp("create_time"));
//                    return item;
//                }
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return null;
//    }
//}
