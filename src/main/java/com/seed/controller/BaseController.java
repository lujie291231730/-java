package com.seed.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.seed.entity.ReturnList;
import com.seed.entity.ReturnValue;
import com.seed.entity.publics.*;
import com.seed.entity.user.OnlineUser;
import com.seed.utils.*;

public class BaseController {

	// region SearchParams Methods
	
	@InitBinder("search")
	public void InitBinderSearch(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("search.");
	}
	
	public <T> void SetSearch(SearchParams sp, SelectBean<T> item, OnlineUser ou, String search) {
		sp.setSearch(search);
		sp.setEnd(this.GetEndCnt(sp));
		sp.setUserid(ou.getUser().getUserid());
		item.setUserid(ou.getUser().getUserid());
		item.setIsadmin(ou.getUser().getIsadmin());
	}
	
	public int GetEndCnt(SearchParams sp) {
		int endcnt = sp.isHasexport() ? ((sp.getExpcnt() == 0) ? Consts.DEFAULT_EXPORT_ROWS : sp.getExpcnt()) : sp.getLimit();
		
		return sp.getStart() + endcnt;
	}
	
	// endregion SearchParams Methods
		
	// region Return Methods
	
	public <T> ReturnList OutLists(List<T> lists) {
		return this.OutLists(lists, lists.size());
	}
	
	public <T> ReturnList OutLists(List<T> lists, int total) {
		ReturnList rtn = new ReturnList();
		rtn.setData(lists);
		rtn.setTotal(total);
		
		return rtn;
	}

	public <T> ResponseEntity<ReturnList> OutReturnList (List<T> lists) {
		return this.OutReturnList(lists, lists.size());
	}
	
	public <T> ResponseEntity<T> OutReturnBean (T item) {
		HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity<T>(item, headers, HttpStatus.OK);
	}
	
	public <T> ResponseEntity<ReturnList> OutReturnList (List<T> lists, int total) {
		HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity<ReturnList>(this.OutLists(lists, total), headers, HttpStatus.OK);
	}
	
	public List<ExportColumn> GeneratorClomun (List<JsonSqlColumn> jsonSqlColumns) {
    List<ExportColumn> columns = new ArrayList<>();
    
    for (JsonSqlColumn item : jsonSqlColumns) {
        ExportColumn column = new ExportColumn();
        column.setFormat(item.getColformat());
        column.setWidth(item.getColwidth());
        column.setText(item.getColname());
        column.setDataindex(item.getColcode());
        columns.add(column);
    }
    
    return columns;
	}

	public ExportSetting GetExportSetting(String file, boolean hasdaterange, RequestParams req) {
		ExportSetting es = new ExportSetting();
		es.setSheetname(file);
		es.setTitle(file);
		if (hasdaterange)
			es.setDaterange(req.GetExportDateBetween());
		return es;
	}

	public ResponseEntity<byte[]> FileDownload(String filename, InputStream is) throws IOException {
		byte[] filecontent =  new byte[is.available()];
		int length = is.read(filecontent);
		HttpHeaders headers = new HttpHeaders();
		
		headers.setContentType(MediaType.valueOf("application/vnd.ms-excel"));
		headers.setContentDispositionFormData("attachment", filename);
		headers.setContentLength(length);
		
		return new ResponseEntity<>(filecontent, headers, HttpStatus.CREATED);
	}

	public <T> ResponseEntity<byte[]> OutExport(List<T> lists, ExportSetting es, List<ExportColumn> cols, String filename)  throws IOException{
		filename = StringUtils.Encode(filename + "-" + ToolUtils.GetFmtDate(ToolUtils.GetNowDate(), "yyyyMMdd") + ".xls");

		InputStream is = ExcelUtils.GetExeclStream(es, cols, lists);
		return FileDownload(filename, is);
	}

	public <T> ResponseEntity<byte[]> OutExport(List<T> lists, List<ExportColumn> cols, String filename) throws IOException {
		ExportSetting es = GetExportSetting(filename, false, null);
		
		return OutExport(lists, es, cols, filename);
	}
	
	public ReturnValue NotLoginBean() {
		ReturnValue rtv = new ReturnValue();
		
		rtv.setSuccess(false);	
		rtv.setMsg("没有登录系统!");
		
		return rtv;
	}

	// endregion Return Methods
}
