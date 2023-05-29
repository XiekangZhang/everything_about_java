package de.xiekang.oauth.model;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class AppDataRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Client getClient(String clientId) {
        return entityManager.find(Client.class, clientId);
    }

    public User getUser(String userId) {
        return entityManager.find(User.class, userId);
    }

    @Transactional
    public AuthorizationCode save(AuthorizationCode authorizationCode) {
        entityManager.persist(authorizationCode);
        return authorizationCode;
    }
}
