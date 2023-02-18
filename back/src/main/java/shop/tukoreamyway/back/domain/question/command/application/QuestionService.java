package shop.tukoreamyway.back.domain.question.command.application;

import lombok.RequiredArgsConstructor;
import shop.tukoreamyway.back.domain.question.dto.QuestionRequest;
import shop.tukoreamyway.back.domain.question.dto.UpdateQuestionRequest;
import shop.tukoreamyway.back.domain.question.entity.Question;
import shop.tukoreamyway.back.domain.question.mapper.QuestionMapper;
import shop.tukoreamyway.back.domain.question.query.application.QuestionQueryRepository;
import shop.tukoreamyway.back.domain.staff.entity.Staff;
import shop.tukoreamyway.back.domain.staff.query.application.StaffQueryService;
import shop.tukoreamyway.back.domain.team.entity.Team;
import shop.tukoreamyway.back.domain.team.query.application.TeamQueryService;
import shop.tukoreamyway.back.global.CommandService;
import shop.tukoreamyway.back.global.IdResponse;

import javax.persistence.EntityNotFoundException;

@CommandService
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final TeamQueryService teamQueryService;
    private final StaffQueryService staffQueryService;
    private final QuestionQueryRepository questionQueryRepository;
    private final QuestionMapper questionMapper;

    public IdResponse<Long> create(QuestionRequest dto) {
        Team team = teamQueryService.getEntity(dto.getTeamId());
        Staff staff = staffQueryService.getActiveStaff(dto.getTeamId());
        Question question = questionRepository.save(questionMapper.toEntity(dto, team, staff));
        return new IdResponse<>(question.getId());
    }


    public void update(Long id, UpdateQuestionRequest dto) {
        getEntity(id).update(dto.getContent());
    }

    private Question getEntity(Long id) {
        return questionQueryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
