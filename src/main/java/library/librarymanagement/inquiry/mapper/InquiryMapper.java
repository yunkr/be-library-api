package library.librarymanagement.inquiry.mapper;

import library.librarymanagement.inquiry.dto.InquiryPatchDto;
import library.librarymanagement.inquiry.dto.InquiryPostDto;
import library.librarymanagement.inquiry.dto.InquiryResponseDto;
import library.librarymanagement.inquiry.entity.Inquiry;
import library.librarymanagement.member.entity.Member;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InquiryMapper {

    default Inquiry inquiryPostDtoToInquiry(InquiryPostDto inquiryPostDto) {

        Inquiry inquiry= new Inquiry();
        Member member = new Member();

        member.setMemberId(inquiryPostDto.getMemberId());

        inquiry.setTitle(inquiryPostDto.getTitle());
        inquiry.setContent(inquiryPostDto.getContent());

        inquiry.setMember(member);

        return inquiry;
    }

    Inquiry inquiryPatchDtoToInquiry(InquiryPatchDto inquiryPatchDto);

    default InquiryResponseDto inquiryToInquiryResponseDto(Inquiry inquiry) {

        InquiryResponseDto inquiryResponseDto = new InquiryResponseDto();

        inquiryResponseDto.setMember(inquiry.getMember());
        inquiryResponseDto.setInquiryId(inquiry.getInquiryId());
        inquiryResponseDto.setTitle(inquiry.getTitle());
        inquiryResponseDto.setContent(inquiry.getContent());
        inquiryResponseDto.setCreatedAt(inquiry.getCreatedAt());
        inquiryResponseDto.setModifiedAt(inquiry.getModifiedAt());

        return inquiryResponseDto;
    }

    List<InquiryResponseDto> inquiryToInquiryResponseDtos(List<Inquiry> inquiries);

}
