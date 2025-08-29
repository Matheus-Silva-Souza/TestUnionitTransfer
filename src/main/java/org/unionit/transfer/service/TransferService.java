package org.unionit.transfer.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.unionit.transfer.model.Transfer;
import org.unionit.transfer.repository.TransferRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class TransferService {

    private final TransferRepository repository;

    // ✅ Construtor com injeção de dependência
    @Autowired
    public TransferService(TransferRepository repository) {
        this.repository = repository;
    }

    public Transfer scheduleTransfer(Transfer transfer) {
        BigDecimal fee = calculateFee(transfer);
        transfer.setFee(fee);
        return transfer;
    }

    public List<Transfer> getAllTransfers() {
        return repository.findAll();
    }

    public BigDecimal calculateFee(Transfer transfer) {
        long daysBetween = ChronoUnit.DAYS.between(transfer.getSchedulingDate(), transfer.getTransferDate());
        BigDecimal amount = transfer.getAmount();

        if (daysBetween == 0) {
            return BigDecimal.valueOf(3).add(amount.multiply(BigDecimal.valueOf(0.025)));
        } else if (daysBetween >= 1 && daysBetween <= 10) {
            return BigDecimal.valueOf(12);
        } else if (daysBetween >= 11 && daysBetween <= 20) {
            return amount.multiply(BigDecimal.valueOf(0.082));
        } else if (daysBetween >= 21 && daysBetween <= 30) {
            return amount.multiply(BigDecimal.valueOf(0.069));
        } else if (daysBetween >= 31 && daysBetween <= 40) {
            return amount.multiply(BigDecimal.valueOf(0.047));
        } else if (daysBetween >= 41 && daysBetween <= 50) {
            return amount.multiply(BigDecimal.valueOf(0.017));
        } else {
            throw new IllegalArgumentException("Transfer date cannot exceed 50 days from scheduling date.");
        }
    }
}
