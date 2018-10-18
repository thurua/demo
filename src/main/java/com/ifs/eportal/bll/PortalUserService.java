package com.ifs.eportal.bll;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifs.eportal.common.Const;
import com.ifs.eportal.common.Utils;
import com.ifs.eportal.common.ZConfig;
import com.ifs.eportal.common.ZHash;
import com.ifs.eportal.common.ZRsa;
import com.ifs.eportal.dal.PasswordChangeHistoryDao;
import com.ifs.eportal.dal.PortalUserDao;
import com.ifs.eportal.dto.PortalUserDto;
import com.ifs.eportal.model.PasswordChangeHistory;
import com.ifs.eportal.model.PortalUser;
import com.ifs.eportal.req.PagingReq;
import com.ifs.eportal.req.PasswordReq;
import com.ifs.eportal.rsp.SingleRsp;

/**
 * 
 * @author ToanNguyen 2018-Oct-10 (verified)
 *
 */
@Service(value = "portalUserService")
@Transactional
public class PortalUserService implements UserDetailsService {
	// region -- Overrides --

	/**
	 * Load user by email
	 */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		PortalUserDto m = portalUserDao.getByUserId(email);

		if (m.getUserId().isEmpty()) {
			throw new UsernameNotFoundException("Invalid email or password.");
		}

		String hash = m.getPassword();
		List<SimpleGrantedAuthority> auths = getRoleBy(m.getId());

		return new User(email, hash, auths);
	}

	// end

	// region -- Fields --

	@Autowired
	private PortalUserDao portalUserDao;

	@Autowired
	private PasswordChangeHistoryDao passwordChangeHistoryDao;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	private static final Logger _log = Logger.getLogger(PortalUserDao.class.getName());

	// end

	// region -- Methods --

	/**
	 * Get by
	 * 
	 * @param id
	 * @return
	 */
	public PortalUserDto getBy(int id) {
		PortalUserDto res = portalUserDao.getBy(id);
		return res;
	}

	/**
	 * Get by
	 * 
	 * @param sfId
	 * @return
	 */
	public PortalUserDto getBy(String sfId) {
		PortalUserDto res = portalUserDao.getBy(sfId);
		return res;
	}

	/**
	 * Get by
	 * 
	 * @param token
	 * @return
	 */
	public PortalUserDto getByToken(String token) {
		PortalUserDto res = portalUserDao.getByToken(token);
		return res;
	}

	/**
	 * Get by
	 * 
	 * @param userId
	 * @return
	 */
	public PortalUserDto getByUserId(String userId) {
		PortalUserDto res = portalUserDao.getByUserId(userId);
		return res;
	}

	/**
	 * Update password
	 * 
	 * @param req
	 * @param isReset
	 * @return
	 */
	public SingleRsp update(PasswordReq req, boolean isReset) {
		SingleRsp res = new SingleRsp();

		// Get data
		Integer id = req.getId();
		String email = req.getEmail();
		String oldPassword = req.getOldPassword();
		String newPassword = req.getNewPassword();

		// Decrypt
		oldPassword = ZRsa.decrypt(oldPassword);
		newPassword = ZRsa.decrypt(newPassword);

		String mode = "";
		// Convert token to id
		if (isReset) {
			PortalUserDto o = portalUserDao.getByToken(email);
			id = o.getId();
			res.setResult(o);

			mode = "A";

		} else {
			mode = "U";
		}

		// Handle
		PortalUser m = portalUserDao.read(id);
		if (m == null) {
			res.setError("Id does not exist");
		} else {
			boolean ok = true;

			String t = bCryptPasswordEncoder.encode(newPassword);

			if (!isReset) {
				ok = bCryptPasswordEncoder.matches(oldPassword, m.getPassword());
			}

			if (ok) {
				m.setPassword(t);
				t = ZHash.hash(t, Const.Authentication.TOKEN_KEY1);
				m.setPasswordHash(t);

				if (isReset) {
					m.setPassReminderExpire(null);
					m.setPassReminderToken(null);
					m.setStatus("ACTD");
					m.setActivatedOn(new Date());

				}
				portalUserDao.update(m);
			} else {
				res.setError("Old password is incorrect");
			}

			Date today = new Date();
			PasswordChangeHistory pwch = new PasswordChangeHistory();
			pwch.setUserSfid(m.getSfId());
			pwch.setUserName(m.getUserId());
			pwch.setChangeBy(mode);
			pwch.setChangedOn(today);

			passwordChangeHistoryDao.update(pwch);
		}

		return res;
	}

	/**
	 * Update token
	 * 
	 * @param userId
	 * @return
	 */
	public String update(String userId) {
		String res = "";

		try {
			PortalUserDto o = portalUserDao.getByUserId(userId);
			Integer id = o.getId();

			if (id == 0) {
				res = "Invalid email. Please contact System Administrator";
			} else {
				// Generate password reminder token
				String t = bCryptPasswordEncoder.encode(userId);
				String token = 'U' + ZHash.hash(t, Const.Authentication.TOKEN_KEY2);

				// Get password reminder token expire time
				Date expire = Utils.getTime(Calendar.HOUR, 24);
				if (expire == null) {
					res = "Error!";
				}

				PortalUser m = portalUserDao.read(id);
				if (m == null) {
					res = "User Id does not exist";
				} else {
					m.setPassReminderExpire(expire);
					m.setPassReminderToken(token);
					m.setNotified(true);

					portalUserDao.update(m);
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

		return res;
	}

	/**
	 * Search by
	 * 
	 * @param req
	 * @return
	 */
	public List<PortalUserDto> search(PagingReq req) {
		List<PortalUserDto> res = portalUserDao.search(req);
		return res;
	}

	/**
	 * Get role by
	 * 
	 * @param id
	 * @return
	 */
	public List<SimpleGrantedAuthority> getRoleBy(int id) {
		List<String> t = portalUserDao.getRoleBy(id);
		List<SimpleGrantedAuthority> res = getAuthority(t);
		return res;
	}

	/**
	 * Check expired
	 * 
	 * @param token
	 * @return
	 */
	public boolean checkExpired(String token) {
		PortalUserDto o = portalUserDao.getByToken(token);

		boolean res = o.getId() > 0;
		return res;
	}

	/**
	 * Get authority
	 * 
	 * @param roles
	 * @return
	 */
	private List<SimpleGrantedAuthority> getAuthority(List<String> roles) {
		Stream<SimpleGrantedAuthority> t;
		t = roles.stream().map(r -> new SimpleGrantedAuthority(r));

		List<SimpleGrantedAuthority> res = t.collect(Collectors.toList());
		return res;
	}

	// end
}