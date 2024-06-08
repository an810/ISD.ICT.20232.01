package com.aims.service.impl;

import com.aims.entity.invoice.Invoice;
import com.aims.entity.order.Order;
import com.aims.entity.payment.PaymentTransaction;
import com.aims.repository.InvoiceRepository;
import com.aims.service.InvoiceService;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public Invoice create(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }
}
