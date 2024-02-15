//package com.takamol.roboagent.gateway.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.http.ResponseEntity;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.takamol.roboagent.gateway.models.UserVO;
//import com.takamol.roboagent.gateway.models.WebResponseBody;
//import com.takamol.roboagent.gateway.service.UserRepository;
//import com.takamol.roboagent.gateway.utils.ComplaintUtils;
//import com.takamol.roboagent.gateway.utils.JwtTokenUtil;
//
//@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "*")
//@RestController
//@RequestMapping("/api/complaints")
//public class ComplaintsController {
//
//	@Autowired
//	private UserRepository userRepository;
//
//	@Autowired
//	private JwtTokenUtil jwtTokenUtil;
//
//	@GetMapping
//	public ResponseEntity<String> findAllUsers(@RequestParam(name = "pageNo", defaultValue = "0") Integer page,
//			@RequestParam(name = "pageSize", defaultValue = "10") Integer size,
//			@RequestParam(name = "colSort", defaultValue = "complaintId") String sort,
//			@RequestHeader MultiValueMap<String, String> headers) {
//
//		PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sort).ascending());
//
//		WebResponseBody responseBody = new WebResponseBody();
//
//		UserVO user = jwtTokenUtil.getUserFromToken(ComplaintUtils.removeFirstandLast(headers.get("authorization").get(0)));
//		System.out.println("user =-- " + user.toString());
//		if (user.getIsAdmin()) {
//			responseBody.setUsers(userRepository.findAll(pageRequest).getContent());
//
//		} else {
////			responseBody.setComplaints(userRepository.getPageComplaintByUserId("" + user.getUserId(), pageRequest).getContent());
//		}
//		responseBody.setResult("0");
//		responseBody.setResultDescription("Success");
//		return ComplaintUtils.responseGenerate(responseBody);
//
//	}
////
////	@PostMapping
////	ResponseEntity<String> newComplaint(@RequestBody ComplaintVO newComplaint,
////			@RequestHeader MultiValueMap<String, String> headers) {
////
////		WebResponseBody responseBody = new WebResponseBody();
////
////		try {
////
////			newComplaint.setComplaintId(complaintsRepository.getMaxComplaintId() + 1);
////			newComplaint.setStatus("Pending");
////			UserVO user = jwtTokenUtil.getUserFromToken(ComplaintUtils.removeFirstandLast(headers.get("authorization").get(0)));
////
////			if (user != null) {
////				newComplaint.setUserId("" + user.getUserId()); // set the userId
////				newComplaint.setUpdatedByUserId("" + user.getUserId()); // set the userId
////				responseBody.getComplaints().add(complaintsRepository.save(newComplaint));
////				responseBody.setUser(user);
////				responseBody.setResult("0");
////				responseBody.setResultDescription("Success");
////
////			} else {
////				responseBody.setResult("104");// User not found
////				responseBody.setResultDescription("Failed - No user login");
////			}
////		} catch (Exception e) {
////			return ComplaintUtils.responseGenerate(responseBody);
////		}
////		return ComplaintUtils.responseGenerate(responseBody);
////	}
////
////	@GetMapping("/{id}")
////	ResponseEntity<String> findById(@PathVariable Long id, @RequestHeader MultiValueMap<String, String> headers) {
////		WebResponseBody responseBody = new WebResponseBody();
////		try {
////			responseBody.getComplaints().add(complaintsRepository.findById(id).orElse(null));
////
////			if (responseBody.getComplaints() != null) {
////				responseBody.setResult("0");
////				responseBody.setResultDescription("Success");
////			} else {
////				responseBody.setResult("-1");
////				responseBody.setResultDescription("Failed - Not found !");
////			}
////		} catch (Exception e) {
////			return ComplaintUtils.responseGenerate(responseBody);
////		}
////		return ComplaintUtils.responseGenerate(responseBody);
////
////	}
//
//	@PutMapping("/{id}")
//	ResponseEntity<String> replaceEmployee(@RequestBody ComplaintVO newComplaint, @PathVariable Long id,
//			@RequestHeader MultiValueMap<String, String> headers) {
//		WebResponseBody responseBody = new WebResponseBody();
//
//		try {
//			UserVO user = jwtTokenUtil.getUserFromToken(ComplaintUtils.removeFirstandLast(headers.get("authorization").get(0)));
//
//			responseBody.getComplaints().add(complaintsRepository.findById(id).map(complaint -> {
//				complaint.setSubject(newComplaint.getSubject());
//				complaint.setComplaintType(newComplaint.getComplaintType());
//				complaint.setUpdatedByUserId("" + user.getUserId());
//				responseBody.setResult("0");
//				responseBody.setResultDescription("Success");
//				return complaintsRepository.save(complaint);
//			}).orElse(null));
//
//			if (responseBody.getComplaints() != null) {
//				responseBody.setResult("0");
//				responseBody.setResultDescription("Success");
//			} else {
//				responseBody.setResult("-1");
//				responseBody.setResultDescription("Failed - Not found !");
//			}
//		} catch (Exception e) {
//			return ComplaintUtils.responseGenerate(responseBody);
//		}
//		return ComplaintUtils.responseGenerate(responseBody);
//
//	}
//
//	@PutMapping("/updatestatus/{id}")
//	ResponseEntity<String> updatestatus(@RequestBody ComplaintVO newComplaint, @PathVariable Long id,
//			@RequestHeader MultiValueMap<String, String> headers) {
//
//		WebResponseBody responseBody = new WebResponseBody();
//		try {
//
//			UserVO user = jwtTokenUtil.getUserFromToken(ComplaintUtils.removeFirstandLast(headers.get("authorization").get(0)));
//
//			responseBody.getComplaints().add(complaintsRepository.findById(id).map(complaint -> {
//				complaint.setStatus(newComplaint.getStatus());
//				complaint.setOpenedBy(user.getEmail());
//				complaint.setUpdatedByUserId("" + user.getUserId());
//
//				return complaintsRepository.save(complaint);
//			}).orElse(null));
//			if (responseBody.getComplaints() != null) {
//				responseBody.setResult("0");
//				responseBody.setResultDescription("Success");
//			} else {
//				responseBody.setResult("105");
//				responseBody.setResultDescription("Failed - Complaint not found");
//			}
//		} catch (Exception e) {
//			return ComplaintUtils.responseGenerate(responseBody);
//		}
//		return ComplaintUtils.responseGenerate(responseBody);
//
//	}
//}