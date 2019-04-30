package org.softuni.university.service;

import org.softuni.university.domain.models.service.RoleServiceModel;

import java.util.Set;

public interface RoleService {

    void seedRolesInDb();

    Set<RoleServiceModel> findAllRoles() throws Exception;

    RoleServiceModel findByAuthority(String authority) throws Exception;
}
