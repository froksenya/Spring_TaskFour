package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserByCarsModelAndSeries(String model, int series) {
      String hql = "from User as u where u.car.model =: modelCar and u.car.series =: seriesCar";
      Query query = sessionFactory.getCurrentSession().createQuery(hql);
      return (User) query.setParameter("modelCar", model)
              .setParameter("seriesCar", series).getSingleResult();

   }

}
