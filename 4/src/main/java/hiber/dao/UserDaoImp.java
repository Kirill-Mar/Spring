package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import hiber.HibernateUtil.HibernateUtil;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      Session session = sessionFactory.getCurrentSession();
      session.save(user);
   }

   @Override
   public List<User> listUsers() {
      Session session = sessionFactory.getCurrentSession();
      TypedQuery<User> query = session.createQuery("from User", User.class);
      return query.getResultList();
   }
   @Override
   public User getUserByCarModelAndSeries(String model, int series) {
      Session session = sessionFactory.getCurrentSession();
      TypedQuery<User> query = session.createQuery("select u from User u where u.car.model = :model and u.car.series = :series", User.class);
      query.setParameter("model", model);
      query.setParameter("series", series);
      List<User> resultList = query.getResultList();
      return resultList.isEmpty() ? null : resultList.get(0);
   }
}

