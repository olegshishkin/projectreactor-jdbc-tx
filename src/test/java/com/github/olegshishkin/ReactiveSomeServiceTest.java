package com.github.olegshishkin;

import static java.util.stream.Collectors.toSet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

import com.github.olegshishkin.application.DemoApplication;
import com.github.olegshishkin.application.entity.Item;
import com.github.olegshishkin.application.entity.Shop;
import com.github.olegshishkin.application.repository.ItemRepository;
import com.github.olegshishkin.application.repository.ShopRepository;
import com.github.olegshishkin.application.service.reactive.ReactiveSomeService;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import reactor.test.StepVerifier;

@ContextConfiguration(classes = DemoApplication.class)
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@TestPropertySource("classpath:application-test.properties")
@SpringBootTest
class ReactiveSomeServiceTest {

    @Autowired
    private ReactiveSomeService service;

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    void test() {
        var expectedShop = new Shop();
        expectedShop.setId(0L);
        expectedShop.setName("shop1");
        expectedShop.setItemCounter(2);
        expectedShop.setVersion(1);

        var expected = new Item();
        expected.setId(1L);
        expected.setName("item2");
        expected.setShop(expectedShop);
        expected.setVersion(0);

        StepVerifier
                .create(service.addItem("shop1", "item2"))
                .consumeNextWith(item -> {
                    assertEquals(expected, item);
                    var items = itemRepository.findAllByShop_name("shop1")
                            .stream()
                            .map(Item::getName)
                            .collect(toSet());
                    assertEquals(Set.of("item1", "item2"), items);
                    assertEquals(2, shopRepository.findByName("shop1").get().getItemCounter());
                })
                .verifyComplete();
    }
}
