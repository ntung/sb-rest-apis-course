package com.itersdesktop.javatechs.springboot.controller;

import com.itersdesktop.javatechs.springboot.ConversionDetails;
import com.itersdesktop.javatechs.springboot.UnitConversionException;
import com.itersdesktop.javatechs.springboot.UnitConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1.0/unit-conversion")
public class UnitConversionApi {
    @PostMapping
    public ResponseEntity<ConversionDetails> convert(@RequestBody ConversionDetails details) {
        try {
            UnitConverter.convert(details);
            System.out.println(details);
        } catch (UnitConversionException e) {
            return ResponseEntity.badRequest().build();
        }

        return new ResponseEntity<>(details, HttpStatus.OK);
    }
}
