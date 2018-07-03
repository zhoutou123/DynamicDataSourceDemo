package com.miya.DynamicDataScourceDemo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.miya.DynamicDataScourceDemo.model.MemberInfo;

/**
 * @date 2018年6月28日 下午6:36:18
 *
 * @author zhoutuo
 */
public interface MemberService {

	public MemberInfo findByMemberIdAndSaasId(String memberId, String saasId);

	public List<MemberInfo> findBySaasId(String saasId);

	public MemberInfo findByMemberName(String name);

	public int updateMmeberPhone(String phone, String saasId, String memberId);

	public Page<MemberInfo> findBySaasIdWithPage(String saasId, Integer page, Integer size);

	public void httpLink();

}
