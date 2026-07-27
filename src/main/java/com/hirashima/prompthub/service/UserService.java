package com.hirashima.prompthub.service;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hirashima.prompthub.form.LoginForm;
import com.hirashima.prompthub.model.UserModel;
import com.hirashima.prompthub.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
//	ログイン処理
public UserModel login(LoginForm form) {
	
	Optional<UserModel> user = userRepository.findByEmail(form.getEmail());
	
	if(user.isPresent()) {
		
		UserModel loginUser = user.get();
		
		
		if(passwordEncoder.matches(form.getPassword(),loginUser.getPassword())) {
			
			return loginUser;
		}
			return null;
	}
	return null;
}
}
