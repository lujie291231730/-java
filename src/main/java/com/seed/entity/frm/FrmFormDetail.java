package com.seed.entity.frm;

import com.seed.entity.*;
import com.seed.entity.publics.*;
import com.seed.enums.DataAction;
import com.seed.utils.*;

public class FrmFormDetail implements BaseBean {

	// 表单编号;
	private String formid;

	// 序号;
	private int cellserial;

	// 开始行数;
	private int beginrow;

	// 开始列数;
	private int endrow;

	// 结束行数;
	private int begincolumn;

	// 结束列表;
	private int endcolumn;

	// 项目名称;
	private String cellname;

	// 数据来源;
	private String valuesource;

	// 数据来源名称;
	private String valuesourcename;

	// 数据类型;
	private String valuetype;

	// 检测值类型名称;
	private String valuetypename;

	// 所属类编号;
	private String classsource;

	// 类字段编号;
	private String fieldcode;

	// 循环序号;
	private int groupserial;

	// 分组循环号;
	private int specserial;

	// 显示数据;
	private String celltext;

	// 显示格式;
	private String cellformat;

	// 线框;
	private int isborder;

	// 下划线;
	private boolean isline;

	// 是否加粗;
	private boolean isbold;

	// 字体;
	private String fontfamliy;

	// 字体大小;
	private int fontsize;

	// 对齐方式;
	private String aligntype;

	// 对齐方式名称;
	private String aligntypename;

	// 纵向对齐方式;
	private String valigntype;

	// 对齐方式名称;
	private String valigntypename;

	// 前缀;
	private String prefixtext;

	// 后缀;
	private String postfixtext;

	// 上线框;
	private boolean linetop;

	// 下线框;
	private boolean linebottom;

	// 左线框;
	private boolean lineleft;

	// 右线框;
	private boolean lineright;
	private String defvalue;
	private int linewidth;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<FrmFormDetail> item;

	public FrmFormDetail() {
		this.OnInit();
	}

	@Override
	public boolean OnBeforeSave(ErrorMsg msg) {
		msg.setErrmsg("");
		boolean rtn = false;
		
		if ((this.getDeal().getAction() ==  DataAction.Deal.getAction()) || (this.getDeal().getAction() ==  DataAction.Delete.getAction())){
			return rtn;
		}
				
		if (ToolUtils.StringIsEmpty(this.getCellname())) {
			msg.setErrmsg("项目名称不能为空!"+ToolUtils.GetNewLines());
			rtn = true;
		}	
		
		if (ToolUtils.CheckComboValue(this.getValuesource())) {
			msg.setErrmsg("请选择数据来源!"+ToolUtils.GetNewLines());
			rtn = true;
		}
		
		if (ToolUtils.CheckComboValue(this.getValuetype())) {
			msg.setErrmsg("请选择数据类型!"+ToolUtils.GetNewLines());
			rtn = true;
		}
		
		if (ToolUtils.CheckComboValue(this.getAligntype())) {
			msg.setErrmsg("请选择对齐方式!"+ToolUtils.GetNewLines());
			rtn = true;
		}
		
		if (ToolUtils.CheckComboValue(this.getValigntype())) {
			msg.setErrmsg("请选择纵向对齐方式!"+ToolUtils.GetNewLines());
			rtn = true;
		}
		
		return rtn;
	}

	@Override
	public String OnDebug() {
		return ToolUtils.DebugProperty(this, this.OnProperties());
	}

	@Override
	public String OnCompare(BaseBean item) {
		return ToolUtils.CompareProperty((FrmFormDetail)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"表单编号:formid", "序号:cellserial", "开始行数:beginrow", "开始列数:endrow", "结束行数:begincolumn", 
				"结束列表:endcolumn", "项目名称:cellname", "数据来源:valuesource", "数据类型:valuetype", "所属类编号:classsource", "循环序号:groupserial", 
				"分组循环号:specserial", "显示数据:celltext", "显示格式:cellformat", "线框:isborder", "下划线:isline", "是否加粗:isbold", 
				"字体:fontfamliy", "字体大小:fontsize", "对齐方式:aligntype", "纵向对齐方式:valigntype", "前缀:prefixtext", "后缀:postfixtext", 
				"上线框:linetop", "下线框:linebottom", "左线框:lineleft", "右线框:lineright"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.formid = "";
		this.cellserial = 0;
		this.beginrow = 0;
		this.endrow = 0;
		this.begincolumn = 0;
		this.endcolumn = 0;
		this.cellname = "";
		this.valuesource = "";
		this.valuesourcename = "";
		this.valuetype = "";
		this.valuetypename = "";
		this.classsource = "";
		this.fieldcode = "";
		this.groupserial = 0;
		this.specserial = 0;
		this.celltext = "";
		this.cellformat = "";
		this.isborder = 0;
		this.isline = false;
		this.isbold = false;
		this.fontfamliy = "";
		this.fontsize = 0;
		this.aligntype = "";
		this.aligntypename = "";
		this.valigntype = "";
		this.valigntypename = "";
		this.prefixtext = "";
		this.postfixtext = "";
		this.linetop = false;
		this.linebottom = false;
		this.lineleft = false;
		this.lineright = false;
		this.linewidth = 1;
		this.defvalue = "";
	}

	public String getDefvalue() {
		return defvalue;
	}

	public void setDefvalue(String defvalue) {
		this.defvalue = defvalue;
	}

	public int getLinewidth() {
		return linewidth;
	}

	public void setLinewidth(int linewidth) {
		this.linewidth = linewidth;
	}

	public String getFormid() {
		return formid;
	}

	public void setFormid(String formid) {
		this.formid=formid;
	}

	public int getCellserial() {
		return cellserial;
	}

	public void setCellserial(int cellserial) {
		this.cellserial=cellserial;
	}

	public int getBeginrow() {
		return beginrow;
	}

	public void setBeginrow(int beginrow) {
		this.beginrow=beginrow;
	}

	public int getEndrow() {
		return endrow;
	}

	public void setEndrow(int endrow) {
		this.endrow=endrow;
	}

	public int getBegincolumn() {
		return begincolumn;
	}

	public void setBegincolumn(int begincolumn) {
		this.begincolumn=begincolumn;
	}

	public int getEndcolumn() {
		return endcolumn;
	}

	public void setEndcolumn(int endcolumn) {
		this.endcolumn=endcolumn;
	}

	public String getCellname() {
		return cellname;
	}

	public void setCellname(String cellname) {
		this.cellname=cellname;
	}

	public String getValuesource() {
		return valuesource;
	}

	public void setValuesource(String valuesource) {
		this.valuesource=valuesource;
	}

	public String getValuesourcename() {
		return valuesourcename;
	}

	public void setValuesourcename(String valuesourcename) {
		this.valuesourcename=valuesourcename;
	}

	public String getValuetype() {
		return valuetype;
	}

	public void setValuetype(String valuetype) {
		this.valuetype=valuetype;
	}

	public String getValuetypename() {
		return valuetypename;
	}

	public void setValuetypename(String valuetypename) {
		this.valuetypename=valuetypename;
	}

	public String getClasssource() {
		return classsource;
	}

	public void setClasssource(String classsource) {
		this.classsource=classsource;
	}

	public String getFieldcode() {
		return fieldcode;
	}

	public void setFieldcode(String fieldcode) {
		this.fieldcode=fieldcode;
	}

	public int getGroupserial() {
		return groupserial;
	}

	public void setGroupserial(int groupserial) {
		this.groupserial=groupserial;
	}

	public int getSpecserial() {
		return specserial;
	}

	public void setSpecserial(int specserial) {
		this.specserial=specserial;
	}

	public String getCelltext() {
		return celltext;
	}

	public void setCelltext(String celltext) {
		this.celltext=celltext;
	}

	public String getCellformat() {
		return cellformat;
	}

	public void setCellformat(String cellformat) {
		this.cellformat=cellformat;
	}

	public int getIsborder() {
		return isborder;
	}

	public void setIsborder(int isborder) {
		this.isborder=isborder;
	}

	public boolean getIsline() {
		return isline;
	}

	public void setIsline(boolean isline) {
		this.isline=isline;
	}

	public boolean getIsbold() {
		return isbold;
	}

	public void setIsbold(boolean isbold) {
		this.isbold=isbold;
	}

	public String getFontfamliy() {
		return fontfamliy;
	}

	public void setFontfamliy(String fontfamliy) {
		this.fontfamliy=fontfamliy;
	}

	public int getFontsize() {
		return fontsize;
	}

	public void setFontsize(int fontsize) {
		this.fontsize=fontsize;
	}

	public String getAligntype() {
		return aligntype;
	}

	public void setAligntype(String aligntype) {
		this.aligntype=aligntype;
	}

	public String getAligntypename() {
		return aligntypename;
	}

	public void setAligntypename(String aligntypename) {
		this.aligntypename=aligntypename;
	}

	public String getValigntype() {
		return valigntype;
	}

	public void setValigntype(String valigntype) {
		this.valigntype=valigntype;
	}

	public String getValigntypename() {
		return valigntypename;
	}

	public void setValigntypename(String valigntypename) {
		this.valigntypename=valigntypename;
	}

	public String getPrefixtext() {
		return prefixtext;
	}

	public void setPrefixtext(String prefixtext) {
		this.prefixtext=prefixtext;
	}

	public String getPostfixtext() {
		return postfixtext;
	}

	public void setPostfixtext(String postfixtext) {
		this.postfixtext=postfixtext;
	}

	public boolean getLinetop() {
		return linetop;
	}

	public void setLinetop(boolean linetop) {
		this.linetop=linetop;
	}

	public boolean getLinebottom() {
		return linebottom;
	}

	public void setLinebottom(boolean linebottom) {
		this.linebottom=linebottom;
	}

	public boolean getLineleft() {
		return lineleft;
	}

	public void setLineleft(boolean lineleft) {
		this.lineleft=lineleft;
	}

	public boolean getLineright() {
		return lineright;
	}

	public void setLineright(boolean lineright) {
		this.lineright=lineright;
	}

	public SearchParams getSearch() {
		if (search == null)
			search = new SearchParams();

		return search;
	}

	public void setSearch(SearchParams search) {
		this.search = search;
	}

	public DataDeal getDeal() {
		if (deal == null)
			deal = new DataDeal();

		return deal;
	}

	public void setDeal(DataDeal deal) {
		this.deal = deal;
	}

	public SelectBean<FrmFormDetail> getItem() {
		if (item == null)
			item = new SelectBean<FrmFormDetail>();

		return item;
	}

	public void setItem(SelectBean<FrmFormDetail> item) {
		this.item = item;
	}

}
