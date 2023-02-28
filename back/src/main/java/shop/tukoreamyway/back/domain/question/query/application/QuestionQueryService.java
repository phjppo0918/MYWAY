package shop.tukoreamyway.back.domain.question.query.application;

import lombok.RequiredArgsConstructor;

import shop.tukoreamyway.back.domain.question.dto.QuestionResponse;
import shop.tukoreamyway.back.domain.question.entity.Question;
import shop.tukoreamyway.back.domain.question.mapper.QuestionMapper;
import shop.tukoreamyway.back.global.service.EntityQueryService;
import shop.tukoreamyway.back.global.service.QueryService;

import java.util.List;

import javax.persistence.EntityNotFoundException;

@QueryService
@RequiredArgsConstructor
public class QuestionQueryService implements EntityQueryService<Question, Long> {
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
