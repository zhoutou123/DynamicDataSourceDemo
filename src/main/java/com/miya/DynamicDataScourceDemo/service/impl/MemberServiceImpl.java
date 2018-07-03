package com.miya.DynamicDataScourceDemo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.miya.DynamicDataScourceDemo.Repository.MemberInfoRepository;
import com.miya.DynamicDataScourceDemo.http.HttpClient;
import com.miya.DynamicDataScourceDemo.model.MemberInfo;
import com.miya.DynamicDataScourceDemo.service.MemberService;

import okhttp3.FormBody;
import okhttp3.FormBody.Builder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * @date 2018年6月28日 下午6:36:18
 *
 * @author zhoutuo
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class MemberServiceImpl implements MemberService {

	@Autowired
	private HttpClient httpClient;

	@Autowired
	private MemberInfoRepository memberInfoRepository;

	public MemberInfo findByMemberIdAndSaasId(String memberId, String saasId) {
		return memberInfoRepository.findByMemberIdAndSaasId(memberId, saasId);
	}

	public List<MemberInfo> findBySaasId(String saasId) {
		return memberInfoRepository.findBySaasId(saasId);
	}

	public MemberInfo findByMemberName(String name) {
		return memberInfoRepository.findByMemberName(name);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public int updateMmeberPhone(String phone, String saasId, String memberId) {
		return memberInfoRepository.updateMmeberPhone(phone, saasId, memberId);
	}

	public Page<MemberInfo> findBySaasIdWithPage(String saasId, Integer page, Integer size) {
		Pageable pageable = PageRequest.of(page, size, Direction.DESC, "memberId");
		return memberInfoRepository.findBySaasId(saasId, pageable);
	}

	public void httpLink() {
		OkHttpClient okHttpClient = httpClient.build();
		Builder bulider = new FormBody.Builder();
		bulider.add("a", "a");
		bulider.add("b", "b");
		RequestBody body = bulider.build();
		Request request = new Request.Builder().url("www.baidu.com").post(body).build();
		JSONObject resJson = httpClient.sendHttp(okHttpClient, request);
	}

}
