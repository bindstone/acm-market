package com.bindstone.acm.market.dto;

/**
 * General DTO Mapper Interface to define the mapping.
 * @param <JPA> JPA Object
 * @param <DTO> DTO Object
 */
public interface DtoMapper<JPA, DTO> {

    /**
     * JPA to DTO Mapper
     *
     * @param object JPA Object
     * @return DTO Object
     */
    DTO toDto(JPA object);

    /**
     * JPA to DTO Mapper
     *
     * @param objectDto DTO Object
     * @return JPA Object
     */
    JPA fromDto(DTO objectDto);

}