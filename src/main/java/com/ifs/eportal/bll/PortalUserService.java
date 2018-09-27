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
import com.ifs.eportal.dto.PayloadDto;
import com.ifs.eportal.dto.ProfileDto;
import com.ifs.eportal.model.PortalUser;
import com.ifs.eportal.req.ProfileReq;

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
		PayloadDto m = new PayloadDto();

		List<Object[]> l = portalUserDao.getBy(email);
		for (Object[] i : l) {
			m.setId((Integer) i[0]);
			m.setEmail((String) i[1]);
			m.setPassword((String) i[2]);
			m.setPasswordHash((String) i[3]);
			m.setPassReminderToken((String) i[4]);
			m.setPassReminderExpire((Date) i[5]);
			m.setFirstName((String) i[6]);
			m.setLastName((String) i[7]);
			m.setClientId((String) i[8]);
			m.setClientName((String) i[9]);
		}

		if (m.getEmail().isEmpty()) {
			throw new UsernameNotFoundException("Invalid email or password.");
		}

		String hash = m.getPassword();
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
	public PayloadDto getBy(String email) {
		PayloadDto m = new PayloadDto();

		List<Object[]> l = portalUserDao.getBy(email);
		for (Object[] i : l) {
			m.setId((Integer) i[0]);
			m.setEmail((String) i[1]);
			m.setPassword((String) i[2]);
			m.setPasswordHash((String) i[3]);
			m.setPassReminderToken((String) i[4]);
			m.setPassReminderExpire((Date) i[5]);
			m.setFirstName((String) i[6]);
			m.setLastName((String) i[7]);
			m.setClientId((String) i[8]);
			m.setClientName((String) i[9]);
		}
		return m;
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

	public String save(ProfileReq req) {
		String res = "";

		// Get data
		Integer id = req.getId();
		String firstName = req.getFirstName();
		String lastName = req.getLastName();
		String salutation = req.getSalutation();
		String mobile = req.getMobile();

		if (id == 0) {
			res = "101";
		} else {
			PortalUser m = portalUserDao.getBy(id);
			if (m == null) {
				res = "Id does not exist";
			} else {

				m.setFirstName(firstName);
				m.setLastName(lastName);
				m.setSalutation(salutation);
				m.setMobile(mobile);

				portalUserDao.save(m);
			}
		}

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

			res.setId((Integer) i[0]);
			res.setEmail((String) i[1]);
			res.setFirstName((String) i[2]);
			res.setLastName((String) i[3]);
			res.setSalutation((String) i[4]);
			res.setRoleName((String) i[5]);
			res.setCompanyName((String) i[6]);
			res.setMobile((String) i[7]);			
		}
		return res;
	}

	// end
}