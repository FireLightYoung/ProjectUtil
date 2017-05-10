package com.util.ming.projectutil.data;
/**
 * 此文件涉及嵌入版，请在同步独立版程序时，同步嵌入版程序
 *
 */
public interface DataListener {
	public void onUpdateData(int type, int action, Object data);
	
	public void onReleaseListener();
}
