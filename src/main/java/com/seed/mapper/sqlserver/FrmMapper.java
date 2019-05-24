package com.seed.mapper.sqlserver;

import java.util.List;

import com.seed.mapper.BaseMapper;
import com.seed.entity.frm.*;

public interface FrmMapper extends BaseMapper {
	
	// region FrmForm Methods

	public FrmForm GetFrmForm(FrmForm item);

	public List<FrmForm> SearchFrmForm(FrmForm item);

	public void SaveFrmForm(FrmForm item);

	// endregion FrmForm Methods

	// region FrmFormDetail Methods

	public FrmFormDetail GetFrmFormDetail(FrmFormDetail item);

	public List<FrmFormDetail> GetFrmFormDetailByForm(FrmForm item);

	public void SaveFrmFormDetail(FrmFormDetail item);

	// endregion FrmFormDetail Methods

	// region FrmFormType Methods

	public FrmFormType GetFrmFormType(FrmFormType item);

	public List<FrmFormType> SearchFrmFormType(FrmFormType item);

	public void SaveFrmFormType(FrmFormType item);

	// endregion FrmFormType Methods

}
