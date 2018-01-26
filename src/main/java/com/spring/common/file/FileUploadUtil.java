package com.spring.common.file;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.imgscalr.Scalr;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
	static Logger logger = Logger.getLogger(FileUploadUtil.class);

	/* ���� ���ε��� ���� ���� */
	public static void makeDir(String docRoot) {
		File fileDir = new File(docRoot);
		if (fileDir.exists()) {
			return;
		}
		fileDir.mkdirs();
	}

	/* ���� ���ε� �޼��� */
	public static String fileUpload(MultipartFile file, HttpServletRequest request, String fileName)
			throws IOException {
		logger.info("fileUpload ȣ�� ���� ");

		String real_name = null;
		// MultipartFile Ŭ������ getFile() �޼���� Ŭ���̾�Ʈ�� ���ε��� ����
		String org_name = file.getOriginalFilename();
		logger.info("org_name :" + org_name);

		// ���ϸ� ����(�ߺ����� �ʰ�)
		if (org_name != null && (!org_name.equals(""))) {
			real_name = fileName + "_" + System.currentTimeMillis() + "_" + org_name;
			// ������ ���� �̸�
			String docRoot = request.getSession().getServletContext().getRealPath("/resources/uploadStorage/" + fileName);
			System.out.println(docRoot);
			makeDir(docRoot);
			File fileAdd = new File(docRoot + "/" + real_name); // ���� ������
			logger.info("���ε��� ����(fileAdd) : " + fileAdd);
			file.transferTo(fileAdd);
		}
		return real_name;
	}

	/* ���� ���� �޼��� */
	public static void fileDelete(String fileName, HttpServletRequest request) throws IOException {
		logger.info("fileDelete ȣ�� ���� ");

		boolean result = false;
		String dirName = fileName.substring(0, fileName.indexOf("_"));
		String docRoot = request.getSession().getServletContext().getRealPath("/resources/uploadStorage/" + dirName);
		File fileDelete = new File(docRoot + "/" + fileName); // ���� ������
		logger.info("������ ����(fileDelete) : " + fileDelete);
		if (fileDelete.exists() && fileDelete.isFile()) {
			result = fileDelete.delete();
		}
		logger.info("���� ���� ����(true/false) : " + result);
	}

	/* ���� Thumbnail ���� �޼ҵ� */
	public static String makeThumbnail(String fileName, HttpServletRequest request) throws Exception {
		String dirName = fileName.substring(0, fileName.indexOf("_"));
		// �̹����� �����ϴ� ���� ����
		String imgPath = request.getSession().getServletContext().getRealPath("/resources/uploadStorage/" + dirName);

		// ����� ������ ���� ���(������ ��ġ : c:\...)
		File fileAdd = new File(imgPath, fileName);
		logger.info("���� �̹��� ����(fileAdd):" + fileAdd);

		BufferedImage sourceImg = ImageIO.read(fileAdd);
		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 133);
		// resize(���[BufferedImage Ÿ��], ��������, ���� �Ǵ� �ʺ�, ũ��)

		String thumbnailName = "thumbnail_" + fileName;
		String docRoot = imgPath + "/thumbnail";
		makeDir(docRoot);

		File newFile = new File(docRoot, thumbnailName);
		logger.info("���ε��� ����(newFile) : " + newFile);

		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
		logger.info("Ȯ����(formatName) : " + formatName);
		ImageIO.write(destImg, formatName, newFile);
		return thumbnailName;

	}
}
