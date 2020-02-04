package com.spring.jerseydemo.Dao;

import com.spring.jerseydemo.Model.User;
import org.springframework.stereotype.Repository;

import javax.ws.rs.core.Response;
import java.util.List;

@Repository
public interface UserRepository {


    List<User> getUsers();

    String insertUsers();

    String createUser(User user);

    User findById(int id);

    Response update(User user);

}