package com.miya.DynamicDataScourceDemo.Repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.miya.DynamicDataScourceDemo.model.CouponPublish;

/**
 * @date 2018年7月3日 下午2:28:56
 *
 * @author zhoutuo
 */
public interface CouponPublishRepository extends PagingAndSortingRepository<CouponPublish, Long>, JpaSpecificationExecutor<CouponPublish> {

	public CouponPublish findByPubId(String pubId);
}
