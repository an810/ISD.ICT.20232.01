package com.aims.controller;


import com.aims.entity.invoice.Invoice;
import com.aims.service.InvoiceService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/invoice")
public class InvoiceController {

    private final InvoiceService invoiceService;
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping("/create")
    public Invoice create(@RequestBody Invoice invoice) {
        return invoiceService.create(invoice);
    }
}
