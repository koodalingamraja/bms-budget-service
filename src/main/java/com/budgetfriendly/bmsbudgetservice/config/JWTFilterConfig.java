package com.budgetfriendly.bmsbudgetservice.config;

import com.budgetfriendly.bmsbudgetservice.dto.UserDTO;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.PostConstruct;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.Security;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import io.jsonwebtoken.Claims;

@Component
public class JWTFilterConfig extends OncePerRequestFilter {

    private static final String JWTPREFIX = "Bearer";
    private static final String AUTHORIZATION_HEADER = "Authorization";

    private static final String EMPTY_STRING = "";

    @Value("${subject}")
    private String subject;

    @Value("${issuer}")
    private String issuer;

    private RSAPrivateKey pk;

    public JWTFilterConfig() {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    }

    @PostConstruct
    public void init() {
        try {
            Resource resource = new ClassPathResource("oauth-private.key");
            byte[] bdata = FileCopyUtils.copyToByteArray(resource.getInputStream());
            String privateKey = new String(bdata, StandardCharsets.UTF_8);
            byte[] keyBytes = org.apache.commons.codec.binary.Base64.decodeBase64(privateKey);
            PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory kf = KeyFactory.getInstance("RSA", BouncyCastleProvider.PROVIDER_NAME);
            this.pk = (RSAPrivateKey) kf.generatePrivate(privateKeySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {

            String token = request.getHeader(AUTHORIZATION_HEADER);

            if (StringUtils.isNotBlank(token)) {

                if (!token.startsWith("Bearer")) {

                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token should start with Bearer");

                }

                String accessToken = token.replaceAll(JWTPREFIX, EMPTY_STRING);

                if (accessToken != null && !"".equalsIgnoreCase(accessToken)) {

                    String[] split_string = accessToken.split("\\.");
                    String base64EncodedHeader = split_string[0];
                    String base64EncodedBody = split_string[1];
                    String base64EncodedSignature = split_string[2];

                    if (StringUtils.isBlank(base64EncodedHeader) || StringUtils.isBlank(base64EncodedBody)
                            || StringUtils.isBlank(base64EncodedSignature)) {

                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token has tampered and unable to proceed further");

                    }

                    Base64 base64Url = new Base64(true);

                    JSONObject jsonHeader = new JSONObject(new String(base64Url.decode(base64EncodedHeader)));

                    if ("none".equalsIgnoreCase(String.valueOf(jsonHeader.get("alg")))) {

                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token has tampered and unable to proceed further");

                    }

                    JSONObject jsonBody = new JSONObject(new String(base64Url.decode(base64EncodedBody)));

                    Date tokenExpAt = new Date(
                            Long.valueOf(String.valueOf(jsonBody.get("exp_str"))));

                    if (tokenExpAt.before(new Date())) {

                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token is expired and could not proceed further");

                    }

                    Claims claims = Jwts.parser().setSigningKey(pk).parseClaimsJws(accessToken).getBody();

                    if (claims.getSubject() != null && !subject.equalsIgnoreCase(claims.getSubject())
                            && claims.getIssuer() != null && issuer.equalsIgnoreCase(claims.getIssuer())) {

                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token format, could validate further");

                    }

                    Jws parseClaimsJws = Jwts.parser().setSigningKey(pk).parseClaimsJws(accessToken);

                    if (!(parseClaimsJws.getHeader() != null && parseClaimsJws.getBody() != null
                            && parseClaimsJws.getSignature() != null)) {

                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token format, could validate further");

                    }else{

                        ObjectMapper objectMapper = new ObjectMapper();
                        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
                        UserDTO userDetail = objectMapper.readValue(new String(base64Url.decode(base64EncodedBody)),
                                UserDTO.class);


                        UserContextHolder.setUserDto(userDetail);


                    }

                }else {

                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Token can not to be empty");

                }

//            }else {
//
//
//                boolean flag=true;
//
//                if ("/bakery-service/bakery/addBakeryDet".equalsIgnoreCase(request.getRequestURI())) {
//
//                    flag=false;
//                }
//
//                if (request.getRequestURI().equalsIgnoreCase("/bakery-service/v2/api-docs")) {
//                    flag=false;
//                }
//                if (request.getRequestURI().startsWith("/bakery-service/configuration")) {
//                    flag=false;
//                }
//                if (request.getRequestURI().startsWith("/bakery-service/swagger")) {
//                    flag=false;
//                }
//                if (request.getRequestURI().equalsIgnoreCase("/bakery-service/v2/api-docs")) {
//                    flag=false;
//                }
//                if (request.getRequestURI().startsWith("/bakery-service/webjars")) {
//                    flag=false;
//                }
//
//                if(flag) {
//
//                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Token can not to be empty");
//
//                }
//
           }

            filterChain.doFilter(request, response);
        }catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

}
