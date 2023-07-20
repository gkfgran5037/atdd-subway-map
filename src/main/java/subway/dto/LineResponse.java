package subway.dto;

import lombok.Getter;
import lombok.Setter;
import subway.entity.Line;

@Getter
@Setter
public class LineResponse {
    private Long id;
    private String name;
    private String color;

    public LineResponse(Long id, String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public LineResponse(Line line) {
        this.id = line.getId();
        this.name = line.getName();
        this.color = line.getColor();
    }
}
