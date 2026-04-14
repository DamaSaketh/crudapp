// package com.example.crudapp;

// import org.junit.jupiter.api.Test;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;

// @SpringBootTest
// @AutoConfigureMockMvc(addFilters = false)
// class CrudappApplicationTests {

//     @Test
//     void contextLoads() {}
// }
package com.example.crudapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CrudappApplicationTests {

    @Test
    void contextLoads() {
        // This test loads full Spring context
        // If no exception → test passes
    }

    @Test
    void mainMethodRuns() {
        CrudappApplication.main(new String[] {});
    }
}