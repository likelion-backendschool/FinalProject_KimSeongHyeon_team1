package com.example.mutbooks.domain.pay.response.cancel;


import lombok.Data;

@Data
public class CancelAvailableAmount {
    private Integer total;
    private Integer tax_free;
    private Integer vat;
    private Integer point;
    private Integer discount;
    private Integer green_deposit;
}
