package com.ftn.es.dto;

import java.util.List;

public class ProfileDTO {
	
	private Long id;

    private String username;

    private String token;

    private List<String> authorities;

    public ProfileDTO() {

    }

    public ProfileDTO(String username, String token, List<String> authorities) {
        super();
        this.username = username;
        this.token = token;
        this.authorities = authorities;
    }
    
    public ProfileDTO(Long id, String username, String token, List<String> authorities) {
        super();
        this.id = id;
        this.username = username;
        this.token = token;
        this.authorities = authorities;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
