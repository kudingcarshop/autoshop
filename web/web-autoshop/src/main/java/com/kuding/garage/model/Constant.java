package com.kuding.garage.model;

public class Constant {
	
	public static class CouponFactoryConstant {
		
		public static final int COUPON_TYPE_NONE = 0;
		public static final int COUPON_TYPE_CASH = 1;
		public static final int COUPON_TYPE_DISCOUNT = 2;
		
		public static final int COUPON_TYPE_CONDITION_NONE = 0;
		public static final int COUPON_TYPE_CONDITION_PRICE = 1;
		public static final int COUPON_TYPE_CONDITION_COUNT = 2;
		
		public static final int COUPON_FOR_TYPE_NONE = 0;
		public static final int COUPON_FOR_TYPE_ALL = 1;
		public static final int COUPON_FOR_TYPE_MALL = 2;
		public static final int COUPON_FOR_TYPE_GARAGE = 3;
		
		public static final int COUPON_STATUS_NONE = 0;
		public static final int COUPON_STATUS_ACTIVE = 1;
		public static final int COUPON_STATUS_CANCLED = 2;
		public static final int COUPON_STATUS_FREEZE = 3;
		
		
		public static final int COUPON_GET_STATUS_NONE = 0;
		public static final int COUPON_GET_STATUS_GET = 1;
		
		public static final String COUPON_FOR_SKU_SPLIT_SIMBOL = ";";
	}
	
	public static class CustomerCouponConstant {
		public static final int CUSTOMER_COUPON_STATUS_NONE = 0;
		public static final int CUSTOMER_COUPON_STATUS_ACTIVE = 1;
		public static final int CUSTOMER_COUPON_STATUS_CANCLED = 2;
		public static final int CUSTOMER_COUPON_STATUS_USED = 3;
		public static final int CUSTOMER_COUPON_STATUS_DISABLED = 4;
		public static final int CUSTOMER_COUPON_STATUS_OVERDUE = 5;
	}

}
