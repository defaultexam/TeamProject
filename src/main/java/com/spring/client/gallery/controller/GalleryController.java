package com.spring.client.gallery.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.client.gallery.service.GalleryService;
import com.spring.client.gallery.vo.GalleryVO;
import com.spring.common.file.FileUploadUtil;

@Controller
@RequestMapping(value = "/gallery")
public class GalleryController {
	Logger logger = Logger.getLogger(GalleryController.class);

	@Autowired
	private GalleryService galleryService;

	@RequestMapping(value = "/galleryList")
	public String galleryList() {
		logger.info("galleryList ȣ�� ����");

		return "gallery/galleryList";
	}

	/*************************
	 * ������ ��� �����ϱ�~!
	 *************************/
	@ResponseBody
	@RequestMapping(value = "/galleryData")
	public String galleryData(ObjectMapper mapper) {
		logger.info("galleryData ȣ�� ����");
		String listData = "";
		List<GalleryVO> galleryList = galleryService.galleryList();

		try {
			listData = mapper.writeValueAsString(galleryList);
			// logger.info(listData);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return listData;

	}

	/*************************
	 * �۾��� �����ϱ�~!
	 * 
	 * @throws Exception
	 *************************/
	@ResponseBody
	@RequestMapping(value = "/galleryInsert", method = RequestMethod.POST, produces = "text/plain; charset=UTF-8")
	public String galleryInsert(@ModelAttribute GalleryVO gvo, HttpServletRequest request) throws Exception {
		logger.info("galleryInsert ȣ�� ����");

		logger.info("file name : " + gvo.getFile().getOriginalFilename());
		String value = "";
		int result = 0;

		if (gvo.getFile() != null) {
			String fileName = FileUploadUtil.fileUpload(gvo.getFile(), request, "gallery");
			gvo.setG_file(fileName);

			// fileUpload.java ���� makeThumbnail �޼ҵ带 �����ؾ���.
			String thumbName = FileUploadUtil.makeThumbnail(fileName, request);
			gvo.setG_thumb(thumbName);
		}
		result = galleryService.galleryInsert(gvo);

		if (result == 1) {
			value = "����";

		} else {
			value = "����";

		}

		return value;

	}

}
