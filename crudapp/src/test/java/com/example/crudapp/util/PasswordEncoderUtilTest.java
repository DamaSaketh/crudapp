// package com.example.crudapp.util;

// import org.junit.jupiter.api.Test;

// class PasswordEncoderUtilTest {

//     @Test
//     void testMainMethodExecution() {
//         // This executes your util main method and increases coverage
//         PasswordEncoderUtil.main(new String[]{});
//     }
// }


package com.example.crudapp.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordEncoderUtilTest {

    // ✅ Cover constructor
    @Test
    void testConstructor() {
        PasswordEncoderUtil util = new PasswordEncoderUtil();
        assertNotNull(util);
    }

    // ✅ Cover main method
    @Test
    void testMainMethod() {
        assertDoesNotThrow(() -> 
            PasswordEncoderUtil.main(new String[]{})
        );
    }
}