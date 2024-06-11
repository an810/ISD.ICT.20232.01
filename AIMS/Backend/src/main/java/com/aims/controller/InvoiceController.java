package com.aims.controller;


import com.aims.entity.delivery.DeliveryInfo;
import com.aims.entity.invoice.Invoice;
import com.aims.entity.response.AIMSResponse;
import com.aims.service.InvoiceService;
import com.aims.utils.Constants;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<AIMSResponse<Invoice>> create(@RequestBody Invoice invoice) {
        Invoice data =  invoiceService.create(invoice);
        AIMSResponse<Invoice> response = new AIMSResponse<>(Constants.SUCCESS_CODE, "Create invoice successfully", data);
        return ResponseEntity.ok(response);
    }
}
