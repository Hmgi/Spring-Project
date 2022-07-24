package introduction.introductionSpring.service;


import java.util.List;
import java.util.Optional;

import introduction.introductionSpring.domain.Member;
import introduction.introductionSpring.repository.MemberRepository;
import introduction.introductionSpring.repository.MemoryMemberRepository;

public class MemberService {
	private final MemberRepository memberRepository = new MemoryMemberRepository();
	/**
	 * ȸ������
	 */
	
	public Long join(Member member) {
		//�ߺ� ȸ�� X
//		Optional<Member> result = memberRepository.findByName(member.getName());
//		result.ifPresent(m-> {
//			throw new IllegalStateException("�̹� �����Ѵ�");
//		});
		
		validateDuplicateMember(member); //�ߺ�ȸ�� ����
		memberRepository.save(member);
		return member.getId();
	}
	
	private void validateDuplicateMember(Member member) {
		memberRepository.findByName(member.getName())
			.ifPresent(m-> {
				throw new IllegalStateException("�̹� �����Ѵ�");
			});
	}
	
	/**
	 * ��üȸ�� ��ȸ
	 */
	public List<Member> findMembers(){
		return memberRepository.findAll();
	}
	
	public Optional<Member> findOne(Long memberId){
		return memberRepository.findById(memberId);
	}
}