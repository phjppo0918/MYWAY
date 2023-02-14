package shop.tukoreamyway.back.domain.staff.command.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.tukoreamyway.back.domain.staff.command.application.StaffService;
import shop.tukoreamyway.back.domain.staff.dto.AcceptApplyRequest;
import shop.tukoreamyway.back.domain.staff.dto.AcceptInviteRequest;
import shop.tukoreamyway.back.domain.staff.dto.ApplyRequest;
import shop.tukoreamyway.back.domain.staff.dto.InviteRequest;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("staffs")
public class StaffController {
    private final StaffService staffService;

    @PostMapping("invite")
    public ResponseEntity<Void> invite(@RequestBody @Valid InviteRequest dto) {
        staffService.invite(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("apply")
    public ResponseEntity<Void> apply(@RequestBody @Valid ApplyRequest dto) {
        staffService.apply(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("{id}/accept-invite")
    public ResponseEntity<Void> invite(@PathVariable Long id, AcceptInviteRequest dto) {
        staffService.acceptInvite(id, dto);
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    @PostMapping("{id}/accept-apply")
    public ResponseEntity<Void> invite(@PathVariable Long id, AcceptApplyRequest dto) {
        staffService.acceptApply(id, dto);
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

}
