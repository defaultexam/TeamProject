package com.spring.admin.board.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.admin.board.service.AdminBoardService;
import com.spring.client.board.vo.BoardVO;
import com.spring.common.excel.ListExcelView;

@Controller
@RequestMapping(value = "/admin")
public class AdminBoardController {
	Logger logger = Logger.getLogger(AdminBoardController.class);

	@Autowired
	private AdminBoardService adminBoardService;

	/********************************************
	 * �� ��� �����ϱ�
	 *********************************************/

	@RequestMapping(value = "/board/boardList", method = RequestMethod.GET)
	public String boardList(@ModelAttribute BoardVO bvo, Model model) {
		logger.info("admin boardList ȣ�� ����");

		// ��ü ���ڵ�� ����
		int total = adminBoardService.boardListCnt(bvo);
		logger.info("total = " + total);

		List<BoardVO> boardList = adminBoardService.boardList(bvo);

		model.addAttribute("boardList", boardList);
		model.addAttribute("total", total);
		model.addAttribute("data", bvo);

		return "admin/board/boardList";
	}

	/******************************************************
	 * �׼� �ٿ�ε� �����ϱ� ���� : ListExcelView Ŭ�������� ��Ÿ������ Model�� �����ϰ� �ȴ�.
	 *****************************************************/

	@RequestMapping(value = "/board/boardExcel", method = RequestMethod.GET)
	public ModelAndView boardExcel(@ModelAttribute BoardVO bvo) {
		logger.info("boardExcel ȣ�� ����");

		List<BoardVO> boardList = adminBoardService.boardList(bvo);

		ModelAndView mv = new ModelAndView(new ListExcelView());
		mv.addObject("list", boardList);
		mv.addObject("template", "board.xlsx");
		mv.addObject("file_name", "board");

		return mv;
	}

}
