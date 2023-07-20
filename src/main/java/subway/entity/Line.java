package subway.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import subway.dto.LineRequest;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Line {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String color;

    public Line(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public Line(LineRequest lineRequest) {
        this.name = lineRequest.getName();
        this.color = lineRequest.getColor();
    }
}
