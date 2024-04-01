package kr.or.ddit.basetech.designpattern.adapter.homework;

public class AdapterPlug implements PluggableKST {

	private final PluggableCN plug;
	
	@Override
	public void receiveElectricWithTwoLeg() {
		// TODO Auto-generated method stub

	}

	public AdapterPlug(PluggableCN plug) {
		super();
		this.plug = plug;
	}
	
}
