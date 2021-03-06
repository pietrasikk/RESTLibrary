package restlibrary.repository.impl;

import org.springframework.stereotype.Repository;
import restlibrary.model.User;
import restlibrary.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository("userRepository")
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addNewUser(User newUser) {
        insertOrUpdate(newUser);
    }

    @Override
    public void removeUser(Long userId) {
        User user = entityManager.find(User.class, userId);
        entityManager.remove(user);
    }

    @Override
    public List<User> getAllUsers() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = builder.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.select(root);
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public User getUserById(Long userId) {
        return entityManager.find(User.class, userId);
    }

    @Override
    public User updateUser(User user) {
        return insertOrUpdate(user);
    }

    @Override
    public User findByLogin(String login) {
        return entityManager.createQuery("from User u "
                + "where u.login = :login", User.class)
                .setParameter("login", login)
                .getSingleResult();
    }

    private User insertOrUpdate(User user) {
        return entityManager.merge(user);
    }
}
