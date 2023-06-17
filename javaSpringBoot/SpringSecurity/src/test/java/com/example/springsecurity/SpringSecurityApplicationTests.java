package com.example.springsecurity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class SpringSecurityApplicationTests {
    Calculat
    or calculator = new Calculator();

    @Test
    void contextLoads() {
      int a = 10;
      int b = 30;

      int expectedResult = 30;


      int c = calculator.add(a,b);

      assertThat(c).isEqualTo(expectedResult);
    }

}
