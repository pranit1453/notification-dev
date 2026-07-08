package com.pranit.notification;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.core.ApplicationModules;

@SpringBootTest
class NotificationApplicationTests {

    @Test
    void contextLoads() {
        var modules = ApplicationModules.of(NotificationApplication.class);
        modules.verify();
    }

}
