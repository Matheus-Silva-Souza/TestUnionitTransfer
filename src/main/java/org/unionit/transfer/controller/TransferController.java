package org.unionit.transfer.controller;

import org.unionit.transfer.model.Transfer;
import org.unionit.transfer.service.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transfers")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public ResponseEntity<Transfer> scheduleTransfer(@RequestBody Transfer transfer) {
        try {
            Transfer scheduled = transferService.scheduleTransfer(transfer);
            return ResponseEntity.ok(scheduled);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public List<Transfer> getAllTransfers() {
        return transferService.getAllTransfers();
    }
}