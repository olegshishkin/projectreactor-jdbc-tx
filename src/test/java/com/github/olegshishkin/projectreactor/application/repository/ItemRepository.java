package com.github.olegshishkin.projectreactor.application.repository;

import com.github.olegshishkin.projectreactor.application.entity.Item;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Optional<Item> findByName(String name);

    List<Item> findAllByShop_name(String shopName);
}
