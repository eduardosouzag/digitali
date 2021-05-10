package com.digitali.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.digitali.api.config.SecurityConfig;
import com.digitali.api.entity.User;
import com.digitali.api.repository.UserRepository;

//Trata as regras de negócio e envia para o repositorio

@Service
public class UserService extends BeanService<User, UserRepository> implements UserDetailsService {

	@Autowired
	private SecurityConfig securityConfig;

	@Autowired
	public UserService(UserRepository repository) {
		super(repository);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = getRepository().findByUserName(username);
		if (Objects.nonNull(user) && Objects.nonNull(user.get())) {
			return new org.springframework.security.core.userdetails.User(user.get().getUserName(),
					user.get().getPassword(), new ArrayList<>());
		}

		return null;
	}

	public User getUser(String username) throws Exception {
		Optional<User> user = getRepository().findByUserName(username);
		if (Objects.isNull(user) && Objects.isNull(user.get())) {
			return user.get();
		}

		return null;
	}

	public User getUser(Integer id) throws Exception {
		if (Objects.isNull(id) || id >= 1){
			return find(id);
		}

		return null;
	}

	public List<User> getAllUser() throws Exception {
		return (List<User>) list();
	}


	public User getUserByDocument(String document) throws Exception {
		Optional<User> user = getRepository().findUserByDocument(document);
		return user.get();
	}

	public List<User> getUsersByDocument(String document) throws Exception {
		Optional<User> user = getRepository().findUsersByDocument(document);
		List<User> users = (List<User>) user.get();
		return users;
	}

	@Override
	public String save(User user) throws Exception{
		//Pega a senha trata e passa para o repositorio
		String encryptedPwd = securityConfig.passwordEncoder().encode(user.getPassword());
		user.setPassword(encryptedPwd);

		String ret = super.save(user);
		return ret;
	}

	@Override
	protected String remove(User user) throws Exception {
		return super.remove(user);
	}
}