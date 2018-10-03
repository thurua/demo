package com.ifs.eportal.dal;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifs.eportal.dto.PasswordChangeHistoryDto;
import com.ifs.eportal.model.PasswordChangeHistory;

/**
 * 
 * @author HoanNguyen 2018-Oct-3
 *
 */
@Service(value = "passwordChangeHistoryDao")
public class PasswordChangeHistoryDao implements Repository<PasswordChangeHistory, Integer> {
	// region -- Implements --

	/**
	 * Create
	 */
	@Override
	public void create(PasswordChangeHistory entity) {
		_em.persist(entity);
	}

	/**
	 * Read
	 */
	@Override
	public PasswordChangeHistory read(Integer id) {
		return _em.find(PasswordChangeHistory.class, id);
	}

	/**
	 * Update
	 */
	@Override
	public PasswordChangeHistory update(PasswordChangeHistory entity) {
		return _em.merge(entity);
	}

	/**
	 * Delete
	 */
	@Override
	public void delete(PasswordChangeHistory entity) {
		_em.remove(entity);
	}

	// end

	// region -- Fields --

	@Autowired
	private EntityManager _em;

	private String _sql;

	// end

	// region -- Methods --
	public PasswordChangeHistoryDao() {
		_sql = "SELECT a.id, \r\n" + "	a.user_sfid, a.user_name, a.change_by, a.changed_on \r\n"
				+ "FROM public.password_change_history a";
	}

	/**
	 * Get by
	 * 
	 * @param id
	 * @return
	 */
	public PasswordChangeHistoryDto getBy(String sfid) {
		String sql = _sql + " WHERE a.user_sfid = :sfid";

		// Execute
		Query q = _em.createNativeQuery(sql);
		q.setParameter("sfid", sfid);
		Object[] i = (Object[]) q.getSingleResult();

		// Convert
		PasswordChangeHistoryDto res = PasswordChangeHistoryDto.convert(i);
		return res;
	}
}
