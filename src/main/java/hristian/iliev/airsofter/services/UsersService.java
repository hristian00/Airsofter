package hristian.iliev.airsofter.services;

import hristian.iliev.airsofter.contracts.IRepository;
import hristian.iliev.airsofter.contracts.IUsersService;
import hristian.iliev.airsofter.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UsersService implements IUsersService {
  private final IRepository<User> usersRepository;
  private final PasswordEncoder passwordEncoder;

  public UsersService(IRepository<User> usersRepository,
                      PasswordEncoder passwordEncoder) {
    this.usersRepository = usersRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public User create(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return this.usersRepository.create(user);
  }

  @Override
  public User getUserByUsername(String username) {
    return usersRepository.getAll()
            .stream()
            .filter(u -> u.getEmail().equals(username))
            .findFirst()
            .orElse(null);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = this.getUserByUsername(username);
    return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
  }
}
