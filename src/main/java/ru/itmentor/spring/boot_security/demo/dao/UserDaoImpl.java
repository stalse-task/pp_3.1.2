package ru.itmentor.spring.boot_security.demo.dao;


import org.springframework.stereotype.Repository;
import ru.itmentor.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
        entityManager.close();
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public void deleteUser(Long id) {
        User user = entityManager.getReference(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public void editUser(Long id, User user) {
        entityManager.find(User.class, id);
    }

    @Override
    public void userForUpdate(User user, Long id) {
        User update = entityManager.find(User.class, id);
        update.setUsername(user.getUsername());
        update.setName(user.getName());
        update.setLastname(user.getLastname());
        update.setAge(user.getAge());
        update.setEmail(user.getEmail());
        update.setPassword(user.getPassword());
    }

    @Override
    public User getUser(Long id) {

        return entityManager.find(User.class, id);
    }

    @Override
    public User getUserByUsername(String username) {

        return (User) entityManager.createQuery("select u from User u join fetch u.roles where u.username =:username")
                .setParameter("username", username).getSingleResult();
    }
}
