package com.example.batchprocessing.repository;

import java.util.Optional;

import com.example.batchprocessing.models.EnumRole;
import com.example.batchprocessing.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(EnumRole name);

}
