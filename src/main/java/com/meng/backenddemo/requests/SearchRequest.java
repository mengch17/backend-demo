package com.meng.backenddemo.requests;

import lombok.Data;

import java.util.Map;

@Data
public class SearchRequest {
    private Map<String, String> searchCriteria;
}
