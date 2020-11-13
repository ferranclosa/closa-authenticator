package com.closa.authentication.services;

import com.closa.authentication.dao.*;
import com.closa.authentication.model.*;
import com.closa.global.status.model.enums.Status;
import com.closa.global.status.util.StatusHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class UserLoader implements CommandLineRunner {

    @Autowired
    URolRepository rolRepository;
    @Autowired
    UConRepository conRepository;
    @Autowired
    UAddRepository addRepository;
    @Autowired
    UUsrRepository usrRepository;

    @Autowired
    UCrdRepository crdRepository;

    @Autowired
    StatusHelper statusHelper;

    @Autowired
    PasswordEncoder passwordEncoder ;


    @Override
    public void run(String... args) throws Exception {

        UserRole userRole = null;
        UserConnection userConnection = null;
        UserUser userUser = null;
        UserAddress userAddress = null;
        UserCredentials userCredentials = null;

        userRole = new UserRole();
        userRole.setRoleCode("SYSADMIN");
        userRole.setRoleCodeNumber("90");
        userRole.setRoleDescription("TOP ACCESS");
        userRole.setRoleStatus(statusHelper.setItemStatus(Status.ACTIVE));
        rolRepository.save(userRole);

        userRole = new UserRole();
        userRole.setRoleCode("SYSAUDIT");
        userRole.setRoleCodeNumber("80");
        userRole.setRoleDescription("TOP AUDIT ACCESS");
        userRole.setRoleStatus(statusHelper.setItemStatus(Status.ACTIVE));
        rolRepository.save(userRole);

        userRole = new UserRole();
        userRole.setRoleCode("APPADMIN");
        userRole.setRoleCodeNumber("70");
        userRole.setRoleDescription("TOP APP ACCESS");
        userRole.setRoleStatus(statusHelper.setItemStatus(Status.ACTIVE));
        rolRepository.save(userRole);

        userRole = new UserRole();
        userRole.setRoleCode("APPCONTROLLER");
        userRole.setRoleCodeNumber("60");
        userRole.setRoleDescription("APP CONTROLLER ACCESS");
        userRole.setRoleStatus(statusHelper.setItemStatus(Status.ACTIVE));
        rolRepository.save(userRole);

        userRole = new UserRole();
        userRole.setRoleCode("APPUSER");
        userRole.setRoleCodeNumber("50");
        userRole.setRoleDescription("APP USER");
        userRole.setRoleStatus(statusHelper.setItemStatus(Status.ACTIVE));
        rolRepository.save(userRole);

        userRole = new UserRole();
        userRole.setRoleCode("APPEXTADMIN");
        userRole.setRoleCodeNumber("40");
        userRole.setRoleDescription("APP EXTERNAL ADMIN ACCESS");
        userRole.setRoleStatus(statusHelper.setItemStatus(Status.ACTIVE));
        rolRepository.save(userRole);

        userRole = new UserRole();
        userRole.setRoleCode("APPEXTUSER");
        userRole.setRoleCodeNumber("30");
        userRole.setRoleDescription("APP EXTERNAL USER ACCESS");
        userRole.setRoleStatus(statusHelper.setItemStatus(Status.ACTIVE));
        rolRepository.save(userRole);

        userRole = new UserRole();
        userRole.setRoleCode("APPBROWSER");
        userRole.setRoleCodeNumber("20");
        userRole.setRoleDescription("APP BROWSER ACCESS");
        userRole.setRoleStatus(statusHelper.setItemStatus(Status.ACTIVE));
        rolRepository.save(userRole);

        userUser = new UserUser();
        userUser.setFirstName("DEFAULT");
        userUser.setLastName("ADMIN");
        userUser.addRole(rolRepository.findByRoleCode("SYSADMIN"));
        userUser.addRole(rolRepository.findByRoleCode("SYSAUDIT"));

        userAddress = new UserAddress("admin@hsbc.fr", true, userUser, "103 Champs Elysées, 75008 Paris, France");
        addRepository.save(userAddress);

        userUser.addAddress(userAddress);
        usrRepository.save(userUser);
        userCredentials = new UserCredentials(userUser, passwordEncoder.encode("AdminPassword"));
        crdRepository.save(userCredentials);

        userConnection = new UserConnection();
        userConnection.setConnectionId("ferran.closa@hsbc.com");

        userConnection.setConnectionUser(userUser);
        userConnection.setConnectionUserCredentials(userCredentials);
        userConnection.setConnectionStatus(statusHelper.setItemStatus(Status.ACTIVE));
        conRepository.save(userConnection);

        userUser = new UserUser();
        userUser.setFirstName("DEFAULT");
        userUser.setLastName("USER");
        userUser.addRole(rolRepository.findByRoleCode("APPCONTROLLER"));
        userUser.addRole(rolRepository.findByRoleCode("APPUSER"));


        userAddress = new UserAddress("user@total.fr", true, userUser, "6 Rue Dombasle, 75015 Paris, France");
        addRepository.save(userAddress);

        userUser.addAddress(userAddress);
        usrRepository.save(userUser);
        userCredentials = new UserCredentials(userUser, passwordEncoder.encode("UserPassword"));
        crdRepository.save(userCredentials);

        userConnection = new UserConnection();
        userConnection.setConnectionId("04695798");
        userConnection.setConnectionUser(userUser);
        userConnection.setConnectionUserCredentials(userCredentials);
        userConnection.setConnectionStatus(statusHelper.setItemStatus(Status.ACTIVE));
        conRepository.save(userConnection);
    }
}
