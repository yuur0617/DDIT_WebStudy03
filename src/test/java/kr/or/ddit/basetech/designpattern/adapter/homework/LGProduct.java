package kr.or.ddit.basetech.designpattern.adapter.homework;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LGProduct implements PluggableKST {

	@Override
	public void receiveElectricWithTwoLeg() {
		log.info("LG에서 실행한 코드입니다.");
	}

}
