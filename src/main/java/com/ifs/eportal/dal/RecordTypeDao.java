package com.ifs.eportal.dal;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifs.eportal.common.Utils;
import com.ifs.eportal.common.ZFile;
import com.ifs.eportal.dto.RecordTypeDto;
import com.ifs.eportal.model.RecordType;

/**
 * 
 * @author ToanNguyen 2018-Oct-05 (verified)
 *
 */
@Service(value = "recordTypeDao")
public class RecordTypeDao implements Repository<RecordType, Integer> {
	// region -- Implements --

	/**
	 * Create
	 */
	@Override
	public void create(RecordType entity) {
		_em.persist(entity);
	}

	/**
	 * Read
	 */
	@Override
	public RecordType read(Integer id) {
		return _em.find(RecordType.class, id);
	}

	/**
	 * Update
	 */
	@Override
	public RecordType update(RecordType entity) {
		return _em.merge(entity);
	}

	/**
	 * Delete
	 */
	@Override
	public void delete(RecordType entity) {
		_em.remove(entity);
	}

	// end

	// region -- Fields --

	@Autowired
	private EntityManager _em;

	private String _path;

	private String _sql;

	private static final Logger _log = Logger.getLogger(RecordTypeDao.class.getName());

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public RecordTypeDao() {
		_path = ZFile.getPath("\\sql\\" + RecordTypeDao.class.getSimpleName());
		_sql = ZFile.read(_path + "_sql.sql");
	}

	/**
	 * Get by
	 * 
	 * @param id
	 * @return
	 */
	public RecordTypeDto getBy(Integer id) {
		RecordTypeDto res = new RecordTypeDto();

		try {
			String sql = _sql + " WHERE a.id = :id";

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("id", id);
			Object[] i = (Object[]) q.getSingleResult();

			// Convert
			res = RecordTypeDto.convert(i);
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

	/**
	 * Get by
	 * 
	 * @param sObjectType
	 * @param name
	 * @return
	 */
	public RecordTypeDto getBy(String sObjectType, String name) {
		RecordTypeDto res = new RecordTypeDto();

		try {
			String sql = _sql + " WHERE a.sobjecttype = :sObjectType AND a.name = :name";

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("sObjectType", sObjectType);
			q.setParameter("name", name);
			Object[] i = (Object[]) q.getSingleResult();

			// Convert
			res = RecordTypeDto.convert(i);
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