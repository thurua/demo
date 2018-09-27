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

import com.ifs.eportal.common.Utils;
import com.ifs.eportal.dal.PortalUserDao;
import com.ifs.eportal.dto.PortalUserDto;
import com.ifs.eportal.model.PortalUser;
import com.ifs.eportal.req.ChangePasswordReq;
import com.ifs.eportal.req.PagingReq;
import com.ifs.eportal.req.ProfileReq;

/**
 * 
 * @author ToanNguyen 2018-Sep-27
 *
 */
@Service(value = "portalUserService")
@Transactional
public class PortalUserService implements UserDetailsService {
	// region -- Fields --

	@Autowired
	private PortalUserDao portalUserDao;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	// end

	// region -- Methods --

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
	 * Get by
	 * 
	 * @param id
	 * @return
	 */
	public PortalUserDto getBy(int id) {
		return portalUserDao.getBy(id);
	}

	/**
	 * Get by
	 * 
	 * @param email
	 * @return
	 */
	public PortalUserDto getBy(String email) {
		return portalUserDao.getBy(email);
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
	 * Save profile
	 * 
	 * @param req
	 * @return
	 */
	public String save(ProfileReq req) {
		String res = "";

		// Get data
		Integer id = req.getId();
		String firstName = req.getFirstName();
		String lastName = req.getLastName();
		String salutation = req.getSalutation();
		String mobile = req.getMobile();

		// Handle
		PortalUser m = portalUserDao.read(id);
		if (m == null) {
			res = "Id does not exist";
		} else {
			m.setFirstName(firstName);
			m.setLastName(lastName);
			m.setSalutation(salutation);
			m.setMobile(mobile);

			portalUserDao.update(m);
		}

		return res;
	}

	/**
	 * Save password
	 * 
	 * @param req
	 * @param id
	 * @return
	 */
	public String save(ChangePasswordReq req) {
		String res = "";

		// Get data
		Integer id = req.getId();
		String oldPassword = req.getOldPassword();
		String newPassword = req.getNewPassword();
		newPassword = bCryptPasswordEncoder.encode(newPassword);

		// Handle
		PortalUser m = portalUserDao.read(id);
		if (m == null) {
			res = "Id does not exist";
		} else {
			if (oldPassword.equals(m.getPassword())) {
				m.setPassword(newPassword);
				m.setPasswordHash(newPassword);
			} else {
				res = "Password is not correct";
			}

			portalUserDao.update(m);
		}

		return res;
	}

	/**
	 * Verify mail
	 * 
	 * @param email
	 * @return
	 */
	public String verifyMail(String email) {
		String res = "";

		try {
			PortalUserDto o = portalUserDao.getBy(email);
			Integer id = o.getId();

			// Generate password reminder token
			String token = bCryptPasswordEncoder.encode(email);

			// Get password reminder token expire time
			Date expire = Utils.getTime(Calendar.MINUTE, 5);
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