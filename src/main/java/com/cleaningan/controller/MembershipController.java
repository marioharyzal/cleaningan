package com.cleaningan.controller;

import com.cleaningan.constant.MessageResponse;
import com.cleaningan.entity.Membership;
import com.cleaningan.model.request.MembershipRequest;
import com.cleaningan.model.response.SuccessResponse;
import com.cleaningan.service.IMembershipService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/memberships")
@Validated
public class MembershipController {

    private final ModelMapper modelMapper;

    private final IMembershipService membershipService;

    public MembershipController(ModelMapper modelMapper, IMembershipService membershipService) {
        this.modelMapper = modelMapper;
        this.membershipService = membershipService;
    }

    @GetMapping
    public ResponseEntity getAllMembership() {
        List<Membership> memberships = membershipService.findAll();
        return ResponseEntity.ok(new SuccessResponse<>(MessageResponse.SUCCESS_GET_DATA + "Membership", memberships));
    }

    @PostMapping
    public ResponseEntity addMembership(@Valid @RequestBody MembershipRequest membershipRequest) throws Exception {
        Membership membershipMap = modelMapper.map(membershipRequest, Membership.class);
        Membership newMembership = membershipService.save(membershipMap);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>(MessageResponse.SUCCESS_CREATE + "Membership", newMembership));
    }

    @GetMapping("/{id}")
    public ResponseEntity getOneMembership(@PathVariable("id") Integer id) {
        Membership oneMembership = membershipService.findOne(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>(MessageResponse.SUCCESS_GET_DATA.toString() + "Membership", oneMembership));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateOneMembership(@PathVariable("id") Integer id, @Valid @RequestBody MembershipRequest membershipRequest) {
        Membership membership = modelMapper.map(membershipRequest, Membership.class);
        Membership updateMembership = membershipService.update(id, membership);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>(MessageResponse.SUCCESS_UPDATE_DATA + "Membership", updateMembership));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeOneMembership(@PathVariable("id") Integer id) {
        Membership deletedMembership = membershipService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>(MessageResponse.SUCCESS_DELETE_DATA + "Membership", deletedMembership));
    }
}
