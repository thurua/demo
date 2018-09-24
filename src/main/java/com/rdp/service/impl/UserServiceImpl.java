package com.rdp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rdp.common.Utils;
import com.rdp.dao.UserDao;
import com.rdp.dao.UserRoleDao;
import com.rdp.dto.UserDto;
import com.rdp.model.User;
import com.rdp.req.UserSearchReq;
import com.rdp.service.UserService;

@Service(value = "userService")
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
	// region -- Fields --

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserRoleDao userRoleDao;

	// end

	// region -- Methods --

	@Override
	public List<UserDto> search(UserSearchReq dto) {
		List<UserDto> res = new ArrayList<>();

		String company = dto.getCompany();
		String status = dto.getStatus();
		String firstName = dto.getFirstName();
		String lastName = dto.getLastName();

		// Check empty
		if (company == null || company.isEmpty()) {
			company = "%";
		}
		if (firstName == null || firstName.isEmpty()) {
			firstName = "%";
		}
		if (lastName == null || lastName.isEmpty()) {
			lastName = "%";
		}
		if (status == null || status.trim().equals("-")) {
			status = "";
		}

		List<User> tmp = userDao.search(company, status, "%");

		for (User m : tmp) {

			UserDto o = new UserDto();
			o.setId(m.getId());
			o.setFirstName(m.getFirstName());
			o.setLastName(m.getLastName());
			o.setEmail(m.getEmail());
			o.setCompany(m.getCompany());
			o.setStatus(m.getStatus());
			o.setKycStatus(m.getKycStatus());

			res.add(o);
		}

		return res;
	}

	@Override
	public User findUserByUserName(String username) {

		return userDao.getUserByUserName(username);
	}

	@Override
	public User findUsersByEmail(String email) {
		return userDao.getUserByEmail(email);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = findUserByUserName(username);
		List<String> roles = new ArrayList<>();

		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		} else {
			roles = userRoleDao.getRoleByUserId(user.getId());
		}

		String hash = user.getPasswordHash();
		return new org.springframework.security.core.userdetails.User(username, hash, getAuthority(roles));
	}

	@Override
	public List<SimpleGrantedAuthority> getAuthorityByUserId(int userId) {
		List<String> tmp = userRoleDao.getRoleByUserId(userId);
		return Utils.getAuthorities(tmp);
	}

	private List<SimpleGrantedAuthority> getAuthority(List<String> roles) {
		return roles.stream().map(r -> new SimpleGrantedAuthority(r)).collect(Collectors.toList());
	}

	// end
}