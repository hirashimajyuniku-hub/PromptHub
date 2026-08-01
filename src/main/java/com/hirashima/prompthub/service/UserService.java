package com.hirashima.prompthub.service;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hirashima.prompthub.exception.DuplicateEmailException;
import com.hirashima.prompthub.form.LoginForm;
import com.hirashima.prompthub.form.SignupForm;
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

	public UserModel findByEmail(String email) {
    return userRepository.findByEmail(email)
            .orElseThrow();            
            }
	
	
	
	public void signup(SignupForm form) {
		
	    Optional<UserModel> existingUser =
	            userRepository.findByEmail(form.getEmail());

	    if (existingUser.isPresent()) {
	        throw new DuplicateEmailException(
	                "このメールアドレスは既に登録されています"
	        );
	    }

	    UserModel user = new UserModel();
	    LocalDateTime now = LocalDateTime.now();
	    
		
	    user.setUsername(form.getUsername());
	    user.setEmail(form.getEmail());
	    user.setPassword(
	            passwordEncoder.encode(form.getPassword())
	    );
	    user.setCreatedAt(now);
	    user.setUpdatedAt(now);
	    user.setRole("ROLE_USER");
		user.setDisplayName(form.getUsername());
		
		

		
	    userRepository.save(user);
	}
}
