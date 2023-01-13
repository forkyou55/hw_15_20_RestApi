package in.reqres;

import models.lombok.RegistrationBodyLombokModel;
import models.lombok.UserBodyLombokModel;
import models.pojo.RegistrationBodyPojoModel;
import models.pojo.UserBodyPojoModel;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static specs.BaseSpecs.baseRequestSpec;
import static specs.BaseSpecs.baseResponseSpec;
import static specs.CreateSpecs.createRequestSpec;
import static specs.CreateSpecs.createResponseSpec;
import static specs.RegistrationSpecs.registrationRequestSpec;
import static specs.RegistrationSpecs.registrationResponseSpec;
import static specs.UpdateSpecs.updateSpecs;

public class InReqresTests {
    @Test
    void registrationWithPojoTest() {
        RegistrationBodyPojoModel registrationBody = new RegistrationBodyPojoModel();
        registrationBody.setEmail("eve.holt@reqres.in");
        registrationBody.setPassword("pistol");

        given()
                .spec(registrationRequestSpec)
                .body(registrationBody)
                .when()
                .post()
                .then()
                .spec(registrationResponseSpec)
                .body("id", is(4))
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }
    @Test
    void registrationWithLombokTest() {
        RegistrationBodyLombokModel registrationBody = new RegistrationBodyLombokModel();
        registrationBody.setEmail("eve.holt@reqres.in");
        registrationBody.setPassword("pistol");

        given()
                .spec(registrationRequestSpec)
                .body(registrationBody)
                .when()
                .post()
                .then()
                .spec(registrationResponseSpec)
                .body("id", is(4))
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }
    @Test
    void updateWithPojoTest() {
        UserBodyPojoModel userBody = new UserBodyPojoModel();
        userBody.setName("morpheus");
        userBody.setJob("leader");
        given()
                .spec(createRequestSpec)
                .body(userBody)
                .when()
                .put("/2")
                .then()
                .spec(updateSpecs)
                .body("name", is("morpheus"))
                .body("job", is("leader"));
    }
    @Test
    void updateWithLombokTest() {
        UserBodyLombokModel userBody = new UserBodyLombokModel();
        userBody.setName("morpheus");
        userBody.setJob("leader");
        given()
                .spec(createRequestSpec)
                .body(userBody)
                .when()
                .put("/2")
                .then()
                .spec(updateSpecs)
                .body("name", is("morpheus"))
                .body("job", is("leader"));
    }
    @Test
    void createPojoTest() {
        UserBodyPojoModel userBody = new UserBodyPojoModel();
        userBody.setName("morpheus");
        userBody.setJob("leader");

        given()
                .spec(createRequestSpec)
                .body(userBody)
                .when()
                .post()
                .then()
                .spec(createResponseSpec)
                .body("name", is("morpheus"))
                .body("job", is("leader"));
    }
    @Test
    void createLombokTest() {
        UserBodyLombokModel userBody = new UserBodyLombokModel();
        userBody.setName("morpheus");
        userBody.setJob("leader");

        given()
                .spec(createRequestSpec)
                .body(userBody)
                .when()
                .post()
                .then()
                .spec(createResponseSpec)
                .body("name", is("morpheus"))
                .body("job", is("leader"));
    }
    @Test
    void deleteTest() {
        given()
                .spec(baseRequestSpec)
                .when()
                .delete("/api/users/2")
                .then()
                .spec(baseResponseSpec)
                .statusCode(204);
    }
    @Test
    void listUsersTest() {
        given()
                .spec(baseRequestSpec)
                .when()
                .get("/api/users?page=2")
                .then()
                .spec(baseResponseSpec)
                .statusCode(200);
    }
    @Test
    void idUserTest() {
        given()
                .spec(baseRequestSpec)
                .when()
                .get("/api/users?page=2")
                .then()
                .spec(baseResponseSpec)
                .statusCode(200)
                .body("data.id", hasItems(7, 8, 9, 10, 11, 12));
    }

}

