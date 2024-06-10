package com.aims.service;

import com.aims.entity.invoice.Invoice;
import org.springframework.stereotype.Service;

@Service
public interface InvoiceService {
    Invoice create(Invoice invoice);
}
