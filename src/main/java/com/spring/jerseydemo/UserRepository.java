package com.spring.jerseydemo;

import org.springframework.stereotype.Repository;

import javax.ws.rs.core.Response;
import java.util.List;

@Repository
public interface UserRepository {


    List<User> getUsers();

    String insertUsers();

}