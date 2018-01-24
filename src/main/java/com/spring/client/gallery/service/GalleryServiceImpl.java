package com.spring.client.gallery.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.client.gallery.dao.GalleryDao;
import com.spring.client.gallery.vo.GalleryVO;

@Service
@Transactional
public class GalleryServiceImpl implements GalleryService {
	Logger logger = Logger.getLogger(GalleryServiceImpl.class);

	@Autowired
	private GalleryDao galleryDao;

	// �� ��� ����
	@Override
	public List<GalleryVO> galleryList() {
		List<GalleryVO> myList = null;
		myList = galleryDao.galleryList();

		return myList;
	}

	// �� �Է� ����
	@Override
	public int galleryInsert(GalleryVO gvo) {
		int result = 0;

		try {
			result = galleryDao.galleryInsert(gvo);
		} catch (Exception e) {
			e.printStackTrace();
			result = 0;
		}
		return result;
	}

}
