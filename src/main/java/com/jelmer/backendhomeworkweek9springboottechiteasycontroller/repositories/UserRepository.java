package com.jelmer.backendhomeworkweek9springboottechiteasycontroller.repositories;



import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}