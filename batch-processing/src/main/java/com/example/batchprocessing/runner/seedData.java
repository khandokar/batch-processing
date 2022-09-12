package com.example.batchprocessing.runner;

import com.example.batchprocessing.models.EnumRole;
import com.example.batchprocessing.models.Role;
import com.example.batchprocessing.repository.RoleRepository;
import com.example.batchprocessing.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class seedData implements ApplicationRunner{

    private static final Logger log = LoggerFactory.getLogger(seedData.class);

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private StudentRepository studyRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {

       if(!roleRepository.findByName(EnumRole.ROLE_ADMIN).isPresent())
       {
           Role role = new Role();
           role.setName(EnumRole.ROLE_ADMIN);
           roleRepository.saveAndFlush(role);
       }

       if(!roleRepository.findByName(EnumRole.ROLE_USER).isPresent())
       {
           Role role = new Role();
           role.setName(EnumRole.ROLE_USER);
           roleRepository.saveAndFlush(role);
       }

       if(!roleRepository.findByName(EnumRole.ROLE_MODERATOR).isPresent())
       {
           Role role = new Role();
           role.setName(EnumRole.ROLE_MODERATOR);
           roleRepository.saveAndFlush(role);
       }

        studyRepository.deleteAll();
    }
}
