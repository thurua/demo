package com.ifs.eportal.dal;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifs.eportal.common.ZConfig;
import com.ifs.eportal.common.ZFile;
import com.ifs.eportal.dto.BranchDto;
import com.ifs.eportal.model.Branch;

/**
 * 
 * @author ToanNguyen 2018-Oct-05 (verified)
 *
 */
@Service(value = "branchDao")
public class BranchDao implements Repository<Branch, Integer> {
	// region -- Implements --

	/**
	 * Create
	 */
	@Override
	public void create(Branch entity) {
		_em.persist(entity);
	}

	/**
	 * Read
	 */
	@Override
	public Branch read(Integer id) {
		return _em.find(Branch.class, id);
	}

	/**
	 * Update
	 */
	@Override
	public Branch update(Branch entity) {
		return _em.merge(entity);
	}

	/**
	 * Delete
	 */
	@Override
	public void delete(Branch entity) {
		_em.remove(entity);
	}

	// end

	// region -- Fields --

	@Autowired
	private EntityManager _em;

	private String _path;

	private String _sql;

	private static final Logger _log = Logger.getLogger(BranchDao.class.getName());

	// end

	// region -- Methods --

	public BranchDao() {
		_path = ZFile.getPath("/sql/" + BranchDao.class.getSimpleName());
		_sql = ZFile.read(_path + "_sql.sql");
	}

	/**
	 * Get list active Branch by
	 * 
	 * @param names
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<BranchDto> getBy(List<String> names) {
		List<BranchDto> res = new ArrayList<>();

		try {
			String sql = _sql;

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("names", names);
			List<Object[]> l = q.getResultList();

			// Convert
			res = BranchDto.convert(l);
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