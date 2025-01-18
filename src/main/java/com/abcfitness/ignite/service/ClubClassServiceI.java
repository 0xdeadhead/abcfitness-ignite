package com.abcfitness.ignite.service;

import com.abcfitness.ignite.dto.ClassCreationRequestDTO;
import com.abcfitness.ignite.entity.ClubClass;

public interface ClubClassServiceI {
    public ClubClass createClass(ClassCreationRequestDTO clubClassDTO);
}
