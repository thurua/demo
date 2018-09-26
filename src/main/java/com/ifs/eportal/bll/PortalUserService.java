package com.ifs.eportal.bll;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifs.eportal.dal.PortalUserDao;
import com.ifs.eportal.model.PortalUser;

@Service(value = "portalUserService")
@Transactional
public class PortalUserService implements UserDetailsService {
	// region -- Fields --

	@Autowired
	private PortalUserDao portalUserDao;

	// end

	// region -- Methods --

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		PortalUser m = portalUserDao.getBy(email);

		if (m == null) {
			throw new UsernameNotFoundException("Invalid email or password.");
		}

		String hash = m.getPasswordHash();
		List<SimpleGrantedAuthority> auths = getRole(m.getId());

		return new User(email, hash, auths);
	}

	public List<SimpleGrantedAuthority> getRole(int id) {
		List<String> roles = portalUserDao.getRoleBy(id);
		List<SimpleGrantedAuthority> res = getAuthority(roles);
		return res;
	}

	private List<SimpleGrantedAuthority> getAuthority(List<String> roles) {
		return roles.stream().map(r -> new SimpleGrantedAuthority(r)).collect(Collectors.toList());
	}

	/**
	 * Get by
	 * 
	 * @param id
	 * @return
	 */
	public PortalUser getBy(int id) {
		PortalUser res = portalUserDao.getBy(id);
		return res;
	}

	/**
	 * Get by
	 * 
	 * @param email
	 * @return
	 */
	public PortalUser getBy(String email) {
		PortalUser res = portalUserDao.getBy(email);
		return res;
	}

	/**
	 * Search all
	 * 
	 * @return
	 */
	public List<PortalUser> search() {
		List<PortalUser> res = portalUserDao.search();
		return res;
	}

	public String save(PortalUser m) {
		String res = "";

		Integer id = m.getId();
		// int userId = m.getUserId();

		if (id == null || id == 0) {

			m.setActive(true);
			m.setDeleted(false);
			m.setCreatedDate(new Date());

			portalUserDao.save(m);

		} else {
			PortalUser m1 = portalUserDao.getBy(id);
			if (m1 == null) {
				res = "Id does not exist";
			} else {
				m1 = portalUserDao.getBy(id);
				// m1.setModifyBy(userId);
				// m1.setModifyOn(new Date());

				portalUserDao.save(m1);
			}
		}

		return res;
	}

	public String delete(PortalUser m) {
		String res = "";

		if (m == null) {
			res = "Id does not exist";
		} else {

			// m.setModifyBy(userId);
			// m.setModifyOn(new Date());

			m.setDeleted(true);

			portalUserDao.save(m);
		}

		return res;
	}

	// end
}