package com.digitali.api.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstablishmentRequest {

    private String id;
    private String description;
    private String folder;
}
