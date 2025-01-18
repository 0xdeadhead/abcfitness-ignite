package com.abcfitness.ignite.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abcfitness.ignite.dto.ClassCreationRequestDTO;
import com.abcfitness.ignite.dto.ClassCreationResponseDTO;
import com.abcfitness.ignite.entity.ClubClass;
import com.abcfitness.ignite.service.ClubClassServiceI;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/class")
@RequiredArgsConstructor
public class ClubClassController {

    private final ClubClassServiceI classService;

    @PostMapping("/")
    public ResponseEntity<ClassCreationResponseDTO> create(
            @RequestBody ClassCreationRequestDTO classCreationRequestDTO) {
        ClubClass createdClass = classService.createClass(classCreationRequestDTO);
        ClassCreationResponseDTO classCreationResponseDTO = ClassCreationResponseDTO.builder()
                .clubClassId(createdClass.getId()).message("created").build();
        return ResponseEntity.status(HttpStatus.CREATED).body(classCreationResponseDTO);
    }

}
