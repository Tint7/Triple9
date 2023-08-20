package com.solt.triplenine.util;

import com.solt.triplenine.entity.Member;

public class Security {
	private static Member member;

	public static Member getMember() {
		return member;
	}

	public static void setMember(Member member) {
		Security.member = member;
	}
}
