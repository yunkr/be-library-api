package library.librarymanagement.member.mapper;

import library.librarymanagement.member.dto.MemberPatchDto;
import library.librarymanagement.member.dto.MemberPostDto;
import library.librarymanagement.member.dto.MemberResponseDto;
import library.librarymanagement.member.entity.Member;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    default Member memberPostDtoToMember(MemberPostDto memberPostDto) {

        Member member = new Member();

        member.setEmail(memberPostDto.getEmail());
        member.setPassword(memberPostDto.getPassword());
        member.setDisplayName(memberPostDto.getDisplayName());
        member.setPhone(memberPostDto.getPhone());

        return member;
    }

    Member memberPatchDtoToMember(MemberPatchDto memberPatchDto);

    default MemberResponseDto memberToMemberResponseDto(Member member) {

        MemberResponseDto memberResponseDto = new MemberResponseDto();

        memberResponseDto.setMemberId(member.getMemberId());
        memberResponseDto.setEmail(member.getEmail());
        memberResponseDto.setPassword(member.getPassword());
        memberResponseDto.setDisplayName(member.getDisplayName());
        memberResponseDto.setPhone(member.getPhone());
        memberResponseDto.setCreatedAt(member.getCreatedAt());      // null 값이 나옴 다시 수정하기
        memberResponseDto.setModifiedAt(member.getModifiedAt());    // null 값이 나옴 다시 수정하기

        return memberResponseDto;

    }

    List<MemberResponseDto> memberToMemberResponseDtos(List<Member> members);

}
