package com.ifs.eportal.bll;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
import com.ifs.eportal.common.RsaService;
import com.ifs.eportal.common.Utils;
import com.ifs.eportal.dal.PasswordChangeHistoryDao;
import com.ifs.eportal.dal.PortalUserDao;
import com.ifs.eportal.dto.PortalUserDto;
import com.ifs.eportal.model.PasswordChangeHistory;
import com.ifs.eportal.model.PortalUser;
import com.ifs.eportal.req.PagingReq;
import com.ifs.eportal.req.PasswordReq;
import com.ifs.eportal.req.ProfileReq;

/**
 * 
 * @author ToanNguyen 2018-Sep-27
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
		PortalUserDto m = portalUserDao.getBy(email);

		if (m.getEmail().isEmpty()) {
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

	// end

	// region -- Methods --

	/**
	 * Read by
	 * 
	 * @param id
	 * @return
	 */
	public PortalUserDto read(int id) {
		return portalUserDao.getBy(id);
	}

	/**
	 * Read by
	 * 
	 * @param email
	 * @return
	 */
	public PortalUserDto read(String email) {
		return portalUserDao.getBy(email);
	}

	/**
	 * Update profile
	 * 
	 * @param req
	 * @return
	 */
	public String update(ProfileReq req) {
		String res = "";

		// Get data
		Integer id = req.getId();
		// String firstName = req.getFirstName();
		// String lastName = req.getLastName();
		// String salutation = req.getSalutation();
		// String mobile = req.getMobile();

		// Handle
		PortalUser m = portalUserDao.read(id);
		if (m == null) {
			res = "Id does not exist";
		} else {
			// m.setFirstName(firstName);
			// m.setLastName(lastName);
			// m.setSalutation(salutation);
			// m.setMobile(mobile);

			portalUserDao.update(m);
		}

		return res;
	}

	/**
	 * Update password
	 * 
	 * @param req
	 * @param id
	 * @return
	 */
	public String update(PasswordReq req, boolean isReset) {
		String res = "";

		// Get data
		Integer id = req.getId();
		String email = req.getEmail();
		String oldPassword = req.getOldPassword();
		String newPassword = req.getNewPassword();

		// Decrypt
		oldPassword = RsaService.decrypt(oldPassword);
		newPassword = RsaService.decrypt(newPassword);

		// Convert token to id
		if (isReset) {
			PortalUserDto o = portalUserDao.getBy((Object) email);
			id = o.getId();
		}

		// Handle
		PortalUser m = portalUserDao.read(id);
		if (m == null) {
			res = "Id does not exist";
		} else {
			boolean ok = true;
			String mode = "";
			String t = bCryptPasswordEncoder.encode(newPassword);

			if (!isReset) {
				ok = bCryptPasswordEncoder.matches(oldPassword, m.getPassword());
			}

			if (ok) {
				m.setPassword(t);
				t = Utils.hash(t, Const.Authentication.TOKEN_KEY1);
				m.setPasswordHash(t);

				if (isReset) {
					m.setPassReminderExpire(null);
					m.setPassReminderToken(null);
				}

				portalUserDao.update(m);
				mode = "A";
			} else {
				res = "Old password is incorrect";
				mode = "U";
			}

			Date today = new Date();
			PasswordChangeHistory pwch = new PasswordChangeHistory();
			pwch.setUserSfid(m.getSfid());
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
	 * @param email
	 * @return
	 */
	public String update(String email) {
		String res = "";

		try {
			PortalUserDto o = portalUserDao.getBy(email);
			Integer id = o.getId();

			// Generate password reminder token
			String t = bCryptPasswordEncoder.encode(email);
			String token = Utils.hash(t, Const.Authentication.TOKEN_KEY2);

			// Get password reminder token expire time
			Date expire = Utils.getTime(Calendar.HOUR, 24);
			if (expire == null) {
				res = "102";
			}

			PortalUser m = portalUserDao.read(id);
			if (m == null) {
				res = "Id does not exist";
			} else {
				m.setPassReminderExpire(expire);
				m.setPassReminderToken(token);

				portalUserDao.update(m);
			}
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
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
		return portalUserDao.search(req);
	}

	/**
	 * Get role by
	 * 
	 * @param id
	 * @return
	 */
	public List<SimpleGrantedAuthority> getRoleBy(int id) {
		List<String> roles = portalUserDao.getRoleBy(id);
		List<SimpleGrantedAuthority> res = getAuthority(roles);
		return res;
	}

	/**
	 * Get authority
	 * 
	 * @param roles
	 * @return
	 */
	private List<SimpleGrantedAuthority> getAuthority(List<String> roles) {
		return roles.stream().map(r -> new SimpleGrantedAuthority(r)).collect(Collectors.toList());
	}

	// end
}