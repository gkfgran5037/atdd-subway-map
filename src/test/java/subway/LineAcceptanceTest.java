package subway;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import subway.dto.LineResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("지하철 노선 관련 기능")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class LineAcceptanceTest {
    /**
     * When 지하철 노선을 생성하면
     * Then 지하철 노선 목록 조회 시 생성한 노선을 찾을 수 있다
     */
    // TODO : 유효성 확인
    @Test
    void createLine() {
        // when
        String name = "분당선";
        String color = "yellow";
        ExtractableResponse<Response> response = create(name, color);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());

        // then
        LineResponse lineResponse = response.as(LineResponse.class);
        assertThat(lineResponse.getName()).isEqualTo(name);
        assertThat(lineResponse.getColor()).isEqualTo(color);
    }


    /**
     * Given 지하철 노선을 생성하고
     * When 생성한 지하철 노선을 조회하면
     * Then 생성한 지하철 노선의 정보를 응답받을 수 있다.
     */
    // TODO : LineResponse 확인
    @Test
    void showLines() {
        // given
        String nameOfLine1 = "1호선";
        String colorOfLine1 = "blue";
        String nameOfLine2 = "2호선";
        String colorOfLine2 = "green";
        String responseOfLine1 = createAndGetName(nameOfLine1, colorOfLine1);
        String responseOfLine2 = createAndGetName(nameOfLine2, colorOfLine2);

        // when
        List<String> lineNames = findNames();

        // then
        assertThat(lineNames).contains(responseOfLine1, responseOfLine2);
    }
    // TODO : 지하철노선 조회  테스트

    /**
     * Given 지하철 노선을 생성하고
     * When 생성한 지하철 노선을 수정하면
     * Then 해당 지하철 노선 정보는 수정된다
     */
    // TODO : 지하철노선 수정 테스트

    /**
     * Given 지하철 노선을 생성하고
     * When 생성한 지하철 노선을 삭제하면
     * Then 해당 지하철 노선 정보는 삭제된다
     */
    // TODO : 지하철노선 삭제  테스트

    /**
     * Given 2개의 지하철 노선을 생성하고
     * When 지하철 노선 목록을 조회하면
     * Then 지하철 노선 목록 조회 시 2개의 노선을 조회할 수 있다.
     */

    private ExtractableResponse<Response> create(String name, String color) {
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("color", color);

        return RestAssured.given().log().all()
                .body(params)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/lines")
                .then().log().all()
                .extract();
    }

    private String createAndGetName(String name, String color) {
        return create(name, color)
                .jsonPath()
                .getString("name");
    }

    private static ExtractableResponse<Response> findAll() {
        return RestAssured.given().log().all()
                .when().get("/lines")
                .then().log().all()
                .extract();
    }

    private static List<String> findNames() {
        return findAll()
                .jsonPath()
                .getList("name", String.class);
    }
}
