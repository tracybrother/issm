package com.tracybrother.context;

import java.io.Serializable;

/**
 * 用户上下文
 * 
 * @author guoming
 *
 */
public class Context implements Serializable {

	private static final long serialVersionUID = 2614424654378386124L;

	private String userId; // 用户ID
	private String userName; // 账号
	private String employName; // 员工姓名
	private String roleId; // 所属角色
	private String roleCode; // 所属角色编码
	private String roleName; // 角色名称
	private String userSkin; // 用户皮肤
	private String phone; // 用户手机号
	private String companyName; // 当前组织
	private String deptName; // 当前部门
	private String password; // 用户密码

	///////// 20170321 新增 luyan /////////
	private String ncSumMessage;// nc消息总条数
	private String msgzcpsize; // nc总承包
	private String msgerbxzbsize; // nc财务
	
	///////// 20170324 新增 luyan /////////
	private String tokenUuid;  // tokenUUID 用于与4A对接的单点登录
	

	public String getTokenUuid() {
		return tokenUuid;
	}

	public void setTokenUuid(String tokenUuid) {
		this.tokenUuid = tokenUuid;
	}

	public String getMsgzcpsize() {
		return msgzcpsize;
	}

	public void setMsgzcpsize(String msgzcpsize) {
		this.msgzcpsize = msgzcpsize;
	}

	public String getMsgerbxzbsize() {
		return msgerbxzbsize;
	}

	public void setMsgerbxzbsize(String msgerbxzbsize) {
		this.msgerbxzbsize = msgerbxzbsize;
	}

	public String getUserId() {
		return userId;
	}

	public String getNcSumMessage() {
		return ncSumMessage;
	}

	public void setNcSumMessage(String ncSumMessage) {
		this.ncSumMessage = ncSumMessage;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmployName() {
		return employName;
	}

	public void setEmployName(String employName) {
		this.employName = employName;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getUserSkin() {
		return userSkin;
	}

	public void setUserSkin(String userSkin) {
		this.userSkin = userSkin;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
