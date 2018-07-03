package com.miya.DynamicDataScourceDemo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @date 2018年6月28日 下午4:28:33
 *
 * @author zhoutuo
 */
@Table(name = "member_info_1")
@Entity
public class MemberInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String saasId;

	private String custId;

	private String marketId;

	private String memberId;

	private String phone;

	private String name;

	private Integer gender;

	private Integer age;

	private String birthday;

	private String prov;

	private String city;

	private String dist;

	private String addr;

	private String idnum;

	private Long itegral;

	private Integer lv;

	private Date lvUpdate;

	private String userid;

	private Date aliCreate;

	private String openid;

	private String unionid;

	private Date wxCreate;

	private String tmId;

	private Date tmCreate;

	private Date gmtCreate;

	private Date gmtUpdate;

	private String type;

	private String nickname;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the saasId
	 */
	public String getSaasId() {
		return saasId;
	}

	/**
	 * @param saasId the saasId to set
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}

	/**
	 * @return the custId
	 */
	public String getCustId() {
		return custId;
	}

	/**
	 * @param custId the custId to set
	 */
	public void setCustId(String custId) {
		this.custId = custId;
	}

	/**
	 * @return the marketId
	 */
	public String getMarketId() {
		return marketId;
	}

	/**
	 * @param marketId the marketId to set
	 */
	public void setMarketId(String marketId) {
		this.marketId = marketId;
	}

	/**
	 * @return the memberId
	 */
	public String getMemberId() {
		return memberId;
	}

	/**
	 * @param memberId the memberId to set
	 */
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the gender
	 */
	public Integer getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(Integer gender) {
		this.gender = gender;
	}

	/**
	 * @return the age
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * @return the birthday
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the prov
	 */
	public String getProv() {
		return prov;
	}

	/**
	 * @param prov the prov to set
	 */
	public void setProv(String prov) {
		this.prov = prov;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the dist
	 */
	public String getDist() {
		return dist;
	}

	/**
	 * @param dist the dist to set
	 */
	public void setDist(String dist) {
		this.dist = dist;
	}

	/**
	 * @return the addr
	 */
	public String getAddr() {
		return addr;
	}

	/**
	 * @param addr the addr to set
	 */
	public void setAddr(String addr) {
		this.addr = addr;
	}

	/**
	 * @return the idnum
	 */
	public String getIdnum() {
		return idnum;
	}

	/**
	 * @param idnum the idnum to set
	 */
	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}

	/**
	 * @return the itegral
	 */
	public Long getItegral() {
		return itegral;
	}

	/**
	 * @param itegral the itegral to set
	 */
	public void setItegral(Long itegral) {
		this.itegral = itegral;
	}

	/**
	 * @return the lv
	 */
	public Integer getLv() {
		return lv;
	}

	/**
	 * @param lv the lv to set
	 */
	public void setLv(Integer lv) {
		this.lv = lv;
	}

	/**
	 * @return the lvUpdate
	 */
	public Date getLvUpdate() {
		return lvUpdate;
	}

	/**
	 * @param lvUpdate the lvUpdate to set
	 */
	public void setLvUpdate(Date lvUpdate) {
		this.lvUpdate = lvUpdate;
	}

	/**
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
	}

	/**
	 * @param userid the userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}

	/**
	 * @return the aliCreate
	 */
	public Date getAliCreate() {
		return aliCreate;
	}

	/**
	 * @param aliCreate the aliCreate to set
	 */
	public void setAliCreate(Date aliCreate) {
		this.aliCreate = aliCreate;
	}

	/**
	 * @return the openid
	 */
	public String getOpenid() {
		return openid;
	}

	/**
	 * @param openid the openid to set
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}

	/**
	 * @return the unionid
	 */
	public String getUnionid() {
		return unionid;
	}

	/**
	 * @param unionid the unionid to set
	 */
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	/**
	 * @return the wxCreate
	 */
	public Date getWxCreate() {
		return wxCreate;
	}

	/**
	 * @param wxCreate the wxCreate to set
	 */
	public void setWxCreate(Date wxCreate) {
		this.wxCreate = wxCreate;
	}

	/**
	 * @return the tmId
	 */
	public String getTmId() {
		return tmId;
	}

	/**
	 * @param tmId the tmId to set
	 */
	public void setTmId(String tmId) {
		this.tmId = tmId;
	}

	/**
	 * @return the tmCreate
	 */
	public Date getTmCreate() {
		return tmCreate;
	}

	/**
	 * @param tmCreate the tmCreate to set
	 */
	public void setTmCreate(Date tmCreate) {
		this.tmCreate = tmCreate;
	}

	/**
	 * @return the gmtCreate
	 */
	public Date getGmtCreate() {
		return gmtCreate;
	}

	/**
	 * @param gmtCreate the gmtCreate to set
	 */
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	/**
	 * @return the gmtUpdate
	 */
	public Date getGmtUpdate() {
		return gmtUpdate;
	}

	/**
	 * @param gmtUpdate the gmtUpdate to set
	 */
	public void setGmtUpdate(Date gmtUpdate) {
		this.gmtUpdate = gmtUpdate;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}
