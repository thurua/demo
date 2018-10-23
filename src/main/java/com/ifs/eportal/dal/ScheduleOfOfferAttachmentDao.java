package com.ifs.eportal.dal;

import java.math.BigInteger;
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
import com.ifs.eportal.dto.AttachmentDto;
import com.ifs.eportal.dto.SortDto;
import com.ifs.eportal.filter.AttachmentFilter;
import com.ifs.eportal.model.ScheduleOfOfferAttachment;
import com.ifs.eportal.req.PagingReq;

/**
 * 
 * @author ToanNguyen 2018-Oct-05 (verified)
 *
 */
@Service(value = "scheduleOfOfferAttachmentDao")
public class ScheduleOfOfferAttachmentDao implements Repository<ScheduleOfOfferAttachment, Integer> {
	// region -- Implements --

	/**
	 * Create
	 */
	@Override
	public void create(ScheduleOfOfferAttachment entity) {
		_em.persist(entity);
	}

	/**
	 * Read
	 */
	@Override
	public ScheduleOfOfferAttachment read(Integer id) {
		return _em.find(ScheduleOfOfferAttachment.class, id);
	}

	/**
	 * Update
	 */
	@Override
	public ScheduleOfOfferAttachment update(ScheduleOfOfferAttachment entity) {
		return _em.merge(entity);
	}

	/**
	 * Delete
	 */
	@Override
	public void delete(ScheduleOfOfferAttachment entity) {
		_em.remove(entity);
	}

	// end

	// region -- Fields --

	@Autowired
	private EntityManager _em;

	private String _path;

	private String _sql;

	private static final Logger _log = Logger.getLogger(ScheduleOfOfferAttachmentDao.class.getName());

	// end

	// region -- Methods --

	public ScheduleOfOfferAttachmentDao() {
		_path = ZFile.getPath("/sql/" + ScheduleOfOfferAttachmentDao.class.getSimpleName());
		_sql = ZFile.read(_path + "_sql.sql");
	}

	/**
	 * Get by
	 * 
	 * @param id
	 * @return
	 */
	public AttachmentDto getBy(Integer id) {
		AttachmentDto res = new AttachmentDto();

		try {
			String sql = _sql + " WHERE a.id = :id";

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("id", id);
			Object[] i = (Object[]) q.getSingleResult();

			// Convert
			res = AttachmentDto.convert(i);
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
	public AttachmentDto getBy(String sfId) {
		AttachmentDto res = new AttachmentDto();

		try {
			String sql = _sql + " WHERE a.sfid = :sfid";

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("sfid", sfId);
			Object[] i = (Object[]) q.getSingleResult();

			// Convert
			res = AttachmentDto.convert(i);
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
	 * Search by
	 * 
	 * @param req
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<AttachmentDto> search(PagingReq req) {
		List<AttachmentDto> res = new ArrayList<AttachmentDto>();

		try {
			// Get data
			Object filter = req.getFilter();
			int page = req.getPage();
			int size = req.getSize();
			List<SortDto> sort = req.getSort();
			int offset = (page - 1) * size;

			// Order by
			String orderBy = "";
			for (SortDto o : sort) {
				String field = o.getField();
				String direction = o.getDirection();

				if ("id".equals(field)) {
					if (!orderBy.isEmpty()) {
						orderBy += ",";
					}
					orderBy += " a.id " + direction;
				}
				if ("name".equals(field)) {
					if (!orderBy.isEmpty()) {
						orderBy += ",";
					}
					orderBy += " a.name " + direction;
				}
				if ("uploadedOn".equals(field)) {
					if (!orderBy.isEmpty()) {
						orderBy += ",";
					}
					orderBy += " a.uploaded_on__c " + direction;
				}
			}

			if (!orderBy.isEmpty()) {
				orderBy = " ORDER BY " + orderBy;
			}

			// Execute to count all
			int i = _sql.indexOf("FROM");
			String sql = "SELECT COUNT(*) " + _sql.substring(i);
			String limit = "";
			Query q = createQuery(sql, filter, limit);
			BigInteger total = (BigInteger) q.getSingleResult();
			req.setTotal(total.longValue());

			// Execute to search
			sql = _sql;
			limit = orderBy;
			if (req.isPaging()) {
				limit += " OFFSET " + offset + " LIMIT " + size;
			}
			q = createQuery(sql, filter, limit);
			List<Object[]> l = q.getResultList();

			// Convert
			res = AttachmentDto.convert(l);
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
	 * Create query to prevent SQL injection
	 * 
	 * @param sql
	 * @param o
	 * @return
	 */
	private Query createQuery(String sql, Object o, String limit) {
		Query q = null;

		try {
			AttachmentFilter filter = AttachmentFilter.convert(o);
			String parentUuId = filter.getParentUuId();

			// Where
			String where = "";
			if (!parentUuId.isEmpty()) {
				where += " AND a.parent_uuid__c = :parentUuId AND a.isdeleted = FALSE AND a.isactive__c = TRUE";
			}
			// Replace first
			if (!where.isEmpty()) {
				where = where.replaceFirst("AND", "WHERE");
			}

			q = _em.createNativeQuery(sql + where + limit);

			// Set parameter
			if (!where.isEmpty()) {
				int i = where.indexOf(":parentUuId");
				i = where.indexOf(":parentUuId");
				if (i > 0) {
					q.setParameter("parentUuId", parentUuId);
				}
			}
		} catch (Exception ex) {
			if (ZConfig._printTrace) {
				ex.printStackTrace();
			}
			if (ZConfig._writeLog) {
				_log.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}

		return q;
	}

	// end
}