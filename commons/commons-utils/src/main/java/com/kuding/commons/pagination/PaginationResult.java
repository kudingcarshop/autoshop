package com.kuding.commons.pagination;

import java.util.List;

public class PaginationResult <T>{
	
		private int total = 0;
		private List<T> rows;
		
		public int getTotal() {
			return total;
		}
		public void setTotal(int total) {
			this.total = total;
		}
		public List<T> getRows() {
			return rows;
		}
		
		public void setRows(List<T> rows) {
			this.rows = rows;
		}
}
