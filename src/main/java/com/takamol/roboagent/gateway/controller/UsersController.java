package com.takamol.roboagent.gateway.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.takamol.roboagent.gateway.models.UserVO;
import com.takamol.roboagent.gateway.models.WebResponseBody;
import com.takamol.roboagent.gateway.service.UserRepository;
import com.takamol.roboagent.gateway.utils.ComplaintUtils;
import com.takamol.roboagent.gateway.utils.JwtTokenUtil;
import com.mysql.cj.util.StringUtils;

@CrossOrigin(origins = "*", exposedHeaders = "*")
@RestController
@RequestMapping("/api/users")
public class UsersController {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@PostMapping("/signup")
	public ResponseEntity<String> signUp(@Validated @RequestBody UserVO user) {
//		try {
//			TimeUnit.SECONDS.sleep(2);
//		} catch (InterruptedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}

		WebResponseBody responseBody = new WebResponseBody();
		try {

			responseBody.setComplaints(null);

			responseBody.setResultDescription(ComplaintUtils.checkUserValidation(user, userRepository));

			if (!StringUtils.isEmptyOrWhitespaceOnly(responseBody.getResultDescription())) {
				responseBody.setResult("103");

				return ComplaintUtils.responseGenerate(responseBody);
			}
			user.setUserName(user.getEmail().split("@")[0]);
			user.setIsAdmin(false);
			user.setUserId((Long) userRepository.getMaxUserId() + 1);
			user.setPassword(JwtTokenUtil.encryptData(user.getPassword()));
			user.setStatus("Pending Approval");
			userRepository.save(user);

			responseBody.setUser(user);

			responseBody.setResult("0");
			responseBody.setResultDescription("Success- Request sent Pending Approval");

		} catch (Exception e) {
			e.printStackTrace();
			return ComplaintUtils.responseGenerate(responseBody);
		}
		return ComplaintUtils.responseGenerate(responseBody);

	}

	@PostMapping("/signupforadmin")
	public ResponseEntity<String> signUpForAdmin(@RequestBody UserVO user) {
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		WebResponseBody responseBody = new WebResponseBody();
		try {
			responseBody.setComplaints(null);

			responseBody.setResultDescription(ComplaintUtils.checkUserValidation(user, userRepository));

			if (!StringUtils.isEmptyOrWhitespaceOnly(responseBody.getResultDescription())) {
				responseBody.setResult("103");

				return ComplaintUtils.responseGenerate(responseBody);
			}

			user.setIsAdmin(true);
//			user.setUserId(userRepository.getMaxUserId() + 1);
//			user.setPassword(JwtTokenUtil.encryptData(user.getPassword()));
			userRepository.save(user);

			responseBody.setResult("0");
			responseBody.setResultDescription("Success");
			responseBody.setUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			return ComplaintUtils.responseGenerate(responseBody);
		}
		return ComplaintUtils.responseGenerate(responseBody);

	}

	@GetMapping("/currentuser")
	public ResponseEntity<String> currentUser(@RequestHeader MultiValueMap<String, String> headers) {
		WebResponseBody responseBody = new WebResponseBody();
		try {
			responseBody.setComplaints(null);
			String token = headers.get("authorization").get(0);
			token = token.startsWith("\"") ? ComplaintUtils.removeFirstandLast(token) : token;
			UserVO user = jwtTokenUtil.getUserFromToken(token);
			if (user != null && user.getUserName() != null) {
				responseBody.setUser(user);
				responseBody.setResult("0");
				responseBody.setResultDescription("Success");
			} else {
				responseBody.setResult("101");
				responseBody.setResultDescription("Failed - Invalied JWT Token");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ComplaintUtils.responseGenerate(responseBody);
		}
		return ComplaintUtils.responseGenerate(responseBody);
	}

	@PostMapping("/signin")
	public ResponseEntity<String> signIn(@RequestBody UserVO user) {
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		WebResponseBody responseBody = new WebResponseBody();
		responseBody.setComplaints(null);

		try {
			System.out.println("password ===> " + user.toString());
			user.setPassword(JwtTokenUtil.encryptData(user.getPassword()));
			System.out.println("password ===> " + user.toString());
			UserVO regUser = userRepository.findByEmail(user.getEmail());
			if (regUser == null) {
				responseBody.setResult("101");
				responseBody.setResultDescription("Failed - The email is not registered");
			} else {
//				System.out.println("should be password ===> " + JwtTokenUtil.decryptData(regUser.getPassword()));
//				System.out.println("should be password regUser===> " + regUser.getPassword());
//				System.out.println("should be password user===> " + user.getPassword());
//			user = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
				if (regUser.getPassword().equals(user.getPassword())) {
					responseBody.setResult("0");
					responseBody.setResultDescription("Hi, Welcome " + regUser.getUserName() + " to RoboAgent");
					responseBody.setUser(regUser);
				} else {
					responseBody.setResult("102");
					responseBody.setResultDescription("Failed - Email/Password not correct");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ComplaintUtils.responseGenerate(responseBody);
		}

		return ComplaintUtils.responseGenerate(responseBody);
	}

	@GetMapping("/getallusers")
	public ResponseEntity<String> findAllUsers(@RequestParam(name = "pageNo", defaultValue = "0") Integer page,
			@RequestParam(name = "pageSize", defaultValue = "10") Integer size,
			@RequestParam(name = "colSort", defaultValue = "user_id") String sort,
			@RequestHeader MultiValueMap<String, String> headers) {

		PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sort).ascending());

		WebResponseBody responseBody = new WebResponseBody();

		UserVO user = jwtTokenUtil
				.getUserFromToken(ComplaintUtils.removeFirstandLast(headers.get("authorization").get(0)));
		System.out.println("user =-- " + user.toString());
		if (user.getIsAdmin()) {
			responseBody.setUsers(userRepository.findAll(pageRequest).getContent());

		} else {
//			responseBody.setComplaints(
//					complaintsRepository.getPageComplaintByUserId("" + user.getUserId(), pageRequest).getContent());
		}
		responseBody.setResult("0");
		responseBody.setResultDescription("Success");
		return ComplaintUtils.responseGenerate(responseBody);

	}
}
