package com.spring.client.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.client.login.dao.LoginDao;
import com.spring.client.login.vo.LoginVO;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDao loginDao;

	@Autowired
	private MemberDao memberDao;

	@Override
	public LoginVO userIdSelect(String userId) {
		return loginDao.userIdSelect(userId);
	}

	@Override
	public LoginVO loginSelect(String userId, String userPw) {
		LoginVO vo = null;
		MemberSecurity sec = memberDao.securitySelect(userId);
		if (sec != null) {
			userPw = new String(OpenCrypt.getSHA256(userPw, sec.getSalt()));
			LoginVO lvo = new LoginVO();
			lvo.setUserId(userId);
			lvo.setUserPw(userPw);
			vo = loginDao.loginSelect(lvo);
		}
		return vo;
	}

	@Override
	public int loginHistoryInsert(LoginVO lvo) {
		int result;
		if (userIdSelect(lvo.getUserId())==null) {
			result=1;
		}else {
			LoginVO vo = loginHistorySelect(lvo.getUserId());
			if (vo==null) {
				loginDao.loginHistoryInsert(lvo);
			}
			result = 2;
		}
		return result;
	}

	@Override
	public int loginHistoryUpdate(LoginVO lvo) {
		return loginDao.loginHistoryUpdate(lvo);
	}

	@Override
	public LoginVO loginHistorySelect(String userId) {
		return loginDao.loginHistorySelect(userId);
	}

}
