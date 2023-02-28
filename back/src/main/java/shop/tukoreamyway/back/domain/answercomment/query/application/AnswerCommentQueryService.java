package shop.tukoreamyway.back.domain.answercomment.query.application;

import lombok.RequiredArgsConstructor;
import shop.tukoreamyway.back.domain.answercomment.dto.AnswerCommentResponse;
import shop.tukoreamyway.back.domain.answercomment.entity.AnswerComment;
import shop.tukoreamyway.back.domain.answercomment.mapper.AnswerCommentMapper;
import shop.tukoreamyway.back.global.service.EntityQueryService;
import shop.tukoreamyway.back.global.service.QueryService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@QueryService
@RequiredArgsConstructor
public class AnswerCommentQueryService implements EntityQueryService<AnswerComment, Long> {
    private final AnswerCommentQueryRepository answerCommentQueryRepository;
    private final AnswerCommentMapper answerCommentMapper;

    public AnswerComment getEntity(final Long id) {
        return answerCommentQueryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
    public List<AnswerCommentResponse> findAllByAnswerId(final Long answerId) {
        final List<AnswerComment> answerComments =
                answerCommentQueryRepository.findAllByAnswerId(answerId);
        return mapToList(answerComments);
    }

    public AnswerCommentResponse findById(final Long id) {
        return answerCommentMapper.toResponse(getEntity(id));
    }

    private List<AnswerCommentResponse> mapToList(final List<AnswerComment> answerComments) {
        return answerComments.stream().map(answerCommentMapper::toResponse).toList();
    }
}
