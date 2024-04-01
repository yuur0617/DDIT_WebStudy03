package kr.or.ddit.basetech.designpattern.adapter.homework;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class XiaomiProduct implements PluggableCN {

	@Override
	public void receiveElectrivWithThreeLeg() {
		log.info("샤오미에서 실행한 코드입니다.");
	}

}
