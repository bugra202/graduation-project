package com.bugratasdemir.graduationproject.mapper;

import com.bugratasdemir.graduationproject.dto.CommentDTO;
import com.bugratasdemir.graduationproject.entity.Comment;
import com.bugratasdemir.graduationproject.request.CommentSaveRequest;
import com.bugratasdemir.graduationproject.request.CommentUpdateContentAndScoreRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);
    Comment convertToComment(CommentSaveRequest request);
    CommentDTO convertToCommentDTO(Comment comment);
    @Mapping(target = "id", ignore = true)
    void updateCustomerFields(@MappingTarget Comment comment, CommentUpdateContentAndScoreRequest request);

}
