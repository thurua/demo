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
import com.ifs.eportal.dto.ProfileDto;
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

				m1.setId(m.getId());
				m1.setActive(m.isActive());
				m1.setClient(m.getClient());
				m1.setContact(m.getContact());
				m1.setCurrencyIsoCode(m.getCurrencyIsoCode());
				m1.setEmail(m.getEmail());
				m1.setExternalId(m.getExternalId());
				m1.setFirstName(m.getFirstName());
				m1.setHcErr(m.getHcErr());
				m1.setHcLastop(m.getHcLastop());
				m1.setDeleted(m.isDeleted());
				m1.setLastName(m.getLastName());
				m1.setMobile(m.getMobile());
				m1.setName(m.getMobile());
				m1.setPassword(m.getPassword());
				m1.setRole(m.getRole());
				m1.setSalutation(m.getSalutation());
				m1.setSfid(m.getSfid());
				m1.setSystemModStamp(m.getSystemModStamp());

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

	/**
	 * Get Profile
	 * 
	 * @return
	 */
	public ProfileDto getProfile(int id) {
		ProfileDto res = new ProfileDto();

		List<Object[]> l = portalUserDao.getProfile(id);
		for (Object[] i : l) {

			res.setEmail((String) i[0]);
			res.setFirstName((String) i[1]);
			res.setLastName((String) i[2]);
			res.setSalutation((String) i[3]);
			res.setRoleName((String) i[4]);
			res.setCompanyName((String) i[5]);
			res.setMobile((String) i[6]);
			res.setId((int) i[7]);
		}
		return res;
	}

	// end
}