package com.example.demo.model;

import lombok.Data;

import java.util.List;
import java.util.Map;
@Data
public class TestResult {
    private List<Map<String, List<String>>> data;
}
