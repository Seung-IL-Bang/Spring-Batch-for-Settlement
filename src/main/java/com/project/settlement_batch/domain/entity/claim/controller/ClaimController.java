package com.project.settlement_batch.domain.entity.claim.controller;


import com.project.settlement_batch.facade.ClaimCompleteFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.HeadersBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/claim")
@RequiredArgsConstructor
public class ClaimController {

    private final ClaimCompleteFacade claimCompleteFacade;

    @GetMapping("/complete/{id}")
    public HeadersBuilder completeClaim(@PathVariable("id") Integer claimReceiptId) {
        claimCompleteFacade.receiptComplete(claimReceiptId);
        return ResponseEntity.noContent(); // 임시로 성공 응답 반환 예시
    }


}
