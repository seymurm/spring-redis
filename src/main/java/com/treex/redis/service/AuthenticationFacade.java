package com.treex.redis.service;

import org.springframework.security.core.Authentication;

/**
 * Created by seymurmanafov on 2020. 05. 25..
 */
public interface AuthenticationFacade {

    Authentication getAuthentication();

}
