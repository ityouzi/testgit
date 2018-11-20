package com.nn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nn.bean.SysUser;
import com.nn.bean.SysUserExample;
import com.nn.dao.SysUserMapper;

@Service
public class SysUserService {
	@Autowired
	private SysUserMapper sysUserMapper;

	public SysUser getSysUser(SysUser sysUser) {
		SysUserExample example = new SysUserExample();
		SysUserExample.Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(sysUser.getUsername());
		criteria.andPasswordEqualTo(sysUser.getPassword());
		List<SysUser> list = sysUserMapper.selectByExample(example);
		if(list.size() == 1){
			return list.get(0);
		}
		return null;
	}

	//修改sys_User表中的密码
	public boolean updatePwd(String oldPhone, SysUser sysUser) {
		SysUserExample example = new SysUserExample();
		SysUserExample.Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(oldPhone);
		sysUserMapper.updateByExampleSelective(sysUser, example);
		return true;
		
	}

	//根据username查询注册用户是否存在
	public SysUser getSysUserByUsername(String phone) {
		SysUserExample example = new SysUserExample();
		SysUserExample.Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(phone);
		List<SysUser> list = sysUserMapper.selectByExample(example);
		if(list.size()>0){
			return list.get(0);
		}else {
			return null;
		}
		
	}

}
