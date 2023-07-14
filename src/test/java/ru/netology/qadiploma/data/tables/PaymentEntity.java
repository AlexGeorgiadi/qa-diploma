package ru.netology.qadiploma.data.tables;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentEntity {
    String id;
    int amount;
    String created;
    String status;
    String transaction_id;
}