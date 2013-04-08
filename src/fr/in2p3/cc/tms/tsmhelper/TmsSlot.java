package fr.in2p3.cc.tms.tsmhelper;

public class TmsSlot {

	private String volume_name;
	private String slot_name;
	
	public TmsSlot(String volname, String slotname) {
		this.volume_name = volname;
		this.slot_name = slotname;
	}

	public String getVolumeName() {
		return volume_name;
	}

	public void setVolumeName(String volume_name) {
		this.volume_name = volume_name;
	}

	public String getSlotName() {
		return slot_name;
	}

	public void setSlotName(String slot_name) {
		this.slot_name = slot_name;
	}
	
	@Override
	public String toString() {
		return volume_name + " / " + slot_name;
	}
		
}
