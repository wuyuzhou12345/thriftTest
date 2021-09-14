package com.zhou;
import com.zhou.biz.service.DateService;
import com.zhou.biz.service.UserVerifyService;
import com.zhou.thrift.GetUserVerifyInfoRequest;
import com.zhou.thrift.GetUserVerifyInfoResponse;
import com.zhou.thrift.testThriftService;

import javax.annotation.Resource;

public class TestThriftImpl implements testThriftService.Iface{

    @Resource
    private UserVerifyService userVerifyService;

    @Resource
    private DateService dateService;

    @Override
    public String getDate(String userName) throws org.apache.thrift.TException {
        return dateService.getDate(userName);
    }

    @Override
    public GetUserVerifyInfoResponse getUserVerifyInfo(GetUserVerifyInfoRequest getUserVerifyInfoRequest) throws org.apache.thrift.TException {
        return userVerifyService.getVerifyInfo(getUserVerifyInfoRequest);
    }
}
