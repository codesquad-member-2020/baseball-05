package com.codesquad.baseball05.application;

import com.codesquad.baseball05.infra.dao.UserDAO;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public String signin(String userId) {
        return userDAO.findByUserId(userId).getUserId();
    }
}
