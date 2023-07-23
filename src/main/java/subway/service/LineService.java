package subway.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import subway.dto.LineRequest;
import subway.dto.LineResponse;
import subway.entity.Line;
import subway.repository.LineRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class LineService {

    private final LineRepository lineRepository;

    public LineService(LineRepository lineRepository) {
        this.lineRepository = lineRepository;
    }

    @Transactional
    public LineResponse saveLine(LineRequest lineRequest) {
        return new LineResponse(lineRepository.save(new Line(lineRequest)));
    }

    @Transactional
    public List<LineResponse> findAllLines() {
        return lineRepository.findAll()
                .stream()
                .map(LineResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public LineResponse findLine(Long id) {
        return new LineResponse(findById(id));
    }

    @Transactional
    private Line findById(Long id) {
        return lineRepository.findById(id).get();
    }

    @Transactional
    public LineResponse updateLine(Long id, LineRequest lineRequest) {
        Line line = findById(id);
        return new LineResponse(line.update(lineRequest));
    }

    @Transactional
    public void deleteLineById(Long id) {
        lineRepository.deleteById(id);
    }
}
