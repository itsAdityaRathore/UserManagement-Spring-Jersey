package com.spring.jerseydemo;

import org.omg.CORBA.NO_RESPONSE;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.constraints.Null;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Service
public class UserService implements UserRepository {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserJersey");

    @Override
    public List<User> getUsers() {
        EntityManager em = emf.createEntityManager();

        // em.getTransaction().begin();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> stud = cq.from(User.class);
//        cq.select(stud.get("id"));
//        cb.max(stud.get("id"));

        //cq.select(stud.get("firstName"));

        CriteriaQuery<User> select = cq.select(stud);
        TypedQuery<User> q = em.createQuery(select);
        List<User> list = q.getResultList();

        return list;
    }

    @Override
    public String insertUsers() {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        User s1 = new User("/user-management/1", "Aditya", "Rathore");
        User s2 = new User("/user-management/2", "Rahul", "Jain");

        em.persist(s1);
        em.persist(s2);
        em.getTransaction().commit();

        em.close();
        return "Insertion Successful";

    }

    @Override
    public String createUser(User user) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        user.setUri("/user-management/" + user.getId());
        em.getTransaction().commit();
        em.close();

        return "New User : " + user.getFirstName() + " created with ID : " + user.getId();
    }

    @Override
    public User findById(int id) {
        EntityManager em = emf.createEntityManager();
        return em.find(User.class, id);
    }

    @Override
    public Response update(User user) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        User updateUser = em.find(User.class, user.getId());

        if (user.getFirstName() == null || user.getLastName() == null) {
            System.out.println("Im Inside");
            return Response.status(501).entity("Please enter Firstname and Lastname").build();
        } else {
            updateUser.setFirstName(user.getFirstName());
            updateUser.setLastName(user.getLastName());
            updateUser.setUri(user.getUri());
            em.persist(updateUser);
            em.getTransaction().commit();
            //Response.status(Response.Status.OK).entity("User Updated").build();
        }

        return Response.status(Response.Status.OK).entity("User Updated : " + updateUser).build();
    }

}