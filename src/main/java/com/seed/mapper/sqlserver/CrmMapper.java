package com.seed.mapper.sqlserver;

import java.util.List;

import com.seed.entity.crm.CrmCust;
import com.seed.mapper.BaseMapper;

public interface CrmMapper extends BaseMapper{
	
	public void SaveCrmCust (CrmCust item);

	public List<CrmCust> GetListCust(CrmCust cust);

	public CrmCust GetCust(CrmCust cust);

	public List<CrmCust> SearchCust(CrmCust cust);
}
