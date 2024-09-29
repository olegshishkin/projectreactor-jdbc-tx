package com.github.olegshishkin.projectreactor.application.repository;

import com.github.olegshishkin.projectreactor.application.entity.Shop;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {

    Optional<Shop> findByName(String name);
}
