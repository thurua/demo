package com.ifs.eportal.dal;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifs.eportal.common.ZConfig;
import com.ifs.eportal.common.ZFile;
import com.ifs.eportal.dto.CurrencyTypeDto;
import com.ifs.eportal.model.CurrencyType;

/**
 * 
 * @author ToanNguyen 2018-Oct-10 (verified)
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

	private String _path;

	private String _sql;

	private static final Logger _log = Logger.getLogger(CurrencyTypeDao.class.getName());

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public CurrencyTypeDao() {
		_path = ZFile.getPath("/sql/" + CurrencyTypeDao.class.getSimpleName());
		_sql = ZFile.read(_path + "_sql.sql");
	}

	/**
	 * Get by
	 * 
	 * @param id
	 * @return
	 */
	public CurrencyTypeDto getBy(Integer id) {
		CurrencyTypeDto res = new CurrencyTypeDto();

		try {
			String sql = _sql + " WHERE a.id = :id";

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("id", id);
			Object[] t = (Object[]) q.getSingleResult();

			// Convert
			res = CurrencyTypeDto.convert(t);
		} catch (Exception ex) {
			if (ZConfig._printTrace) {
				ex.printStackTrace();
			}
			if (ZConfig._writeLog) {
				_log.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}

		return res;
	}

	/**
	 * Get by
	 * 
	 * @param sfId
	 * @return
	 */
	public CurrencyTypeDto getBy(String sfId) {
		CurrencyTypeDto res = new CurrencyTypeDto();

		try {
			String sql = _sql + " WHERE a.sfid = :sfId";

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("sfId", sfId);
			Object[] t = (Object[]) q.getSingleResult();

			// Convert
			res = CurrencyTypeDto.convert(t);
		} catch (Exception ex) {
			if (ZConfig._printTrace) {
				ex.printStackTrace();
			}
			if (ZConfig._writeLog) {
				_log.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}

		return res;
	}

	/**
	 * Get by
	 * 
	 * @param isoCode
	 * @return
	 */
	public CurrencyTypeDto getByIsoCode(String isoCode) {
		CurrencyTypeDto res = new CurrencyTypeDto();

		try {
			String sql = _sql + " WHERE a.isocode = :isoCode";

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("isoCode", isoCode);
			Object[] t = (Object[]) q.getSingleResult();

			// Convert
			res = CurrencyTypeDto.convert(t);
		} catch (Exception ex) {
			if (ZConfig._printTrace) {
				ex.printStackTrace();
			}
			if (ZConfig._writeLog) {
				_log.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}

		return res;
	}

	// end
}