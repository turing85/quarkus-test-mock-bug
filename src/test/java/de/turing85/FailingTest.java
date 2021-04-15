package de.turing85;

import static io.quarkus.test.junit.QuarkusMock.installMockForType;
import static io.restassured.RestAssured.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import javax.ws.rs.core.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


@QuarkusTest
@TestHTTPEndpoint(FooResource.class)
class FailingTest {

  private final FooRepository fooRepository = mock(FooRepository.class);

  @BeforeEach
  void setup() {
    installMockForType(fooRepository, FooRepository.class);
  }

  @Test
  void test() {
    final Foo foo = new Foo();

    given()
            .header("Content-Type", MediaType.APPLICATION_JSON)
            .body(foo)
        .when()
            .post();

    verify(fooRepository).save(foo);
  }
}
