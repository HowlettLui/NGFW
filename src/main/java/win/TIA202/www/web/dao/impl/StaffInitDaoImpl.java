package win.TIA202.www.web.dao.impl;

import java.util.List;

import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import win.TIA202.www.web.dao.StaffInitDao;
import win.TIA202.www.web.entity.Staff;

@Repository
public class StaffInitDaoImpl implements StaffInitDao {
	@PersistenceContext
	private Session session;

	@Override
	public int staffInitInsert(Staff staff) {
		session.persist(staff);
		return 1;
	}

	@Override
	public Staff staffSelectForLogin(String staffEmail, String staffPassword) {		
		final String sql = "select * from STAFF where STAFF_EMAIL = :staff_email and STAFF_PASSWORD = :staff_password";
		return session
				.createNativeQuery(sql, Staff.class)
				.setParameter("staff_email", staffEmail)
				.setParameter("staff_password", staffPassword)
				.uniqueResult();
	}

	public Staff selectByStaffEmail(String staffEmail) {
		CriteriaBuilder cBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Staff> cQuery = cBuilder.createQuery(Staff.class);
		Root<Staff> root = cQuery.from(Staff.class);
		cQuery.where(cBuilder.equal(root.get("staffEmail"), staffEmail));
		return session
				.createQuery(cQuery)
				.uniqueResult();
	}

	@Override
	public List<Staff> selectAllUser() {
		final String hql = "FROM Staff ORDER BY staff_id";
		return session
				.createQuery(hql, Staff.class)
				.getResultList();
	}

	@Override
	public Staff selectStaffById(Integer id) {
		final String hql = "FROM Staff WHERE staffId = :staff_id";
		
		return session
				.createQuery(hql, Staff.class)
				.setParameter("staff_id", id)
				.uniqueResult();
	}

	@Override
	public int updateStaff(Staff staff) {
		final StringBuilder hql = new StringBuilder()
			.append("UPDATE Staff SET ");
		final String password = staff.getStaffPassword();
		if (password != null && !password.isEmpty()) {
			hql.append("staffPassword = :password,");
		}
		hql
			.append("staffName = :staffName, ")
			.append("staffEmail = :staffEmail, ")
			.append("staffPhone = :staffPhone, ")
			.append("staffStatus = :staffStatus, ")
			.append("staffRoleId = :staffRoleId ")
			.append("WHERE staffId = :staffId");

		Query<?> query = session.createQuery(hql.toString());
		if (password != null && !password.isEmpty()) {
			query.setParameter("password", staff.getStaffPassword());
		}
			
		return query
				.setParameter("staffId", staff.getStaffId())
				.setParameter("staffName", staff.getStaffName())
				.setParameter("staffEmail", staff.getStaffEmail())
				.setParameter("staffPhone", staff.getStaffPhone())
				.setParameter("staffStatus", staff.getStaffStatus())
				.setParameter("staffRoleId", staff.getStaffRole())
				.executeUpdate();
	}
}
