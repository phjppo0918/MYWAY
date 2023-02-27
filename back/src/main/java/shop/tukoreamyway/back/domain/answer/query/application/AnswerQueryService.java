package shop.tukoreamyway.back.domain.answer.query.application;

import lombok.RequiredArgsConstructor;
import shop.tukoreamyway.back.domain.answer.dto.AnswerResponse;
import shop.tukoreamyway.back.domain.answer.entity.Answer;
import shop.tukoreamyway.back.domain.answer.mapper.AnswerMapper;
import shop.tukoreamyway.back.global.QueryService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@QueryService
@RequiredArgsConstructor
public class AnswerQueryService {
    private final AnswerQueryRepository answerQueryRepository;
    private final AnswerMapper answerMapper;

    public Answer getEntity(Long id) {
        return answerQueryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<AnswerResponse> findAllByQuestionId(Long memberId) {
        List<Answer> answers = answerQueryRepository.findAllByQuestionId(memberId);
        return mapToList(answers);
    }

    public AnswerResponse findById(Long id) {
        return answerMapper.toResponse(getEntity(id));
    }

    private List<AnswerResponse> mapToList(List<Answer> answers) {
        return answers.stream().map(answerMapper::toResponse).toList();
    }
}
