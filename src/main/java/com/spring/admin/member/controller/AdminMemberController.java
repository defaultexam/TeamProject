package com.spring.admin.member.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.admin.member.service.AdminMemberService;
import com.spring.client.member.vo.MemberVO;
import com.spring.common.graph.ChartMake;

@Controller
@RequestMapping(value="/admin")
public class AdminMemberController {

	Logger logger = Logger.getLogger(AdminMemberController.class);
	
	@Autowired
	private AdminMemberService adminMemberService;
	/**************************************************************
	* ȸ�� ����Ʈ �����ϱ�
	**************************************************************/
	@RequestMapping(value="/member/memberList", method = RequestMethod.GET)
	public String memberList(@ModelAttribute MemberVO bvo,
	Model model, HttpServletRequest request) {
		
		logger.info("memberList ȣ�� ����");
		
		List<MemberVO> memberList = adminMemberService.memberList(bvo);
		 /*���ɴ뺰 ������*/
		Map<String,Integer> memberAgeList = adminMemberService.memberAgeList();
		ChartMake.pieChart(request, memberAgeList);
		/* ���� ������*/
		Map<String,Integer> memberGenderList = adminMemberService.memberGenderList();
		ChartMake.barChart(request, memberGenderList);
		
		model.addAttribute("memberList", memberList);
		
		return "admin/member/memberList";
		
	}
	
	
}
