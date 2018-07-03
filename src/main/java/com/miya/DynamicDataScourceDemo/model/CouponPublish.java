package com.miya.DynamicDataScourceDemo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "coupon_publish")
@Entity
public class CouponPublish implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String pubId;

	private String activeId;

	private Integer pubMerchantRole;

	private String pubSaasId;

	private String pubSaasParentId;

	private String couponName;

	private Integer brand;

	private Integer type;

	private Long couponAmt;

	private Integer couponCnt;

	private Date startTime;

	private Date endTime;

	private Integer effectTimeMode;

	private Integer effectTimeInterval;

	private Date fetchStart;

	private Date fetchEnd;

	private Date showStart;

	private Date showEnd;

	private String pic;

	private Integer pubFetchId;

	private Integer multiFetchChannel;

	private String showPageId;

	private String useDesc;

	private Integer showChannel;

	private Integer finishStatus;

	private Date gmtCreate;

	private Date gmtUpdate;

	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPubId() {
		return pubId;
	}

	public void setPubId(String pubId) {
		this.pubId = pubId == null ? null : pubId.trim();
	}

	public String getActiveId() {
		return activeId;
	}

	public void setActiveId(String activeId) {
		this.activeId = activeId == null ? null : activeId.trim();
	}

	public Integer getPubMerchantRole() {
		return pubMerchantRole;
	}

	public void setPubMerchantRole(Integer pubMerchantRole) {
		this.pubMerchantRole = pubMerchantRole;
	}

	public String getPubSaasId() {
		return pubSaasId;
	}

	public void setPubSaasId(String pubSaasId) {
		this.pubSaasId = pubSaasId == null ? null : pubSaasId.trim();
	}

	public String getPubSaasParentId() {
		return pubSaasParentId;
	}

	public void setPubSaasParentId(String pubSaasParentId) {
		this.pubSaasParentId = pubSaasParentId == null ? null : pubSaasParentId.trim();
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName == null ? null : couponName.trim();
	}

	public Integer getBrand() {
		return brand;
	}

	public void setBrand(Integer brand) {
		this.brand = brand;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getCouponAmt() {
		return couponAmt;
	}

	public void setCouponAmt(Long couponAmt) {
		this.couponAmt = couponAmt;
	}

	public Integer getCouponCnt() {
		return couponCnt;
	}

	public void setCouponCnt(Integer couponCnt) {
		this.couponCnt = couponCnt;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getEffectTimeMode() {
		return effectTimeMode;
	}

	public void setEffectTimeMode(Integer effectTimeMode) {
		this.effectTimeMode = effectTimeMode;
	}

	public Integer getEffectTimeInterval() {
		return effectTimeInterval;
	}

	public void setEffectTimeInterval(Integer effectTimeInterval) {
		this.effectTimeInterval = effectTimeInterval;
	}

	public Date getFetchStart() {
		return fetchStart;
	}

	public void setFetchStart(Date fetchStart) {
		this.fetchStart = fetchStart;
	}

	public Date getFetchEnd() {
		return fetchEnd;
	}

	public void setFetchEnd(Date fetchEnd) {
		this.fetchEnd = fetchEnd;
	}

	public Date getShowStart() {
		return showStart;
	}

	public void setShowStart(Date showStart) {
		this.showStart = showStart;
	}

	public Date getShowEnd() {
		return showEnd;
	}

	public void setShowEnd(Date showEnd) {
		this.showEnd = showEnd;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic == null ? null : pic.trim();
	}

	public Integer getPubFetchId() {
		return pubFetchId;
	}

	public void setPubFetchId(Integer pubFetchId) {
		this.pubFetchId = pubFetchId;
	}

	public Integer getMultiFetchChannel() {
		return multiFetchChannel;
	}

	public void setMultiFetchChannel(Integer multiFetchChannel) {
		this.multiFetchChannel = multiFetchChannel;
	}

	public String getShowPageId() {
		return showPageId;
	}

	public void setShowPageId(String showPageId) {
		this.showPageId = showPageId == null ? null : showPageId.trim();
	}

	public String getUseDesc() {
		return useDesc;
	}

	public void setUseDesc(String useDesc) {
		this.useDesc = useDesc == null ? null : useDesc.trim();
	}

	public Integer getShowChannel() {
		return showChannel;
	}

	public void setShowChannel(Integer showChannel) {
		this.showChannel = showChannel;
	}

	public Integer getFinishStatus() {
		return finishStatus;
	}

	public void setFinishStatus(Integer finishStatus) {
		this.finishStatus = finishStatus;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtUpdate() {
		return gmtUpdate;
	}

	public void setGmtUpdate(Date gmtUpdate) {
		this.gmtUpdate = gmtUpdate;
	}

}