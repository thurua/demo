package com.ifs.eportal.dal;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifs.eportal.common.Utils;
import com.ifs.eportal.dto.CurrencyTypeDto;
import com.ifs.eportal.model.CurrencyType;

/**
 * 
 * @author ToanNguyen 2018-Oct-04 (verified)
 *
 */
@Service(value = "currencyTypeDao")
public class CurrencyTypeDao implements Repository<CurrencyType, Integer> {
	// region -- Implements --

	/**
	 * Create
	 */
	@Override
	public void create(CurrencyType entity) {
		_em.persist(entity);
	}

	/**
	 * Read
	 */
	@Override
	public CurrencyType read(Integer id) {
		return _em.find(CurrencyType.class, id);
	}

	/**
	 * Update
	 */
	@Override
	public CurrencyType update(CurrencyType entity) {
		return _em.merge(entity);
	}

	/**
	 * Delete
	 */
	@Override
	public void delete(CurrencyType entity) {
		_em.remove(entity);
	}

	// end

	// region -- Fields --

	@Autowired
	private EntityManager _em;

	private String _sql;

	private static final Logger _log = Logger.getLogger(CurrencyTypeDao.class.getName());

	// end

	// region -- Methods --

	public CurrencyTypeDao() {
		_sql = "SELECT \r\n" + "	a.id, a.sfid, a.isocode \r\n" + "FROM salesforce.currencytype a ";
	}

	/**
	 * Read by
	 * 
	 * @param isoCode
	 * @return
	 */
	public CurrencyTypeDto read(String isoCode) {
		CurrencyTypeDto res = new CurrencyTypeDto();

		try {
			// Execute
			String sql = _sql + " WHERE a.isocode = :isoCode";
			Query q = _em.createNativeQuery(sql);
			q.setParameter("isoCode", isoCode);
			Object[] i = (Object[]) q.getSingleResult();

			// Convert
			res = CurrencyTypeDto.convert(i);
		} catch (Exception ex) {
			if (Utils.printStackTrace) {
				ex.printStackTrace();
			}
			if (Utils.writeLog) {
				_log.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}

		return res;
	}

	// end
}