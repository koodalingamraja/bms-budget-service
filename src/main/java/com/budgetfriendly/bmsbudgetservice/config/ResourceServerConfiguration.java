package com.budgetfriendly.bmsbudgetservice.config;


import com.budgetfriendly.bmsbudgetservice.constants.BudgetConstant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	private TokenStore tokenStore;

	@Value("${oauth-private-key}")
	private String privateKey;

	@Value("${oauth-public-key}")
	private String publicKey;

	@Override
	public void configure(final ResourceServerSecurityConfigurer resources) {
		resources.tokenStore(tokenStore());
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/**").permitAll();
	}

	@Bean
	public DefaultTokenServices tokenServices(final TokenStore tokenStore) {
		DefaultTokenServices tokenServices = new DefaultTokenServices();
		tokenServices.setTokenStore(tokenStore);
		return tokenServices;
	}

	@Bean
	public TokenStore tokenStore() {
		if (tokenStore == null) {
			tokenStore = new JwtTokenStore(jwtAccessTokenConverter());
		}
		return tokenStore;
	}

	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		
		JwtAccessTokenConverter jwtAccessTokenConverter = new YourTokenEnhancer();
        jwtAccessTokenConverter.setSigningKey(getPrivateAsString());
        jwtAccessTokenConverter.setVerifier(new RsaVerifier(getPublicAsString()));
        
		return jwtAccessTokenConverter;
	}

	private String getPublicAsString() {
		try {

			Resource resource = new ClassPathResource(publicKey);
			byte[] bdata = FileCopyUtils.copyToByteArray(resource.getInputStream());
			return new String(bdata, StandardCharsets.UTF_8);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private String getPrivateAsString() {
		try {

			Resource resource = new ClassPathResource(privateKey);
			byte[] bdata = FileCopyUtils.copyToByteArray(resource.getInputStream());
			return new String(bdata, StandardCharsets.UTF_8);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}


}
