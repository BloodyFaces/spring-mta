package com.cfstarter.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import com.sap.cloud.sdk.cloudplatform.security.Authorization;
import com.sap.cloud.sdk.cloudplatform.security.user.ScpCfUser;
import com.sap.cloud.sdk.cloudplatform.security.user.UserAccessor;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cfstarter.domain.ClaimInfo;
import com.cfstarter.domain.TokenInfo;
import com.sap.cloud.sdk.cloudplatform.security.AuthToken;
import com.sap.cloud.sdk.cloudplatform.security.AuthTokenAccessor;

@Service
public class SecurityService {

	public List<String> getScopes() {
		List<String> scopeList = new ArrayList<String>();
		ScpCfUser user = (ScpCfUser)UserAccessor.getCurrentUser();
		Set<Authorization> scopes = user.getAuthorizations();
		scopes.forEach(scope -> {
			scopeList.add(scope.getName());
		});
		return scopeList;
	}

	public void userHasAuthorization(String authorization) {
		if (!UserAccessor.getCurrentUser().hasAuthorization(new Authorization(authorization))) {
			throw new AccessDeniedException("User action is not permitted! Insufficient privilege!");
		}
	}
	
	public TokenInfo getTokenInfo() {
		Optional<AuthToken> auth = AuthTokenAccessor.getCurrentToken();
		if (!auth.isPresent()) {
			return null;
		}
		DecodedJWT jwtToken = auth.get().getJwt();
		TokenInfo tokenInfo = new TokenInfo();
		List<ClaimInfo> claimList = new ArrayList<ClaimInfo>();
		Map<String, Claim> claimMap = jwtToken.getClaims();
		claimMap.forEach((key, value) -> {
			ClaimInfo claimInfo = new ClaimInfo();
			claimInfo.setName(key);
			claimInfo.setValue(value.asString());
			claimList.add(claimInfo);
		});
		tokenInfo.setHeader(jwtToken.getHeader());
		tokenInfo.setPayload(jwtToken.getPayload());
		tokenInfo.setSignature(jwtToken.getSignature());
		tokenInfo.setToken(jwtToken.getToken());
		tokenInfo.setClaimList(claimList);
		return tokenInfo;
	}
	
}
