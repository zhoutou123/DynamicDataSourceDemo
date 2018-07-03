package com.miya.DynamicDataScourceDemo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miya.DynamicDataScourceDemo.Repository.CouponPublishRepository;
import com.miya.DynamicDataScourceDemo.aop.annotation.TargetDataSource;
import com.miya.DynamicDataScourceDemo.model.CouponPublish;
import com.miya.DynamicDataScourceDemo.service.CouponPublishService;
import com.miya.DynamicDataScourceDemo.utils.enums.EnumDataSourceName;

/**
 * @date 2018年7月3日 下午2:27:50
 *
 * @author zhoutuo
 */
@Service
public class CouponPublishServiceImpl implements CouponPublishService {

	@Autowired
	private CouponPublishRepository couponPublishRepository;

	/**
	 * @see com.miya.DynamicDataScourceDemo.service.CouponPublishService#findCouponPublishByPubId(java.lang.String)
	 */
	@Override
	@TargetDataSource(EnumDataSourceName.DS2)
	public CouponPublish findCouponPublishByPubId(String pubId) {
		return couponPublishRepository.findByPubId(pubId);
	}

}
