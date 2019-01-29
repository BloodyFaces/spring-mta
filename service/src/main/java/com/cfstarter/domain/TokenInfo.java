package com.cfstarter.domain;

import java.util.List;

public class TokenInfo {
	
	private String header;
	
	private String payload;
	
	private String signature;
	
	private String token;
	
	private List<ClaimInfo> claimList;

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<ClaimInfo> getClaimList() {
		return claimList;
	}

	public void setClaimList(List<ClaimInfo> claimList) {
		this.claimList = claimList;
	}
	
}
