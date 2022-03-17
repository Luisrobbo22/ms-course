package br.com.robbo.hroauth.service;

import br.com.robbo.hroauth.entities.User;
import br.com.robbo.hroauth.feignClients.UserFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserFeignClient userFeignClient;

    public User findByEmail(String email) throws IllegalAccessException {
        User user = userFeignClient.findByEmail(email).getBody();

        if (user == null) {
            logger.error("Email not found: " + email);
            throw new IllegalAccessException("Email not found");
        }

        logger.info("Email found: " + email);
        return user;
    }
}
