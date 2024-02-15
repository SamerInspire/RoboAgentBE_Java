package com.takamol.roboagent.gateway.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.domain.Page;

@Entity
@Table(name = "Users")
public class UserVO {
	@Id
	@Column(name = "user_id")
	private Long userId;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "password")
	private String password;

	@Column(name = "email")
	private String email;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "status")
	private String status;

	@Column(name = "role")
	private String role;

	@Column(name = "is_admin")
	private boolean isAdmin;

	@Column(name = "full_name")
	private String fullName;

	@Transient
	private List<UserVO> users = new ArrayList<UserVO>();
	@Transient
	private Page<UserVO> usersPage = null;
	@Transient
	private transient List<AuthoritiesVO> authorities = new ArrayList<AuthoritiesVO>();

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void generateFullName() {
		this.fullName = this.firstName + " " + this.middleName + " " + this.lastName;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean getIsAdmin() {
		return isAdmin;
	}

	public List<AuthoritiesVO> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<AuthoritiesVO> authorities) {
		this.authorities = authorities;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public List<UserVO> getUsers() {
		return users;
	}

	public void setUsers(List<UserVO> users) {
		this.users = users;
	}

	public Page<UserVO> getUsersPage() {
		return usersPage;
	}

	public void setUsersPage(Page<UserVO> usersPage) {
		this.usersPage = usersPage;
	}

	@Override
	public String toString() {
		return "UserVO [userId=" + userId + ", userName=" + userName + ", password=" + password + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + ", firstName=" + firstName + ", middleName=" + middleName
				+ ", lastName=" + lastName + ", status=" + status + ", role=" + role + ", isAdmin=" + isAdmin
				+ ", fullName=" + fullName + ", users=" + users + ", usersPage=" + usersPage + "]";
	}

}