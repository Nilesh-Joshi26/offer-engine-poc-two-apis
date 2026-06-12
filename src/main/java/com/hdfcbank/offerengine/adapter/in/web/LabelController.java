package com.hdfcbank.offerengine.adapter.in.web;

import com.hdfcbank.offerengine.application.dto.ApiResponse;
import com.hdfcbank.offerengine.application.dto.CreateLabelRequest;
import com.hdfcbank.offerengine.application.dto.LabelResponse;
import com.hdfcbank.offerengine.application.dto.ModifyLabelRequest;
import com.hdfcbank.offerengine.application.service.LabelApplicationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/v1/labels")
public class LabelController {

    private final LabelApplicationService labelApplicationService;

    public LabelController(LabelApplicationService labelApplicationService) {
        this.labelApplicationService = labelApplicationService;
    }

    // API 1 - Label Create / Modify
    // Source connectivity: Frontend -> Middleware -> App -> DB.
    @PostMapping
    public ResponseEntity<ApiResponse<LabelResponse>> create(@Valid @RequestBody CreateLabelRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(labelApplicationService.create(request)));
    }

    @PutMapping("/{labelCode}")
    public ResponseEntity<ApiResponse<LabelResponse>> modify(
            @PathVariable @NotBlank @Size(max = 50) String labelCode,
            @Valid @RequestBody ModifyLabelRequest request) {
        return ResponseEntity.ok(ApiResponse.success(labelApplicationService.modify(labelCode, request)));
    }

    @DeleteMapping("/{labelCode}")
    public ResponseEntity<ApiResponse<LabelResponse>> delete(
            @PathVariable @NotBlank @Size(max = 50) String labelCode,
            @RequestParam @NotBlank @Size(max = 50) String deletedBy) {
        return ResponseEntity.ok(ApiResponse.success(labelApplicationService.delete(labelCode, deletedBy)));
    }
}
