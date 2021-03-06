package hristian.iliev.airsofter.controllers.rest;

import hristian.iliev.airsofter.contracts.IUsersService;
import hristian.iliev.airsofter.models.User;
import hristian.iliev.airsofter.models.request.Names;
import hristian.iliev.airsofter.models.request.Passwords;
import hristian.iliev.airsofter.models.response.Bool;
import hristian.iliev.airsofter.models.response.Conversation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;
import java.security.Principal;

@RestController
@RequestMapping("/api")
public class UsersController {
  private final IUsersService usersService;

  @Autowired
  public UsersController(IUsersService usersService) {
    this.usersService = usersService;
  }

  @RequestMapping(value = "/register", method = RequestMethod.POST)
  @ResponseBody
  public User register(@RequestBody User user) {
    return this.usersService.register(user);
  }

  @RequestMapping(value = "/getCurrentUser", method = RequestMethod.GET)
  @ResponseBody
  public Conversation getCurrentUser(Principal principal) {
    String userEmail = principal.getName();
    User current = this.usersService.getUserByEmail(userEmail);
    return new Conversation(current, this.usersService.getById(current.getLastConversationWith()));
  }

  @RequestMapping(value = "/changeNames", method = RequestMethod.POST)
  @ResponseBody
  public User changeNames(@RequestBody Names names, Principal principal) {
    String userEmail = principal.getName();
    User current = this.usersService.getUserByEmail(userEmail);
    return this.usersService.changeNames(current, names);
  }

  @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
  @ResponseBody
  public User changePassword(@RequestBody Passwords passwords, Principal principal) {
    String userEmail = principal.getName();
    User current = this.usersService.getUserByEmail(userEmail);
    return this.usersService.changePasswords(current, passwords);
  }

  @RequestMapping(value = "/installationCompleted", method = RequestMethod.GET)
  public void installationCompleted(Principal principal) {
    String userEmail = principal.getName();
    User current = this.usersService.getUserByEmail(userEmail);

    this.usersService.installationCompleted(current);
  }
}
