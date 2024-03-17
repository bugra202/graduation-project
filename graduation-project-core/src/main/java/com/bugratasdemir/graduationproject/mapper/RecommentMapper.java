package com.bugratasdemir.graduationproject.mapper;

import com.bugratasdemir.graduationproject.dto.RecommentDTO;
import com.bugratasdemir.graduationproject.general.RestResponse;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.stream.Collectors;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RecommentMapper {
    RecommentMapper INSTANCE = Mappers.getMapper(RecommentMapper.class);
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "latitude", source = "latitude")
    @Mapping(target = "longitude", source = "longitude")
    @Mapping(target = "rate", source = "rate")
    RecommentDTO mapToRecommentDTO(RecommentDTO source);
    default List<RecommentDTO> mapListToRecommentDTO(List<RecommentDTO> sourceList) {
        return sourceList.stream()
                .map(this::mapToRecommentDTO)
                .collect(Collectors.toList());
    }
    default List<RecommentDTO> convertToRecommentDTOs(ResponseEntity<RestResponse<List<RecommentDTO>>> recommentDTO) {
        return mapListToRecommentDTO(recommentDTO.getBody().getData());
    }
}
