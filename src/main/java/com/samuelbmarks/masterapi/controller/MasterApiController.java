package com.samuelbmarks.masterapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/masterapi")
public class MasterApiController {
    private final Logger LOGGER = LoggerFactory.getLogger(MasterApiController.class);

    public MasterApiController() {}

    @GetMapping(value = "/status")
    public ResponseEntity<String> status() {
        LOGGER.info("Status request received");
        return ResponseEntity.ok("MasterAPI is running");
    }
}
