package library.librarymanagement.inquiry.controller;

import library.librarymanagement.book.dto.BookPatchDto;
import library.librarymanagement.book.dto.BookPostDto;
import library.librarymanagement.book.entity.Book;
import library.librarymanagement.dto.MultiResponseDto;
import library.librarymanagement.dto.SingleResponseDto;
import library.librarymanagement.inquiry.dto.InquiryPatchDto;
import library.librarymanagement.inquiry.dto.InquiryPostDto;
import library.librarymanagement.inquiry.entity.Inquiry;
import library.librarymanagement.inquiry.mapper.InquiryMapper;
import library.librarymanagement.inquiry.service.InquiryService;
import library.librarymanagement.utils.UriCreator;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/inquiries")
@Validated
public class InquiryController {

    private final static String INQUIRY_DEFAULT_URL = "/inquiries";

    private final InquiryService inquiryService;
    private final InquiryMapper inquiryMapper;

    public InquiryController(InquiryService inquiryService, InquiryMapper inquiryMapper) {
        this.inquiryService = inquiryService;
        this.inquiryMapper = inquiryMapper;
    }

    @PostMapping
    public ResponseEntity<?> postInquiry(@Valid @RequestBody InquiryPostDto requestBody) {

        Inquiry inquiry = inquiryService.createInquiry(inquiryMapper.inquiryPostDtoToInquiry(requestBody));
        URI location = UriCreator.createUri(INQUIRY_DEFAULT_URL, inquiry.getInquiryId());

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{inquiry-id}")
    public ResponseEntity<?> patchInquiry(@PathVariable("inquiry-id") @Positive long inquiryId,
                                       @Valid @RequestBody InquiryPatchDto requestBody) {

        requestBody.setInquiryId(inquiryId);
        Inquiry inquiry = inquiryService.updateInquiry(inquiryMapper.inquiryPatchDtoToInquiry(requestBody));

        return new ResponseEntity<>(
                new SingleResponseDto<>(inquiryMapper.inquiryToInquiryResponseDto(inquiry))
                , HttpStatus.OK);
    }

    @GetMapping("/{inquiry-id}")
    public ResponseEntity<?> getInquiry(@PathVariable("inquiry-id") @Positive long inquiryId) {

        Inquiry inquiry = inquiryService.findInquiry(inquiryId);

        return new ResponseEntity<>(
                new SingleResponseDto<>(inquiryMapper.inquiryToInquiryResponseDto(inquiry))
                , HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getInquiries(@RequestParam int page, @RequestParam int size) {

        Page<Inquiry> pageInquiries = inquiryService.findInquiries(page - 1, size);

        List<Inquiry> Inquiries = pageInquiries.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(inquiryMapper.inquiryToInquiryResponseDtos(Inquiries), pageInquiries)
                , HttpStatus.OK);
    }

    @DeleteMapping("/{inquiry-id}")
    public ResponseEntity<?> deleteInquiry(@PathVariable("inquiry-id") @Positive long inquiryId) {

        inquiryService.deleteInquiry(inquiryId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
