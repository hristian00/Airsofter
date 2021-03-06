package hristian.iliev.airsofter.utils;

import hristian.iliev.airsofter.models.Arena;
import hristian.iliev.airsofter.models.ArenaCategory;
import hristian.iliev.airsofter.models.ArenaPhoto;
import hristian.iliev.airsofter.models.Request;
import hristian.iliev.airsofter.models.Team;
import hristian.iliev.airsofter.models.Timetable;
import hristian.iliev.airsofter.models.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
  private static SessionFactory sessionFactory;

  static {
    try {
      Configuration configuration = new Configuration().configure();

      configuration.addAnnotatedClass(User.class);
      configuration.addAnnotatedClass(ArenaCategory.class);
      configuration.addAnnotatedClass(Arena.class);
      configuration.addAnnotatedClass(Timetable.class);
      configuration.addAnnotatedClass(Request.class);
      configuration.addAnnotatedClass(ArenaPhoto.class);
      configuration.addAnnotatedClass(Team.class);

      StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
              .applySettings(
                      configuration.getProperties())
              .build();

      sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    } catch (Exception ex) {
      System.out.println("----------------------");
      System.out.println(ex);
    }
  }

  public static SessionFactory getSessionFactory() {
    return sessionFactory;
  }
}
