package shop.tukoreamyway.back.domain.question.query.application;

import lombok.RequiredArgsConstructor;
import shop.tukoreamyway.back.domain.question.dto.QuestionResponse;
import shop.tukoreamyway.back.domain.question.entity.Question;
import shop.tukoreamyway.back.domain.question.mapper.QuestionMapper;
import shop.tukoreamyway.back.global.service.EntityLoader;
import shop.tukoreamyway.back.global.service.QueryService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@QueryService
@RequiredArgsConstructor
public class QuestionQueryService implements EntityLoader<Question, Long> {
    private final QuestionQueryRepository questionQueryRepository;
    private final QuestionMapper questionMapper;

    public Question getEntity(final Long id) {
        return questionQueryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<QuestionResponse> findAllByWriterId(final Long writerId) {
        final List<Question> questions = questionQueryRepository.findAllByWriterId(writerId);
        return mapToList(questions);
    }

    public QuestionResponse findById(final Long Id) {
        final Question question = getEntity(Id);
        return questionMapper.toResponse(question);
    }

    private List<QuestionResponse> mapToList(List<Question> questions) {
        return questions.stream().map(questionMapper::toResponse).toList();
    }
}
