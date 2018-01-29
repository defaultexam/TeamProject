package com.spring.common.excel;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import net.sf.jxls.transformer.XLSTransformer;

public class ListExcelView extends AbstractExcelView {
	static Logger logger = Logger.getLogger(ListExcelView.class);

	/*********************************************************************
	 * ���� : "Content-disposition: attachment"�� ������ �ν� ���� Ȯ���ڸ� �����Ͽ� ��� Ȯ������ ���ϵ鿡 ����,
	 * �ٿ�ε�� ������ "���� �ٿ�ε�" ��ȭ���ڸ� �����ֵ��� * ����Ӽ��̶� �� �� �ִ�.
	 ********************************************************************/
	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook arg1, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setHeader("Content-Disposition", "attachment;fileName=\"" + model.get("file_name") + "_"
				+ new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime()) + ".xlsx" + "\"");
		response.setContentType("application/x-msexcel; charset=UTF-8");

		/**********************************************************
		 * * ���� : jXLS�� �������� ������ ���ø��� �̿��Ͽ� * ���� ������ �ս��� �����ϱ� ���� ��Ű�� * ( jXLS�� ���ø��� �������
		 * ���� ���������� ����)
		 **********************************************************/
		String docRoot = request.getSession().getServletContext().getRealPath("/WEB-INF/excel/");
		logger.info("��� üũ(docRoot) : " + docRoot);
		InputStream is = new BufferedInputStream(new FileInputStream(docRoot + model.get("template")));
		XLSTransformer trans = new XLSTransformer();
		Workbook workbook = trans.transformXLS(is, model);
		is.close();
		workbook.write(response.getOutputStream());
	}

}
