package subway.dto;

import lombok.Getter;

@Getter
public class LineRequest {
    private String name;
    private String color;

    public LineRequest(String name, String color) {
        this.name = name;
        this.color = color;
    }
}
