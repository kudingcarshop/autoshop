package com.kuding.commons;

/**
 * 错误码
 * 格式：类型_模块_desc,如 BIZ_GAR_
 * @author steven
 *
 */
public class ErrorCode {
	
	/**业务错误码:车行ID为空*/
	public static int BIZ_GAR_ID_NULL = 2001;
	
	/**业务错误码：车辆VIN或Engine Number为空*/
	public static int BIZ_VEH_VIN_ENGNO_NULL = 3001;
	
	/**业务错误码：车牌号为空*/
	public static int BIZ_VEH_PLATENO_NULL = 3002;
	
	/**业务错误码：车牌已存在*/
	public static int BIZ_VEH_EXIST =	3003;
	
	/**业务错误码：车辆ID不合法*/
	public static int BIZ_VEH_INVALID_ID = 3004;
	
	/**业务错误码：车辆不存在*/
	public static int BIZ_VEH_NOT_EXIST = 3005;

}
