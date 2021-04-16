package cmtyMgmtSys.dao;

import cmtyMgmtSys.entity.Authorities;
import cmtyMgmtSys.entity.Resident;
import cmtyMgmtSys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@Repository
public class ResidentDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void addResident(Resident resident) {
        Authorities authorities = new Authorities();
        authorities.setAuthorities("ROLE_USER");
        authorities.setEmailId(resident.getUser().getEmailId());
        Session session = null;
        
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(authorities);
            session.save(resident);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public Resident getResidentByUserName(String username) {
        User user = null;
        try (Session session = sessionFactory.openSession()) {
            user = session.get(User.class, username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (user != null) {
            return user.getResident();
        }
        return null;
    }
}
