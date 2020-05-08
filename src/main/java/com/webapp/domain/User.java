package com.webapp.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.UniqueElements;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer uid;

	@UniqueElements
	@Column(length=30)
	@NotNull
	private String account;
	@Column(length=30)
	@NotNull
	private String password;
	@Column(length=30)
	private String name;
	public enum Sex{
		男, 女;
		public static List<String> toList(){
			Sex[] sex = Sex.values();
			List<String> datas = new ArrayList<>();
			for(Sex s : sex) {
				datas.add(s.name());
			}
			return datas;
		}
	};
	private Sex gender;
	private LocalDate birthday;
	@Column(length=11)
	private String mobile;
	@Column(length=100)
	private String email;
	private LocalDate lasttime;
	private Integer logincount;
	private Integer validstate=1;
	
	
	
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Sex getGender() {
		return gender;
	}
	public void setGender(Sex gender) {
		this.gender = gender;
	}
	public LocalDate getBirthday() {
		return birthday;
	}
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDate getLasttime() {
		return lasttime;
	}
	public void setLasttime(LocalDate lasttime) {
		this.lasttime = lasttime;
	}
	public Integer getLogincount() {
		return logincount;
	}
	public void setLogincount(Integer logincount) {
		this.logincount = logincount;
	}
	public Integer getValidstate() {
		return validstate;
	}
	public void setValidstate(Integer validstate) {
		this.validstate = validstate;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		return true;
	}
	
}
