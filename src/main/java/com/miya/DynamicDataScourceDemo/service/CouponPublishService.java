package com.miya.DynamicDataScourceDemo.service;

import com.miya.DynamicDataScourceDemo.model.CouponPublish;

/**
 * @date 2018年7月3日 下午2:27:25
 *
 * @author zhoutuo
 */
public interface CouponPublishService {

	/**
	 * @author zhoutuo
	 * @date 2018年7月3日 下午2:32:22
	 */
	CouponPublish findCouponPublishByPubId(String pubId);

}
