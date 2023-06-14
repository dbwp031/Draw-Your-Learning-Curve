package com.dbwp031.dylc.auth.clients;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application-oauth.properties")
public class ClientGithub {
    @Value("${spring.security.oauth2.client.registration.github.client-id}")
    private String githubClientId = null;

    @Value("${spring.security.oauth2.client.registration.github.client-secret}")
    private String githubClientSecret = null;

    private ClientRegistration clientRegistrationGithub;

    public ClientRegistration getGithubClientRegistration() {
        return clientRegistrationGithub;
    }

    @PostConstruct
    public void init() {
        System.out.println("ClientGithub PostConstruct");

        clientRegistrationGithub = ClientRegistration
                .withRegistrationId("github")
                .clientId(githubClientId)
                .clientSecret(githubClientSecret)
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
                .scope("profile,email")
                .authorizationUri("https://github.com/login/oauth/authorize")
                .tokenUri("https://github.com/login/oauth/access_token")
                .userInfoUri("https://api.github.com/user")
                .userNameAttributeName("login")
                .clientName("Github")
                .build();
    }

}