package shop.tukoreamyway.back.domain.question.query.application;

import lombok.RequiredArgsConstructor;
import shop.tukoreamyway.back.domain.question.dto.QuestionResponse;
import shop.tukoreamyway.back.domain.question.entity.Question;
import shop.tukoreamyway.back.domain.question.mapper.QuestionMapper;
import shop.tukoreamyway.back.global.QueryService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@QueryService
@RequiredArgsConstructor
public class QuestionQueryService {
    private final QuestionQueryRepository questionQueryRepository;
    private final QuestionMapper questionMapper;

    public Question getEntity(Long id) {
        return questionQueryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<QuestionResponse> findAllByWriterId(Long staffId) {
        List<Question> questions = questionQueryRepository.findAllByWriterId(staffId);
        return mapToList(questions);
    }

    public QuestionResponse findQuestionById(Long questionId) {
        Question question = getEntity(questionId);
        return questionMapper.toResponse(question);
    }

    private List<QuestionResponse> mapToList(List<Question> questions) {
        return questions.stream().map(questionMapper::toResponse).toList();
    }
}
