package com.seed.service;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.seed.dao.PublicDao;
import com.seed.entity.*;
import com.seed.entity.log.TranLog;
import com.seed.enums.AlignType;
import com.seed.enums.DataAction;
import com.seed.enums.VAlignType;
import com.seed.enums.ValueSource;
import com.seed.utils.*;
import com.seed.dao.FrmDao;
import com.seed.entity.frm.*;

public class FrmService {
	
	// region FrmForm Methods

	public static FrmForm GetFrmForm(FrmForm item) {
		SqlSession session = DBUtils.getFactory();

		try {
			FrmForm rtn = FrmDao.GetFrmForm(session, item);
			
			if ((rtn != null) && !StringUtils.StringIsEmpty(rtn.getFormid())) {
				rtn.setDetails(FrmDao.GetFrmFormDetailByForm(session, rtn));
			}
			
			return rtn;
		} catch (Exception e) {	} finally {
			session.close();
		}
		
		return new FrmForm();
	}
	
	public static void SaveFrmForm(FrmForm item, ReturnValue rtv, TranLog log) {
		rtv.setSuccess(false);

		SqlSession session = DBUtils.getFactory();

		try {
			ErrorMsg errmsg = new ErrorMsg();

			if (item.OnBeforeSave(errmsg)) {
				rtv.setMsg(errmsg.getErrmsg());
				return;
			}

			for (FrmFormDetail detail : item.getDetails()) {
				detail.setFormid(item.getFormid());
				detail.getDeal().setAction(DataAction.Create.getAction());
				
				if (detail.OnBeforeSave(errmsg)) {
					rtv.setMsg(errmsg.getErrmsg());
					return;
				}
			}
			
			FrmDao.SaveFrmForm(session, item);

			FrmFormDetail ditem = new FrmFormDetail();
			ditem.setFormid(item.getFormid());
			ditem.getDeal().setAction(DataAction.Create.getAction());
			FrmDao.SaveFrmFormDetail(session, ditem);
			
			for (FrmFormDetail detail : item.getDetails()) {
				FrmDao.SaveFrmFormDetail(session, detail);
			}
			
			log.ActionToTran(item.getDeal().getAction());
			log.setTrandesc(item.getFormid());
			PublicDao.SaveTranLog(session, log);

			rtv.setSuccess(true);
			rtv.setMsg(Consts.STR_SAVE_S);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			rtv.setMsg(ToolUtils.GetErrorMessage(e, Consts.STR_SAVE_F));
		} finally {
			session.close();
		}
	}

	// endregion FrmForm Methods

	// region FrmFormType Methods

	public static void SaveFrmFormType(FrmFormType item, ReturnValue rtv, TranLog log) {
		rtv.setSuccess(false);

		SqlSession session = DBUtils.getFactory();

		try {
			ErrorMsg errmsg = new ErrorMsg();

			if (item.OnBeforeSave(errmsg)) {
				rtv.setMsg(errmsg.getErrmsg());
				return;
			}

			FrmDao.SaveFrmFormType(session, item);

			log.ActionToTran(item.getDeal().getAction());
			log.setTrandesc(item.getFormtype());
			PublicDao.SaveTranLog(session, log);

			rtv.setSuccess(true);
			rtv.setMsg(Consts.STR_SAVE_S);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			rtv.setMsg(ToolUtils.GetErrorMessage(e, Consts.STR_SAVE_F));
		} finally {
			session.close();
		}
	}

	// endregion FrmFormType Methods

	// region Print Methods
	
	@SuppressWarnings("unchecked")
	public static <T> List<PrintPage> SetPrintData(String[] formids, T item) {
    List<PrintPage> pages = new ArrayList<PrintPage>();

    SqlSession session = DBUtils.getFactory();

    try {
      FrmForm formget = new FrmForm();

      int arrlen = 0;
      int pagecnt = 0;
      int page = 0;

      for (String formid : formids) {
        formget.setFormid(formid);
        FrmForm form = FrmDao.GetFrmForm(session, formget);

        PrintPage ppage = new PrintPage();
        ppage.setForm(form);

        if (form.getIsloop() && !ToolUtils.StringIsEmpty(form.getLoopclass())) {
          Field f = item.getClass().getDeclaredField(form.getLoopclass());
          f.setAccessible(true);

          if (f.getType().toString().indexOf("java.util.List") >= 0) {           	
            List<Object> arrvalues = (ArrayList<Object>) f.get(item);
            arrlen = arrvalues.size();

            // 第一个1为整除后+1，第二个1为首页
            if (arrlen <= form.getLoopfirst())
              pagecnt = 1;
            else {
              pagecnt = (arrlen - form.getLoopfirst()) / form.getLooprecord()
                  + 1;

              if (pagecnt * form.getLooprecord() + form.getLoopfirst() < arrlen)
                pagecnt++;
            }

            List<FrmFormDetail> pdetails = FrmDao.GetFrmFormDetailByForm(session, formget);

            for (page = 0; page <= pagecnt - 1; page++) {
              PrintData pditem = new PrintData();

              for (FrmFormDetail detail : pdetails) {
                FrmFormDetail newitem = new FrmFormDetail();
                
                newitem.setFormid(detail.getFormid());
                newitem.setCellserial(detail.getCellserial());
                newitem.setBeginrow(detail.getBeginrow());
                newitem.setEndrow(detail.getEndrow());
                newitem.setBegincolumn(detail.getBegincolumn());
                newitem.setEndcolumn(detail.getEndcolumn());
                newitem.setCellname(detail.getCellname());
                newitem.setValuesource(detail.getValuesource());
                newitem.setValuesourcename(detail.getValuesourcename());
                newitem.setValuetype(detail.getValuetype());
                newitem.setValuetypename(detail.getValuetypename());
                newitem.setClasssource(detail.getClasssource());
                newitem.setFieldcode(detail.getFieldcode());
                newitem.setGroupserial(detail.getGroupserial());
                newitem.setSpecserial(detail.getSpecserial());
                newitem.setCelltext(detail.getCelltext());
                newitem.setCellformat(detail.getCellformat());
                newitem.setIsborder(detail.getIsborder());
                newitem.setIsline(detail.getIsline());
                newitem.setIsbold(detail.getIsbold());
                newitem.setFontfamliy(detail.getFontfamliy());
                newitem.setFontsize(detail.getFontsize());
                newitem.setAligntype(detail.getAligntype());
                newitem.setAligntypename(detail.getAligntypename());
                newitem.setValigntype(detail.getValigntype());
                newitem.setValigntypename(detail.getValigntypename());
                newitem.setPrefixtext(detail.getPrefixtext());
                newitem.setPostfixtext(detail.getPostfixtext());
                newitem.setLinetop(detail.getLinetop());
                newitem.setLinebottom(detail.getLinebottom());
                newitem.setLineleft(detail.getLineleft());
                newitem.setLineright(detail.getLineright());

                if (newitem.getClasssource().equals(form.getLoopclass())
                    && (newitem.getGroupserial() > 0)) {
                  if ((form.getLooprecord() == 1) && (form.getLoopfirst() == 1))
                    newitem.setSpecserial(detail.getSpecserial() + form.getLooprecord() * page);
                  else
                    newitem.setSpecserial(detail.getSpecserial() + form.getLooprecord() * page + form.getLoopfirst());
                }

                pditem.getDatas().add(newitem);
              }

              ppage.getDetails().add(pditem);
            }
          }
        } else {
          List<FrmFormDetail> details = FrmDao.GetFrmFormDetailByForm(session, formget);

          ppage.getDetails().add(new PrintData(details));
        }

        for (PrintData pdata : ppage.getDetails()) {
          SetFormDetail(pdata.getDatas(), item);
        }

        pages.add(ppage);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    } finally {
      session.close();
    }

    return pages;
  }

	public static <T> void GetFieldValue(FrmFormDetail detail, T data) {
		String value = "";
		
		try {
  		Field f = data.getClass().getDeclaredField(detail.getFieldcode());
      f.setAccessible(true);
      
      if (f.get(data) != null) {
      	if (f.get(data) instanceof java.util.Date) {
					SimpleDateFormat sp = new SimpleDateFormat(StringUtils.StringIsEmpty(detail.getCellformat()) ? 
							"yyyy-MM-dd HH:mm:ss" : detail.getCellformat());
					
					value = sp.format(f.get(data));
				}
				else if (f.get(data) instanceof Double) {
					DecimalFormat df = new DecimalFormat(StringUtils.StringIsEmpty(detail.getCellformat()) ? 
							"0.00" : detail.getCellformat());
					
					value = df.format(f.get(data));
				}
				else {
					value = f.get(data).toString();
				}
      }
		} catch (Exception e) {
			value = "";
		}
		
		detail.setCelltext(value);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> void SetFormDetail(List<FrmFormDetail> details, T data) {
    try {
      Object objclass = null;
      Field f = null;

      for (FrmFormDetail detail : details) {
        objclass = null;

        if (StringUtils.StringIsEmpty(detail.getClasssource())) {
        	if (StringUtils.StringIsEmpty(detail.getFieldcode()))
        		continue;
        	
        	GetFieldValue(detail, data);
        }
        else {
        	try {
        		f = data.getClass().getDeclaredField(detail.getClasssource());
        		f.setAccessible(true);
            
        		if ((detail.getGroupserial() > 0) && (f.getType().toString().indexOf("java.util.List") >= 0)) {
        			List<Object> arrvalues = (ArrayList<Object>) f.get(data);

              if (detail.getSpecserial() <= arrvalues.size()) {
                objclass = arrvalues.get(detail.getSpecserial() - 1);
              }
        		}
        		else
        			objclass = (Object) f.get(data);
        		
        		if (objclass != null)
        			GetFieldValue(detail, objclass);
        		
					} catch (Exception e) {
						// System.out.println(e.getMessage());
					}
        }
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
		
	public static String GetFormHtml(List<PrintPage> items) {
		StringBuffer sb =  new StringBuffer();
		
		// String remoteurl = ""; // PropertiesUtils.GetString("REMOTE_SERVER_URL");
		
		int i = 0, j = 0, formidx = 0, page = 0, pagecount = 0, pageidx = 0;
		FrmFormDetail record = new FrmFormDetail();
		int rowspan = 0; int colspan = 0;
		int height = 36, width = 40, nowheight = 0, nowwidth = 0;
		String celltext = "";
		String style = "", align = "", valign = "";
		
		for (formidx = 0; formidx < items.size(); formidx++) {
			pagecount += items.get(formidx).getDetails().size();
		}
		
		for (formidx = 0; formidx < items.size(); formidx++) {
			PrintPage nowform = items.get(formidx);
			
			for (page = 0; page < nowform.getDetails().size(); page++) {
				PrintData nowpage = nowform.getDetails().get(page);
				
				pageidx ++;
				
				if (pageidx == pagecount) {
					sb.append("<table cellspacing=\"0\" cellpadding = \"0\" style=\"font-size:12px;border-collapse:collapse;\">");
				}
				else {
					sb.append("<table cellspacing=\"0\" cellpadding = \"0\" style=\"font-size:12px;border-collapse:collapse;page-break-after:always;\">");
				}
				
				sb.append("<tr>");
				
				for(i = 0; i < nowform.getForm().getFormwidth(); i++) {
					sb.append("<td width=\"" + width + " px\"; height=\"1px\" ></td>");
				}
				
				sb.append("</tr>");
				
				for (i = 1; i< nowform.getForm().getFormlength(); i++) {
					sb.append("<tr><td width=\"" + width + "px\"; height=\"" + height + "px\"></td>");
					
					for(j = 0; j < nowpage.getDatas().size(); j++) {
						record = nowpage.getDatas().get(j);						
						
						if (record.getBeginrow() == i) {
							rowspan = record.getEndrow() - record.getBeginrow() + 1;
							nowheight = height * rowspan;
							
							colspan = record.getEndcolumn() - record.getBegincolumn() + 1;
							nowwidth = width * colspan;
							
							celltext = "";
							
							switch (ValueSource.parse(record.getValuesource())) {
								case DATA:
									celltext = record.getCelltext();
									break;
									
								case EMPTY:
									celltext = "";
									break;
									
								case TEXT:
									celltext = record.getCellname();
									break;

								default:
									break;
							}
							
							celltext = record.getPrefixtext() + celltext + record.getPostfixtext();
							
							style = "";
							align = "";
							valign = "";
							
							switch (AlignType.parse(record.getAligntype())) {
								case LEFT:
									align = " align=\"left\" ";
									style += "padding-left:3px;";
									break;
									
								case CENTER:
									align = " align=\"center\" ";
									break;
									
								case RIGHT:
									align = " align=\"right\" ";
									style += "padding-right:3px;";
									break;

								default:
									break;
							}
							
							switch (VAlignType.parse(record.getValigntype())) {
								case TOP:
									valign = " valign=\"top\" ";
									style += "padding-top:3px;";
									break;
									
								case MIDDLE:
									valign = " valign=\"middle\" ";
									break;
									
								case BOTTOM:
									valign = " valign=\"bottom\" ";
									style += "padding-bottom:3px;";
									break;

								default:
									break;
							}
							
							if(record.getIsbold())
								style += "font-weight:bold;";
							
							if (!StringUtils.StringIsEmpty(record.getFontfamliy()))
								style += "font-family:" + record.getFontfamliy() + ";";
							
							style += "font-size:" + record.getFontsize() + "px;";
							
							if (record.getLineleft() && record.getLineright() && record.getLinetop() && record.getLinebottom())
								style += "border:" + record.getLinewidth() + "px solid Black;";
							else {
								if (record.getLineleft())
									style += "border-left:" + record.getLinewidth() + "px solid Black;";
								
								if (record.getLineright())
									style += "border-right:" + record.getLinewidth() + "px solid Black;";
								
								if (record.getLinetop())
									style += "border-top:" + record.getLinewidth() + "px solid Black;";
								
								if (record.getLinebottom())
									style += "border-bottom:" + record.getLinewidth() + "px solid Black;";
							}
							
							if (StringUtils.StringIsEmpty(celltext) && !StringUtils.StringIsEmpty(record.getDefvalue()))
								celltext = record.getDefvalue();
							
							sb.append("<td width=\"" + nowwidth + "px\" height=\"" + nowheight + "px\" " +
									" style=\"" + style + "\" " + (rowspan == 1 ? "" : "rowspan =\"" + rowspan + "\" ") +
									(colspan == 1 ? "" : "colspan =\"" + colspan + "\" ") + 
									align + valign + ">" + celltext + "</td>");
						}
					}
					
					sb.append("</tr>");
				}
			
				sb.append("</table>");
			}
		}		
		
		return "<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"width:98%;\">" + 
        "<tr><td align=\"center\">" + sb.toString() + "</td></tr></table>";
	}
	
	// endregion Print Methods
}
