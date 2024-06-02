package com.ams.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class ITTeam {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long itteamid;

	private String name;

	private String email;

	private String password;

	private String location;

	private String phoneno;

	@OneToMany(mappedBy = "itteam", cascade = CascadeType.ALL)
	private List<Ticket> ticket;

	public ITTeam(Long itteamid, String name, String email, String password, String location, String phoneno,
			List<Ticket> ticket) {
		super();
		this.itteamid = itteamid;
		this.name = name;
		this.email = email;
		this.password = password;
		this.location = location;
		this.phoneno = phoneno;
		this.ticket = ticket;
	}

	public ITTeam() {
		super();

	}

	public Long getItteamid() {
		return itteamid;
	}

	public void setItteamid(Long itteamid) {
		this.itteamid = itteamid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

}
