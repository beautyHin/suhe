package com.cms.notice.bean;

import java.util.Date;

public class NoticeBean {

	private Integer noticeId;
	private String noticeName;
	private String noticeInfo;
	private Date createDate;
	private Integer createId;
	public Integer getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(Integer noticeId) {
		this.noticeId = noticeId;
	}
	public String getNoticeName() {
		return noticeName;
	}
	public void setNoticeName(String noticeName) {
		this.noticeName = noticeName;
	}
	public String getNoticeInfo() {
		return noticeInfo;
	}
	public void setNoticeInfo(String noticeInfo) {
		this.noticeInfo = noticeInfo;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Integer getCreateId() {
		return createId;
	}
	public void setCreateId(Integer createId) {
		this.createId = createId;
	}
	@Override
	public String toString() {
		return "NoticeBean [noticeId=" + noticeId + ", noticeName=" + noticeName + ", noticeInfo=" + noticeInfo
				+ ", createDate=" + createDate + ", createId=" + createId + "]";
	}
	
}
