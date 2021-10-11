package com.example.injection;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InjectionApplicationTests {

  @Test
  void unTest() {
    assertThat(true).isEqualTo(true);
  }
}
