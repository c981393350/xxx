package com.qianfeng.dao;

import java.util.List;

public interface IResourcesDao {

	public List<Resources> findByUname(String uname);
}
