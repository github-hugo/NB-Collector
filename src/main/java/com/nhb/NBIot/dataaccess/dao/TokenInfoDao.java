package com.nhb.NBIot.dataaccess.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nhb.NBIot.entity.TokenInfo;

@Repository
public interface TokenInfoDao extends MongoRepository<TokenInfo, String> {

	TokenInfo findByAppIdAndSecret(String appId, String secret);

}
