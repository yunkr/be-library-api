package library.librarymanagement.inquiry.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import library.librarymanagement.inquiry.dto.InquiryPatchDto;
import library.librarymanagement.inquiry.dto.InquiryResponseDto;
import library.librarymanagement.inquiry.entity.Inquiry;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-15T22:27:32+0900",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.jar, environment: Java 11.0.18 (Azul Systems, Inc.)"
)
@Component
public class InquiryMapperImpl implements InquiryMapper {

    @Override
    public Inquiry inquiryPatchDtoToInquiry(InquiryPatchDto inquiryPatchDto) {
        if ( inquiryPatchDto == null ) {
            return null;
        }

        Inquiry inquiry = new Inquiry();

        inquiry.setInquiryId( inquiryPatchDto.getInquiryId() );
        inquiry.setTitle( inquiryPatchDto.getTitle() );
        inquiry.setContent( inquiryPatchDto.getContent() );

        return inquiry;
    }

    @Override
    public List<InquiryResponseDto> inquiryToInquiryResponseDtos(List<Inquiry> inquiries) {
        if ( inquiries == null ) {
            return null;
        }

        List<InquiryResponseDto> list = new ArrayList<InquiryResponseDto>( inquiries.size() );
        for ( Inquiry inquiry : inquiries ) {
            list.add( inquiryToInquiryResponseDto( inquiry ) );
        }

        return list;
    }
}
