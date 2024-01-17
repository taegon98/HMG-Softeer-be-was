package service;

import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import request.SignUpRequest;
import webserver.RequestHandler;

import java.util.Collection;

import static util.SingletonUtil.getDatabase;

public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);

    // 회원가입 요청을 처리하는 메소드
    public void signUp(SignUpRequest signUpRequest) {
        User user = new User(signUpRequest); // 회원정보 객체 생성
        getDatabase().addUser(user); // 데이터베이스에 회원정보 저장

        // 데이터베이스에 정상적으로 저장되었는지 확인하기 위해 로그를 출력
        logger.debug("Total User Count : {}\n", getDatabase().getUserCount());
        logger.debug("UserId : {}, Password : {}, Name : {}, Email : {}\n",
                signUpRequest.getUserId(), signUpRequest.getPassword(), signUpRequest.getName(), signUpRequest.getEmail());
    }

    // 회원 아이디를 전달받아서 데이터베이스에서 회원정보를 조회하는 메소드
    public User findUserById(String userId) {
        return getDatabase().findUserById(userId);
    }

    // 데이터베이스에 저장된 모든 회원정보를 조회하는 메소드
    public Collection<User> findAll() {
        return getDatabase().findAll();
    }
}