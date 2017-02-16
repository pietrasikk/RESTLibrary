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
    public User addNewUser(User newUser) {
        return insertOrUpdate(newUser);
    }

    @Override
    public void removeUser(User user) {
        if (entityManager.find(User.class, user.getId()) != null) {
            entityManager.remove(user);
        }
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

    private User insertOrUpdate(User user) {
        return entityManager.merge(user);
    }
}
