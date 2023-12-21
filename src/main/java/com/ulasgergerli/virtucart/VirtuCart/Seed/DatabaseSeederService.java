package com.ulasgergerli.virtucart.VirtuCart.Seed;

import com.ulasgergerli.virtucart.VirtuCart.User.Role;
import com.ulasgergerli.virtucart.VirtuCart.User.User;
import com.ulasgergerli.virtucart.VirtuCart.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseSeederService {

    private final UserRepository userRepository;

    public DatabaseSeederService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void seedDatabase() {

        if(userRepository.count() > 0) {
            return;
        }

        var superAdmin = new User();
        superAdmin.setName("Super");
        superAdmin.setSurname("Admin");
        superAdmin.setEmail("hi@verdantdev.co");
        superAdmin.setPassword("123qwe");
        superAdmin.setRole(Role.SUPERADMIN);

        userRepository.save(superAdmin);
    }
}
